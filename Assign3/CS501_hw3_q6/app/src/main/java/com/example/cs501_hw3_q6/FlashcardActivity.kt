package com.example.cs501_hw3_q6
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.example.cs501_hw3_q6.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.cs501_hw3_q6.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class FlashcardActivity : AppCompatActivity(){
    /*
    * The flash card activity
    * */
    private val flashcardViewModel: QuestionViewModel by viewModels()
    private lateinit var binding_questions: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding_questions = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding_questions.root)

        // Points to the buttons, for status change later
        val generateButton = findViewById<Button>(binding_questions.generateQuestions.id)
        val submitButton = findViewById<Button>(binding_questions.submitAnswer.id)

        // Restart from phone status change
        // Restore the display content
        if(flashcardViewModel.inGame){
            setQuestion()
            generateButton.isEnabled = false
            submitButton.isEnabled = true
        }
        else{
            setQuestion()
            generateButton.isEnabled = true
            submitButton.isEnabled = false
        }

        // Click on the generate questions button
        binding_questions.generateQuestions.setOnClickListener {
            if(flashcardViewModel.totalQuestion == 0) {
                // Mixture questions
                if(binding_questions.subtraction.isChecked && binding_questions.addition.isChecked) {
                    flashcardViewModel.setCurrentMode("mixed")
                    flashcardViewModel.generateMixed()
                }
                // Only subtraction questions
                else if(binding_questions.subtraction.isChecked){
                    flashcardViewModel.setCurrentMode("subtraction")
                    flashcardViewModel.generateSubtraction()
                }
                // Only addition questions
                else if(binding_questions.addition.isChecked){
                    flashcardViewModel.setCurrentMode("addition")
                    flashcardViewModel.generateAddition()
                }
                // Mixture questions
                else{
                    flashcardViewModel.setCurrentMode("mixed")
                    flashcardViewModel.generateMixed()
                }
                setQuestion()
            }
            // Can only submit answers
            generateButton.isEnabled = false
            submitButton.isEnabled = true
            flashcardViewModel.inGame = true
        }

        binding_questions.submitAnswer.setOnClickListener {
            // Compare the answer
            if (flashcardViewModel.answer == binding_questions.answerBox.text.toString().toInt()) {
                flashcardViewModel.score_increment()
                Toast.makeText(this,"Correct, one point added to score.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Incorrect, no points earned for this question.", Toast.LENGTH_SHORT).show()
            }
            // Having answered all the questions
            if(flashcardViewModel.totalQuestion == 9){
                Toast.makeText(this, "Your total score is "+flashcardViewModel.totalScore+" out of 10", Toast.LENGTH_SHORT).show()
                flashcardViewModel.refresh()
                // Can only generate questions
                generateButton.isEnabled = true
                submitButton.isEnabled = false
                flashcardViewModel.inGame = false
            }
            else {
                // Go to next question
                flashcardViewModel.question_num_increment()
                if(flashcardViewModel.getCurrentStatus == "addition"){
                    flashcardViewModel.nextQuestion()
                    flashcardViewModel.generateAddition()
                }else if(flashcardViewModel.getCurrentStatus == "subtraction"){
                    flashcardViewModel.nextQuestion()
                    flashcardViewModel.generateSubtraction()
                }else if(flashcardViewModel.getCurrentStatus == "mixed"){
                    flashcardViewModel.nextQuestion()
                    flashcardViewModel.generateMixed()
                }
                setQuestion()
            }
        }
    }

    // Intent to connect to other activities
    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, FlashcardActivity::class.java)
        }
    }

    // set question display
    fun setQuestion(){
        binding_questions.operation.text = flashcardViewModel.operation
        binding_questions.topOperand.text = flashcardViewModel.topNum.toString()
        binding_questions.secondOperand.text = flashcardViewModel.bottomNum.toString()
    }

}
package com.example.task5

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.example.task5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.text.TextUtils;


class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val op1str = binding.editText1.text.toString()
            val op2str = binding.editText2.text.toString()
            if(TextUtils.isEmpty(op1str) || TextUtils.isEmpty(op2str)){
                showBarTop("Please enter the operands")
            }
            else{
                val operand1 = op1str.toDouble()
                val operand2 = op2str.toDouble()
                val result = calculate(operand1, operand2)
                binding.textViewResult.text = "Result: $result"
            }
        }
    }

     fun calculate(operand1: Double, operand2: Double): Double {
         print("nvbijsbibvf")
         print(operand1)
         print(operand2)
         if(operand1 == null || operand2 == null){
             showBarTop("Please enter the operands")
             return 0.0
         }
        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.Addition -> return operand1 + operand2
            R.id.Subtraction -> return operand1 - operand2
            R.id.Multiplication -> return operand1 * operand2
            R.id.Division -> {
                if (operand2 == 0.0){
                    showBarTop("Cannot divide by 0")
                }
                else return operand1 / operand2
            }
            R.id.Modulus -> {
                if (operand2 == 0.0){
                    showBarTop("Cannot divide by 0")
                }
                else return operand1 % operand2
            }
            else -> {
                showBarTop("You must select an operand")
            }
        }
         return Double.NaN
     }

    private fun showBarTop(sentence: String){
        val bar = Snackbar.make(binding.root, sentence, 1000)
        val view: View = bar.getView()
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        bar.show()
    }
}

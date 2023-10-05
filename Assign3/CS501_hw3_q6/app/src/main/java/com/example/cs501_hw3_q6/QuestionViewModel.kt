package com.example.cs501_hw3_q6

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

// Lists of operations for different game modes
val mixed_operationList = listOf('+','-','+','-','+','-','+','-','+','-')
val addition_operationList = listOf('+','+','+','+','+','+','+','+','+','+')
val subtraction_operationList = listOf('-','-','-','-','-','-','-','-','-','-')

// Game status
var score = 0
var status = ""
var questionCount = 0
class QuestionViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var currentVal: Int = 0

    // Properties for current question, used by flash card activity
    var topNum = 0
    var bottomNum = 0
    var answer = 0
    var operation = ""
    var inGame = false

    val totalScore: Int
        get() = score
    val totalQuestion: Int
        get() = questionCount
    val getMixed_operationList: List<Char>
        get() = mixed_operationList
    val getAddition_operationList: List<Char>
        get() = addition_operationList
    val getSubtraction_operationList: List<Char>
        get() = subtraction_operationList
    val getCurrentVal: Int
        get() = currentVal
    val getCurrentStatus: String
        get() = status
    fun nextQuestion() {
        currentVal = currentVal + 1
    }
    fun refresh(){
        currentVal = 0
        score = 0
        questionCount = 0
    }
    fun score_increment(){
        score+=1
    }
    fun question_num_increment(){
        questionCount+=1
    }
    fun setCurrentMode(current_status : String){
        status = current_status
    }

    // Generate a desired question
    fun generateQuestion(operationList : List<Char>, currentIndex : Int) : Question{
        val top_min = 1
        val top_max = 99
        val bottom_min = 1
        val bottom_max = 20
        var topNum = Random.nextInt(top_min, top_max+1)
        var bottomNum = Random.nextInt(bottom_min, bottom_max+1)
        var answer = 0
        if (operationList[currentIndex] == '+') {
            answer = topNum + bottomNum
        } else {
            answer = topNum - bottomNum
        }
        return Question(topNum,bottomNum,answer,operationList[currentIndex].toString())
    }

    // Generate a question based on user selection
    fun generateSubtraction(){
        val question = generateQuestion(
            getSubtraction_operationList,
            getCurrentVal
        )
        topNum = question.topNumber
        bottomNum = question.bottomNumber
        answer = question.answer
        operation = question.operation
    }
    fun generateAddition(){
        val question = generateQuestion(
            getAddition_operationList,
            getCurrentVal
        )
        topNum = question.topNumber
        bottomNum = question.bottomNumber
        answer = question.answer
        operation = question.operation
    }
    fun generateMixed(){
        val question = generateQuestion(
            getMixed_operationList,
            getCurrentVal
        )
        topNum = question.topNumber
        bottomNum = question.bottomNumber
        answer = question.answer
        operation = question.operation
    }
}
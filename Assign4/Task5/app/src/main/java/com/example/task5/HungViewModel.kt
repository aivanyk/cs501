package com.example.task5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class HungViewModel : ViewModel() {

    val chars: List<Char> = ('A'..'Z').toList()
    val clicked = MutableList(26){false}

    val questions: List<Question> = listOf(
        Question("HUNG", "H  G", "This game", 0),
        Question("BANANA", "BA A A", "Food", 0),
        Question("TABLET", "T B  T", "Electronic", 0),
        Question("WINNER", "W NN  ", "You", 0),
        Question("HINT", "H N ", "Here", 0),
    )

    var refreshFlag = MutableLiveData<Int>(0)
    var currQuestion = Random.nextInt(0, questions.size)
    var guessChange = MutableLiveData<Int>(0)
    var hungStatus = MutableLiveData<Int>(0)
    var hintStatus = 0

    var hungPics: List<Int>  = listOf(
        R.drawable.hung00,
        R.drawable.hung01,
        R.drawable.hung02,
        R.drawable.hung03,
        R.drawable.hung04,
        R.drawable.hung05,
        R.drawable.hung06
    )

    fun getCurrQuestion(): Question {
        return questions[currQuestion]
    }

    fun guessChange() {
        guessChange.value = guessChange.value?.plus(1)
    }

    fun hangOneMore():Boolean {
        hungStatus.value = hungStatus.value?.plus(1)
        guessChange()
        return hungStatus.value == 6
    }

    fun nearHung(): Boolean {
        return hungStatus.value == 5
    }

    fun showHalf() {
        getCurrQuestion().showHalf()

        for(i in 0..getCurrQuestion().length()-1){
            var curr = getCurrQuestion().getChar(i)[0]
            if(curr != ' ') clicked[curr.code-'A'.code] = true
        }
    }

    fun nextQuestion() {
        currQuestion = Random.nextInt(0, questions.size)
        guessChange()
        hintStatus = 0
    }

    fun refresh(){
        getCurrQuestion().refresh()
        var pre = currQuestion
        do {
            currQuestion = Random.nextInt(0, questions.size)
        } while (pre == currQuestion)
        hungStatus.value = 0
        hintStatus = 0
        clicked.fill(false)
        guessChange.value = 0
        refreshFlag.value = refreshFlag.value?.plus(1)
    }

    fun gameOver(): Boolean {
        return getCurrQuestion().full || hungStatus.value == 6
    }
}

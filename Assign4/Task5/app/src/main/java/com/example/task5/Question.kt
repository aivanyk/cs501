package com.example.task5

class Question (
    val fullName: String,
    val halfName: String,
    val hint: String,
    val state: Int,
    ){
    val len = fullName.length
    val fullArray = fullName.toCharArray()
    val halfArray = halfName.toCharArray()
    var fill = MutableList(len){false}
    var full = false
//    val fill = MutableList(len){true}
//        val fill = mutableListOf<Boolean>(true, true, true, false, false, false)

    fun length(): Int {
        return len
    }

    fun insert(n: Char): Boolean {
        var f = true
        var res = false
        for(i in 0..len-1){
            if(!fill[i]){
                if(fullArray[i]==n){
                    fill[i] = true
                    res = true
                }
                else
                    f = false
            }
        }
        full = f
        return res
    }

    fun getChar(i: Int): String {
        if(fill[i]) return ""+fullArray[i]
        else return " "
    }

    fun showHalf() {
        for(i in 0..len-1){
            if(halfArray[i] != ' '){
                fill[i] = true
            }
        }
    }

    fun showAll() {
        fill = MutableList(len){true}
        full = true
    }

    fun refresh(){
        fill.fill(false)
        full = false
    }
}
package com.inc205.trivi.ui.trivia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inc205.trivi.ui.trivia.model.Question
import com.inc205.trivi.ui.trivia.model.Responses

class QuestionViewModel:ViewModel(){

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> =  _email

    private val _listNumber1 = MutableLiveData<List<Int>>()
    val listNumber1 : LiveData<List<Int>> = _listNumber1

    private val _listNumber2 = MutableLiveData<List<Int>>()
    val listNumber2 : LiveData<List<Int>> = _listNumber2

    private val _listOperations = MutableLiveData<List<String>>()
    val listOperations : LiveData<List<String>> = _listOperations

    private val _numQuestions = MutableLiveData<Int>()
    val numQuestions : LiveData<Int> = _numQuestions

    private val _listQuestions = MutableLiveData<List<Question>>()
    val listQuestions : LiveData<List<Question>> = _listQuestions

    private val _selectedQuestion = MutableLiveData<Int>()
    val selectedQuestion : LiveData<Int> = _selectedQuestion

    fun generateQuestions(){
        var ques: List<Question> = emptyList()
        val a = _listNumber1.value ?: listOf(1,4,5,6)
        val b = _listNumber2.value  ?: listOf(1,2,3,4,5,6,7,8,9,10,11,12)
        val o = _listOperations.value ?: listOf("+","x","÷")
        val nu = _numQuestions.value  ?: 15

        for( n in a) {
            for (m in b){
                for (p in o){
                    var r: Int
                    when (p) {
                        "+" -> {
                            r = n+m
                            var r1:List<Responses> = listOf(Responses(result = r, correct = true, selected = false))
                            repeat(3) { index ->
                                if(listOf(1,2).random() == 1){
                                    r1 += Responses(result = r+(index+1), correct = false, selected = false)
                                }else{
                                    r1 += Responses(result = r-(index+1), correct = false, selected = false)
                                }
                            }
                            ques = ques.plus(Question(question="$n $p $m = __",checkExisting="$n$p$m=$r",correct=false,options = r1.shuffled()))
                        }
                        "-" -> {
                            r = n+m
                            var r1:List<Responses> = listOf(Responses(result = n, correct = true, selected = false))
                            repeat(3) { index ->
                                if(listOf(1,2).random() == 1){
                                    r1+=Responses(result = n+(index+1), correct = false, selected = false)
                                }else{
                                    r1+=Responses(result = n-(index+1), correct = false, selected = false)
                                }
                            }
                            ques = ques.plus(Question(question="$r $p $m = __",checkExisting="$r$p$m=$n",correct=false,options = r1.shuffled()))

                        }
                        "x" -> {
                            r = n*m
                            var r1:List<Responses> = listOf(Responses(result = r, correct = true, selected = false))
                            repeat(3) { index ->
                                if(listOf(1,2).random() == 1){
                                    r1+=Responses(result = r+(index+1), correct = false, selected = false)
                                }else{
                                    r1+=Responses(result = r-(index+1), correct = false, selected = false)
                                }
                            }
                            ques = ques.plus(Question(question="$n $p $m = __",checkExisting="$n$p$m=$r",correct=false,options = r1.shuffled()))
                        }
                        "÷" -> {
                            r = n*m
                            var r1:List<Responses> = listOf(Responses(result = n, correct = true, selected = false))
                            repeat(3) { index ->
                                if(listOf(1,2).random() == 1){
                                    r1+=Responses(result = n+(index+1), correct = false, selected = false)
                                }else{
                                    r1+=Responses(result = n-(index+1), correct = false, selected = false)
                                }
                            }
                            ques = ques.plus(Question(question="$r $p $m = __",checkExisting="$r$p$m=$n",correct=false,options = r1.shuffled()))
                        }
                    }
                }
            }
        }
        _listQuestions.value = ques.shuffled().take(nu)
        _selectedQuestion.value = 0
    }

    fun updateAnswer(){

    }
}
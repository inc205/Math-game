package com.inc205.trivi.ui.trivia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inc205.trivi.ui.trivia.model.Question
import com.inc205.trivi.ui.trivia.model.Responses

class QuestionViewModel : ViewModel() {

    private val _listNumber1 = MutableLiveData<List<Int>>()
    val listNumber1: LiveData<List<Int>> = _listNumber1

    private val _listNumber2 = MutableLiveData<List<Int>>()
    val listNumber2: LiveData<List<Int>> = _listNumber2

    private val _listOperations = MutableLiveData<List<String>>()
    val listOperations: LiveData<List<String>> = _listOperations

    private val _numQuestions = MutableLiveData<Int>()
    val numQuestions: LiveData<Int> = _numQuestions

    private val _listQuestions = MutableLiveData<List<Question>>()
    val listQuestions: LiveData<List<Question>> = _listQuestions

    private val _selectedQuestion = MutableLiveData<Int>()
    val selectedQuestion: LiveData<Int> = _selectedQuestion

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> = _score

    private val _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean> = _finished

    fun generateQuestions() {
        var ques: List<Question> = emptyList()
        val a = _listNumber1.value ?: listOf(1, 4, 5, 6)
        val b = _listNumber2.value ?: listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        val o = _listOperations.value ?: listOf("+", "x", "÷")
        val nu = _numQuestions.value ?: 15

        for (n in a) {
            for (m in b) {
                for (p in o) {
                    var r: Int
                    when (p) {
                        "+" -> {
                            r = n + m
                            var r1: List<Responses> =
                                listOf(Responses(result = r, correct = true, selected = false))
                            repeat(3) { index ->
                                if (listOf(1, 2).random() == 1) {
                                    r1 += Responses(
                                        result = r + (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                } else {
                                    r1 += Responses(
                                        result = r - (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                }
                            }
                            ques = ques.plus(
                                Question(
                                    question = "$n $p $m = __",
                                    answered = false,
                                    correct = false,
                                    options = r1.shuffled()
                                )
                            )
                        }

                        "-" -> {
                            r = n + m
                            var r1: List<Responses> =
                                listOf(Responses(result = n, correct = true, selected = false))
                            repeat(3) { index ->
                                if (listOf(1, 2).random() == 1) {
                                    r1 += Responses(
                                        result = n + (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                } else {
                                    r1 += Responses(
                                        result = n - (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                }
                            }
                            ques = ques.plus(
                                Question(
                                    question = "$r $p $m = __",
                                    answered = false,
                                    correct = false,
                                    options = r1.shuffled()
                                )
                            )

                        }

                        "x" -> {
                            r = n * m
                            var r1: List<Responses> =
                                listOf(Responses(result = r, correct = true, selected = false))
                            repeat(3) { index ->
                                if (listOf(1, 2).random() == 1) {
                                    r1 += Responses(
                                        result = r + (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                } else {
                                    r1 += Responses(
                                        result = r - (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                }
                            }
                            ques = ques.plus(
                                Question(
                                    question = "$n $p $m = __",
                                    answered = false,
                                    correct = false,
                                    options = r1.shuffled()
                                )
                            )
                        }

                        "÷" -> {
                            r = n * m
                            if (r == 0 || m == 0) continue
                            var r1: List<Responses> =
                                listOf(Responses(result = n, correct = true, selected = false))
                            repeat(3) { index ->
                                if (listOf(1, 2).random() == 1) {
                                    r1 += Responses(
                                        result = n + (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                } else {
                                    r1 += Responses(
                                        result = n - (index + 1),
                                        correct = false,
                                        selected = false
                                    )
                                }
                            }
                            ques = ques.plus(
                                Question(
                                    question = "$r $p $m = __",
                                    answered = false,
                                    correct = false,
                                    options = r1.shuffled()
                                )
                            )
                        }
                    }
                }
            }
        }
        _listQuestions.value = ques.shuffled().take(nu)
        _selectedQuestion.value = 0
        _score.value = 0
        _finished.value = false
    }

    fun updateAnswer(selected: Int) {
        var ques: List<Question>? = _listQuestions.value ?: return
        var loc: Int = _selectedQuestion.value ?: 0
        ques?.get(loc)?.options?.get(selected)?.selected = true
        if ((ques?.get(loc)?.options?.get(selected)?.selected == true) && (ques[loc].options[selected]?.correct
                ?: false)
        ) {
            _score.value = _score.value!! + 1
        }
        _listQuestions.value = ques
        if ((loc + 1) < ques?.count() ?: 0) {
            _selectedQuestion.value = loc + 1
        } else {
            _finished.value = true
        }
    }

    fun toggleNumberOnListA(i: Int) {
        var arr: List<Int> = _listNumber1.value ?: emptyList()
        if (i in arr) {
            arr -= i
        } else {
            arr += i
        }
        _listNumber1.value = arr
    }

    fun toggleNumberOnListB(i: Int) {
        var arr: List<Int> = _listNumber2.value ?: emptyList()
        if (i in arr) {
            arr -= i
        } else {
            arr += i
        }
        _listNumber2.value = arr
    }

    fun toggleOperation(i: String) {
        var arr: List<String> = _listOperations.value ?: emptyList()
        if (i in arr) {
            arr -= i
        } else {
            arr += i
        }
        _listOperations.value = arr
    }

    fun setNumQuestions(i: Int) {
        _numQuestions.value = i
    }

    fun results() {
        _finished.value = false
    }
}
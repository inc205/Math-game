package com.inc205.trivi.ui.trivia.model

data class Question(
    var question:String,
    var checkExisting:String,
    var correct:Boolean,
    var options:List<Responses>
)

data class Responses(
    var result:Int,
    var correct:Boolean,
    var selected:Boolean
)
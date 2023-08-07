package com.inc205.trivi.ui.trivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Question(
    var question:String,
    var answered:Boolean,
    var correct:Boolean,
    var options:List<Responses>
)

data class Responses(
    var result:Int,
    var correct:Boolean,
    var selected:Boolean
)
//To save the last configuration in the local storage
@Serializable
data class Configurations(
    @SerialName("num1") val num1:List<Int>,
    @SerialName("num2") val num2:List<Int>,
    @SerialName("op")   val op:List<String>,
    @SerialName("num")  val num:Int
)

//Class for credits
data class Developer(
    val name:String,
    val email:String
)
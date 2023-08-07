package com.inc205.trivi.ui.trivia.model

sealed class Routes (val route:String){
    object Game:Routes("game")
    object Configuration:Routes("configuration")
    object Results:Routes("results")
    object Credits:Routes("credits")
}
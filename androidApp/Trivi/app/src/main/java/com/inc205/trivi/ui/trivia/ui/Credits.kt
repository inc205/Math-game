package com.inc205.trivi.ui.trivia.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Credits() {
    ContactElement()
}

@Composable
fun ContactElement() {
    Box(modifier = Modifier.fillMaxWidth()){
        Column() {
            Text(text ="Nombre")
            Text(text = "Email")
        }
    }
}

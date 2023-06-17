package com.inc205.trivi

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inc205.trivi.ui.theme.TriviTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetUI()
                }
            }
        }
    }
}

@Composable
fun GetUI(){
    Greeting(name = "Trivi")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TriviTheme {
        Greeting("Android")
    }
}


//Previews

@Preview(showBackground = true, name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun LightPreview() {
    GetUI()
}

@Preview(showBackground = true, name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun DarkPreview() {
    GetUI()
}


@Preview(showBackground = true, name = "Undefined", uiMode = Configuration.UI_MODE_NIGHT_MASK, showSystemUi = true)
@Composable
fun UndefinedPreview() {
    GetUI()
}
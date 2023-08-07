package com.inc205.trivi.ui.trivia.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc205.trivi.R
import com.inc205.trivi.ui.theme.TriviTheme
import com.inc205.trivi.ui.trivia.model.Developer
import com.inc205.trivi.ui.trivia.model.Routes

@Composable
fun Credits(vm: QuestionViewModel, nc: NavHostController) {
    val credits: List<Developer> = listOf(
        Developer(name = "Katherine Collaguazo ", email = "kas.collaguazo@yavirac.edu.ec"),
        Developer(name = "Anderso Macias", email = "anderk222@gmail.com"),
        Developer(name = "Jhair Alejandro Vasquez Galarza", email = "jhairavg.dev@gmail.com"),
        Developer(name = "Jonas Villacis", email = "jonasvillacis@yahoo.com"),
        Developer(name = "Brandon Josue Caranqui Agualongo", email = "brandito144@gmail.com"),
        Developer(name = "Mateo David Castro Vera", email = "davidxdcastro@gmail.com"),
        Developer(name = "Michael Sebastián Ortiz Jarrin", email = "sebasortiz112004@gmail.com"),
        Developer(name = "Diego Cando", email = "info@inc205.com")
    )
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.width(350.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Button(
                    onClick = {
                        nc.navigate(Routes.Game.route)
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(100.dp),
                    shape = RoundedCornerShape(20)
                ) {
                    Text(text = stringResource(id = R.string.play), color = Color.White)
                }

                Button(onClick = {
                    nc.navigate(Routes.Configuration.route)
                },
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp),
                    shape = RoundedCornerShape(20)) {
                    Text(text = stringResource(id = R.string.configuration), color = Color.White)
                }
            }

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (me in credits.sortedBy { it.name }) {
                    ContactElement(me = me)
                }
            }

        }
    }

}

@Composable
fun ContactElement(me: Developer) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(16.dp))
            .width(350.dp)
            .height(60.dp),
        contentAlignment = Alignment.Center,

        ) {
        Column() {
            Text(text = me.name)
            Text(text = me.email)
        }
    }
}


//Previews

@Preview(
    showBackground = true,
    name = "Light",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun LightPreview1() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Credits(QuestionViewModel(), rememberNavController())
        }
    }
}

@Preview(
    showBackground = true,
    name = "Dark",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun DarkPreview1() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Credits(QuestionViewModel(), rememberNavController())
        }
    }
}

@Preview(
    showBackground = true,
    name = "Undefined",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_MASK,
    showSystemUi = true
)
@Composable
fun UndefinedPreview1() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Credits(QuestionViewModel(), rememberNavController())
        }
    }
}
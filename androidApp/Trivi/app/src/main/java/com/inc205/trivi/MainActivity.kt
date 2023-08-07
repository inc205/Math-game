package com.inc205.trivi

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inc205.trivi.ui.theme.TriviTheme
import com.inc205.trivi.ui.trivia.model.Question
import com.inc205.trivi.ui.trivia.model.Routes
import com.inc205.trivi.ui.trivia.ui.Config
import com.inc205.trivi.ui.trivia.ui.Credits
import com.inc205.trivi.ui.trivia.ui.QuestionViewModel
import com.inc205.trivi.ui.trivia.ui.Results

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
                    val nc = rememberNavController()
                    val vm = QuestionViewModel()
                    NavHost(navController = nc, startDestination = Routes.Configuration.route) {
                        composable(Routes.Game.route) { GetUI(vm = vm, nc = nc) }
                        composable(Routes.Configuration.route) { Config(vm = vm, nc = nc) }
                        composable(Routes.Results.route) { Results(vm = vm, nc = nc) }
                        composable(Routes.Credits.route) { Credits(vm = vm, nc = nc) }
                    }
                }
            }
        }
    }
}

@Composable
fun GetUI(vm: QuestionViewModel, nc: NavHostController) {
    Game(vm = vm, nc = nc)
}

@Composable
fun Game(vm: QuestionViewModel, nc: NavHostController) {
    val selected: Int by vm.selectedQuestion.observeAsState(initial = 0)
    val pl: List<Question> by vm.listQuestions.observeAsState(initial = emptyList())
    val score: Int by vm.score.observeAsState(initial = 0)
    val finished: Boolean by vm.finished.observeAsState(initial = false)

    if (finished) {
        vm.results()

        nc.navigate(Routes.Results.route)
//        vm.results()
    }

    var pregunta: Question = pl[selected]

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
            Row(
                modifier = Modifier.width(300.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.score, score))// "Score:$score")
                Text(text = "${(selected + 1)}/${pl.count()}")
            }
            QuestionTitle(q = pregunta)

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ButtonOption(q = pregunta, i = 0, vm = vm)
                    ButtonOption(q = pregunta, i = 1, vm = vm)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ButtonOption(q = pregunta, i = 2, vm = vm)
                    ButtonOption(q = pregunta, i = 3, vm = vm)
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Button(
                    onClick = {
                        vm.generateQuestions()
                    },
                    modifier = Modifier
                        .height(40.dp),
                    shape = RoundedCornerShape(20)
                ) {
                    Text(text = stringResource(id = R.string.reset), color = Color.White)
                }

                Button(
                    onClick = {
                        nc.navigate(Routes.Configuration.route)
                    },
                    modifier = Modifier
                        .height(40.dp),
                    shape = RoundedCornerShape(20)
                ) {
                    Text(text = stringResource(id = R.string.configuration), color = Color.White)
                }

                Button(
                    onClick = {
                        nc.navigate(Routes.Credits.route)
                    },
                    modifier = Modifier
                        .height(40.dp),
                    shape = RoundedCornerShape(20)
                ) {
                    Text(text = stringResource(id = R.string.info), color = Color.White)
                }

            }
        }
    }
}

@Composable
private fun QuestionTitle(q: Question) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(300.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = q.question,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ButtonOption(q: Question, i: Int, vm: QuestionViewModel) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .background(
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                vm.updateAnswer(i)

            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = q.options[i].result.toString(),
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//Previews

@Preview(
    showBackground = true,
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun LightPreview() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GetUI(QuestionViewModel(), rememberNavController())
        }
    }
}

@Preview(
    showBackground = true,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun DarkPreview() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GetUI(QuestionViewModel(), rememberNavController())
        }
    }
}

@Preview(
    showBackground = true,
    name = "Undefined",
    uiMode = Configuration.UI_MODE_NIGHT_MASK,
    showSystemUi = true
)
@Composable
fun UndefinedPreview() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GetUI(QuestionViewModel(), rememberNavController())
        }
    }
}
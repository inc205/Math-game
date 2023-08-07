package com.inc205.trivi.ui.trivia.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc205.trivi.R
import com.inc205.trivi.ui.theme.TriviTheme
import com.inc205.trivi.ui.trivia.model.Routes

@Composable
fun Config(vm: QuestionViewModel, nc: NavHostController) {
    val nums = (0..12)
    val l1: List<Int> by vm.listNumber1.observeAsState(initial = emptyList())
    val l2: List<Int> by vm.listNumber2.observeAsState(initial = emptyList())
    val lo: List<String> by vm.listOperations.observeAsState(initial = emptyList())
    val nu: Int by vm.numQuestions.observeAsState(initial = 0)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = stringResource(id = R.string.element_a))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(40.dp),
                modifier = Modifier.width(350.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                content = {
                    items(nums.count()) { index ->
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .background(
                                    if (l1.contains(index)) MaterialTheme.colorScheme.onTertiary
                                    else MaterialTheme.colorScheme.tertiary,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clickable { vm.toggleNumberOnListA(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "$index")
                        }
                    }
                }
            )

            Text(text = stringResource(id = R.string.element_b))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(40.dp),
                modifier = Modifier.width(350.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                content = {
                    items(nums.count()) { index ->
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .background(
                                    if (l2.contains(index)) MaterialTheme.colorScheme.onTertiary
                                    else MaterialTheme.colorScheme.tertiary,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clickable { vm.toggleNumberOnListB(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "$index")
                        }
                    }
                }
            )
            Text(text = stringResource(id = R.string.operations))
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                for (o in listOf("+", "-", "x", "÷")) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                if (lo.contains(o)) MaterialTheme.colorScheme.onTertiary
                                else MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { vm.toggleOperation(o) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "$o")
                    }
                }
            }

            Text(text = stringResource(id = R.string.how_many_questions))
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                if (nu == ((it + 1) * 5)) MaterialTheme.colorScheme.onTertiary
                                else MaterialTheme.colorScheme.tertiary,

                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { vm.setNumQuestions((it + 1) * 5) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${(it + 1) * 5}")
                    }
                }
            }
            val context = LocalContext.current
            val mess = stringResource(id = R.string.configuration_error)
            Button(
                onClick = {
                    if (l1.isNotEmpty() && l2.isNotEmpty() && lo.isNotEmpty() && nu > 0) {
                        vm.generateQuestions()
                        nc.navigate(Routes.Game.route)
                    } else {
                        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(20)
            ) {
                Text(text = stringResource(id = R.string.apply), color = Color.White)
            }
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
fun LightPreview() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Config(QuestionViewModel(), rememberNavController())
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
fun DarkPreview() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Config(QuestionViewModel(), rememberNavController())
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
fun UndefinedPreview() {
    TriviTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Config(QuestionViewModel(), rememberNavController())
        }
    }
}
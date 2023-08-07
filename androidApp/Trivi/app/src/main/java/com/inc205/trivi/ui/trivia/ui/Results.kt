package com.inc205.trivi.ui.trivia.ui

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.inc205.trivi.R
import com.inc205.trivi.ui.trivia.model.Question
import com.inc205.trivi.ui.trivia.model.Routes
import java.time.format.TextStyle

@Composable
fun Results(vm: QuestionViewModel, nc: NavHostController) {
    val pl: List<Question> by vm.listQuestions.observeAsState(initial = emptyList())
    val sc: Int by vm.score.observeAsState(initial = 0)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row() {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Button(
                        onClick = {
                            nc.navigate(Routes.Game.route)
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
            Text(text = stringResource(id = R.string.score, sc), fontSize = 30.sp)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                for (a in pl) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (a.options.filter { x -> x.selected }[0].correct) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Person Icon",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Person Icon",
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                        ResultElement(
                            a = a.question,
                            c = MaterialTheme.colorScheme.tertiary,
                            w = 80.dp,
                            h = 55.dp
                        )
                        ResultElement(
                            a = a.options[0].result.toString(),
                            c = if (a.options[0].correct) {
                                MaterialTheme.colorScheme.onPrimary
                            } else if (a.options[0].selected && !a.options[0].correct) {
                                MaterialTheme.colorScheme.onSecondary
                            } else {
                                MaterialTheme.colorScheme.tertiary
                            },
                            w = 40.dp
                        )
                        ResultElement(
                            a = a.options[1].result.toString(),
                            c = if (a.options[1].correct) {
                                MaterialTheme.colorScheme.onPrimary
                            } else if (a.options[1].selected && !a.options[1].correct) {
                                MaterialTheme.colorScheme.onSecondary
                            } else {
                                MaterialTheme.colorScheme.tertiary
                            },
                            w = 40.dp
                        )
                        ResultElement(
                            a = a.options[2].result.toString(),
                            c = if (a.options[2].correct) {
                                MaterialTheme.colorScheme.onPrimary
                            } else if (a.options[2].selected && !a.options[2].correct) {
                                MaterialTheme.colorScheme.onSecondary
                            } else {
                                MaterialTheme.colorScheme.tertiary
                            },
                            w = 40.dp
                        )
                        ResultElement(
                            a = a.options[3].result.toString(),
                            c = if (a.options[3].correct) {
                                MaterialTheme.colorScheme.onPrimary
                            } else if (a.options[3].selected && !a.options[3].correct) {
                                MaterialTheme.colorScheme.onSecondary
                            } else {
                                MaterialTheme.colorScheme.tertiary
                            },
                            w = 40.dp
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun ResultElement(a: String, c: Color, h: Dp = 40.dp, w: Dp) {
    Box(
        modifier = Modifier
            .height(h)
            .width(w)
            .background(
                c,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = a)
    }
}
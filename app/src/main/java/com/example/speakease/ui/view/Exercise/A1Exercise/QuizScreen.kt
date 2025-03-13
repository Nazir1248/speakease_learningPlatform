package com.example.speakease.ui.view.Exercise.A1Exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun QuizScreen(navController: NavHostController, exerciseNumber: Int?) {
    QuizApp(exerciseNumber = exerciseNumber)
}

@Composable
fun QuizApp(exerciseNumber: Int?) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    val totalQuestions = QuestionAnswer.question.size

    // Check if the quiz is finished
    val isQuizFinished = currentQuestionIndex >= totalQuestions

    if (isQuizFinished) {
        FinishQuiz(score, totalQuestions) {
            // Restart the quiz
            currentQuestionIndex = 0
            score = 0
            selectedAnswer = ""
        }
    } else {
        val question = QuestionAnswer.question[currentQuestionIndex]
        val choices = QuestionAnswer.choices[currentQuestionIndex]
        val correctAnswer = QuestionAnswer.correctAnswers[currentQuestionIndex]

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray) // Set background color for the entire Box
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp), // Add padding to the Column
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Exercise $exerciseNumber",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Total Questions: $totalQuestions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = question,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))

                // RadioButtons for choices
                choices.forEach { choice ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (selectedAnswer == choice),
                                onClick = { selectedAnswer = choice }
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (selectedAnswer == choice),
                            onClick = { selectedAnswer = choice },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Blue, // Color when selected
                                unselectedColor = Color.Gray, // Color when unselected
                                disabledSelectedColor = Color.LightGray, // Color when disabled & selected
                                disabledUnselectedColor = Color.LightGray // Color when disabled & unselected
                            )

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = choice,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        // Check if the selected answer is correct
                        if (selectedAnswer == correctAnswer) {
                            score++
                        }

                        // Move to the next question
                        if (currentQuestionIndex < totalQuestions - 1) {
                            currentQuestionIndex++
                            selectedAnswer = "" // Reset selected answer
                        } else {
                            // Quiz is finished
                            currentQuestionIndex = totalQuestions
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Composable
fun FinishQuiz(score: Int, totalQuestions: Int, onRestart: () -> Unit) {
    val passStatus = if (score > totalQuestions * 0.6) "Passed" else "Failed"
    val percentage = (score.toFloat() / totalQuestions.toFloat()) * 100

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = passStatus, color = Color.Black) },
        text = {
            Column {
                Text(text = "Correct Answers: $score out of $totalQuestions", color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Percentage: ${"%.2f".format(percentage)}%", color = Color.Black)
            }
        },
        confirmButton = {
            Button(onClick = onRestart) {
                Text(text = "Restart")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizApp() {
    QuizApp(exerciseNumber = 1)
}
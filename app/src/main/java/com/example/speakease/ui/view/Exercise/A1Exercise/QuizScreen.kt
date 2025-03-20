package com.example.speakease.ui.view.Exercise.A1Exercise

import Question
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speakease.R
import com.example.speakease.ui.viewmodel.QuizViewModel
import getQuestionById


@Composable
fun QuizApp(
    navController: NavHostController,
    exerciseNumber: Int?,
    quizViewModel: QuizViewModel
) {
    var selectedAnswer by remember { mutableStateOf("") }

    // Get the current question
    val question = getQuestionById(exerciseNumber ?: 1)

    // MediaPlayer for audio playback
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, question.audioResId) }

    // Clean up MediaPlayer when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

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
                text = "Question ${question.id}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Zoomable Image
            ZoomableImage(
                imageResId = question.imageResId,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )


            // RadioButtons for options
            question.options.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedAnswer == option),
                            onClick = { selectedAnswer = option }
                        )
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedAnswer == option),
                        onClick = { selectedAnswer = option },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Blue,
                            unselectedColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = option,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Audio Player
            Button(
                onClick = {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    } else {
                        mediaPlayer.start()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (mediaPlayer.isPlaying) "Pause Audio" else "Play Audio",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Next, Previous, and Submit Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Previous Button
                Button(
                    onClick = {
                        // Move to the previous question without updating the score
                        if (exerciseNumber != null && exerciseNumber > 1) {
                            navController.navigate("quizScreen/${exerciseNumber - 1}")
                        }
                    },
                    enabled = exerciseNumber != null && exerciseNumber > 1, // Disable on the first question
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Previous", fontSize = 16.sp)
                }

                // Next or Submit Button
                if (exerciseNumber != null && exerciseNumber < 6) {
                    // Next Button
                    Button(
                        onClick = {
                            // Update the score only if the selected answer is correct
                            if (selectedAnswer == question.correctAnswer) {
                                quizViewModel.incrementScore()
                            }

                            // Move to the next question
                            navController.navigate("quizScreen/${exerciseNumber + 1}")
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Next", fontSize = 16.sp)
                    }
                } else {
                    // Submit Button
                    Button(
                        onClick = {
                            // Update the score only if the selected answer is correct
                            if (selectedAnswer == question.correctAnswer) {
                                quizViewModel.incrementScore()
                            }

                            // Finish the quiz
                            navController.navigate("finishQuiz/${quizViewModel.score}")
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Submit", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FinishQuiz(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit
) {
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


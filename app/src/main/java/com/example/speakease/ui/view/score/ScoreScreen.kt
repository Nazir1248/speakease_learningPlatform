package com.example.speakease.ui.view.score

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.speakease.R


@Preview
@Composable
fun prevScoreScreen(){
    ScoreScreen(numOfQuestions = 12, numOfCorrectAns = 4)
}


@Composable
fun ScoreScreen(
    numOfQuestions: Int,
    numOfCorrectAns: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Close Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /* TODO: Handle close action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.account_box_24px),
                    contentDescription = "Close",
                    tint = colorResource(id = R.color.blue_grey)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Score Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Lottie Animation
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congra))
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(150.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Congrats Text
                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Score Percentage
                val scorePercentage = calculatePercentage(numOfCorrectAns, numOfQuestions)
                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Completion Message
                Text(
                    text = "Quiz completed successfully.",
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Attempt and Correct Answers
                val annotatedString = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Black)) {
                        append("You attempted ")
                    }
                    withStyle(SpanStyle(color = Color.Blue)) {
                        append("$numOfQuestions questions ")
                    }
                    withStyle(SpanStyle(color = Color.Black)) {
                        append("and from that ")
                    }
                    withStyle(SpanStyle(color = colorResource(id = R.color.green))) {
                        append("$numOfCorrectAns answers ")
                    }
                    withStyle(SpanStyle(color = Color.Black)) {
                        append("are correct.")
                    }
                }
                Text(
                    text = annotatedString,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// Utility function to calculate percentage
fun calculatePercentage(correct: Int, total: Int): String {
    require(total > 0) { "Total number of questions must be greater than zero." }
    val percentage = (correct.toDouble() / total.toDouble()) * 100
    return DecimalFormat("#.##").format(percentage)
}

//package com.example.speakease.ui.view.Exercise.A1Exercise
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//
//data class Exercise(
//    val number: Int,
//    val totalQuestions: Int
//)
//
//@Composable
//fun ExerciseListScreen(modifier: Modifier = Modifier, navController: NavHostController) {
//    val exercises = List(18) { index ->
//        Exercise(
//            number = index + 1,
//            totalQuestions = 10
//        )
//    }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        TopBar(title = "Akkusativ",navController=navController)
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(exercises) { exercise ->
//                ExerciseCard(exercise)
//            }
//        }
//    }
//}
//
//@Composable
//fun TopBar(title: String,navController: NavHostController) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFFFA500)) // Orange background
//            .padding(12.dp,25.dp,0.dp,10.dp),
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = { /* Handle back action */
//                navController.navigate("dashboard" )
//
//            }) {
//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = "Back",
//                    tint = Color.White
//                )
//            }
//            Text(
//                text = title,
//                color = Color.White,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}
//
//@Composable
//fun ExerciseCard(exercise: Exercise) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(50.dp)
//                    .background(Color(0xFF4CAF50)), // Green box
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "${exercise.number}",
//                    fontSize = 18.sp,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Column {
//                Text(
//                    text = "Exercise ${exercise.number}",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "${exercise.totalQuestions} Questions",
//                    fontSize = 12.sp,
//                    color = Color.Gray
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewExerciseList() {
//    val navController = rememberNavController()
//    ExerciseListScreen(navController = navController)
//}

//
//package com.example.speakease.ui.view.Exercise.A1Exercise
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.selection.selectable
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.speakease.ui.view.AdScreen.GoogleBannerAd
//
//data class Exercise(
//    val number: Int,
//    val totalQuestions: Int
//)
//
//@Composable
//fun ExerciseListScreen(modifier: Modifier = Modifier, navController: NavHostController) {
//    val exercises = List(18) { index ->
//        Exercise(
//            number = index + 1,
//            totalQuestions = 10
//        )
//    }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        TopBar(title = "Akkusativ", navController = navController)
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(exercises) { exercise ->
//                ExerciseCard(exercise, onClick = {
//                    navController.navigate("quizScreen/${exercise.number}") // Pass exercise number as argument
//                })
//            }
//        }
//        // Add the Google Ads banner at the bottom of the screen
//        GoogleBannerAd(modifier = Modifier.fillMaxWidth(), adId = "ca-app-pub-3940256099942544/9214589741")
//    }
//}
//
//@Composable
//fun TopBar(title: String, navController: NavHostController) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFFFA500)) // Orange background
//            .padding(12.dp, 25.dp, 0.dp, 10.dp),
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = {
//                navController.navigate("dashboard")
//            }) {
//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = "Back",
//                    tint = Color.White
//                )
//            }
//            Text(
//                text = title,
//                color = Color.White,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}
//
//@Composable
//fun ExerciseCard(exercise: Exercise, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable(onClick = onClick),
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(50.dp)
//                    .background(Color(0xFF4CAF50)), // Green box
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "${exercise.number}",
//                    fontSize = 18.sp,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Column {
//                Text(
//                    text = "Exercise ${exercise.number}",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "${exercise.totalQuestions} Questions",
//                    fontSize = 12.sp,
//                    color = Color.Gray
//                )
//            }
//        }
//    }
//}




package com.example.speakease.ui.view.Exercise.A1Exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speakease.ui.view.AdScreen.GoogleBannerAd

data class Exercise(
    val number: Int,
    val totalQuestions: Int
)

@Composable
fun ExerciseListScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val exercises = List(10) { index ->
        Exercise(
            number = index + 1,
            totalQuestions = 10
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        TopBar(title = "Akkusativ", navController = navController)

        // Exercise List
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(exercises) { exercise ->
                ExerciseCard(exercise, onClick = {
                    navController.navigate("quizScreen/${exercise.number}") // Pass exercise number as argument
                })
            }
        }

//        // Google Ads Banner at the bottom
//        GoogleBannerAd(modifier = Modifier.fillMaxWidth(), adId = "ca-app-pub-4910715387270743/3769947946")
//
        GoogleBannerAd(
            modifier = Modifier.fillMaxWidth(),
            adId = "ca-app-pub-3940256099942544/6300978111" // Your Ad Unit ID
        )
    }
}

@Composable
fun TopBar(title: String, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFA500)) // Orange background
            .padding(12.dp, 25.dp, 0.dp, 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate("dashboard")
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF4CAF50)), // Green box
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${exercise.number}",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Exercise ${exercise.number}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${exercise.totalQuestions} Questions",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseList() {
    val navController = rememberNavController()
    ExerciseListScreen(modifier = Modifier, navController = navController)
}
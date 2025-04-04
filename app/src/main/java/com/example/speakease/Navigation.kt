package com.example.speakease

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speakease.ui.view.Exercise.A1Exercise.ExerciseListScreen
import com.example.speakease.ui.view.Exercise.A1Exercise.FinishQuiz
import com.example.speakease.ui.view.Exercise.A1Exercise.QuizApp
import com.example.speakease.ui.view.MainDashboard.MainDashboardPage
import com.example.speakease.ui.view.dashboard.DashboardPage
import com.example.speakease.ui.view.home.HomePage
import com.example.speakease.ui.view.login.LoginPage
import com.example.speakease.ui.view.signup.SignUpPages
import com.example.speakease.ui.viewmodel.AuthViewModel
import com.example.speakease.ui.viewmodel.QuizViewModel

//
//
//@Composable
//fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "Login", builder = {
//        composable("Login") {
//            LoginPage(modifier, navController, authViewModel)
//        }
//        composable("signup") {
//            SignUpPages(modifier, navController, authViewModel)
//        }
//        composable("home") {
//            HomePage(modifier, navController, authViewModel)
//        }
//        composable("MainDashboard") {
//            MainDashboardPage(modifier, navController)
//        }
//        composable("dashboard") { // Add this route for DashboardPage
//            DashboardPage(modifier, navController)
//        }
//        composable("exerciseA1") { // Add this route for DashboardPage
//            ExerciseListScreen(modifier,navController)
//        }
//    })
//}


@Composable
fun MyAppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    quizViewModel: QuizViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login", builder = {
        composable("Login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignUpPages(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("MainDashboard") {
            MainDashboardPage(modifier, navController)
        }
        composable("dashboard") {
            DashboardPage(modifier, navController)
        }
        composable("exerciseA1") {
            ExerciseListScreen(modifier, navController)
        }
        composable("quizScreen/{exerciseNumber}") { backStackEntry ->
            val exerciseNumber = backStackEntry.arguments?.getString("exerciseNumber")?.toIntOrNull() ?: 0
            QuizApp(
                navController = navController,
                exerciseNumber = exerciseNumber,
                quizViewModel = quizViewModel
            )
        }
        composable("finishQuiz/{score}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            FinishQuiz(
                score = score,
                totalQuestions = 6, // Total number of questions
                onRestart = {
                    quizViewModel.resetScore() // Reset the score
                    navController.navigate("quizScreen/1") // Restart from the first question
                }
            )
        }
    })
}
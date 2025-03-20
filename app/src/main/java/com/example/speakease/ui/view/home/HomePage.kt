package com.example.speakease.ui.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speakease.ui.view.Exercise.A1Exercise.ExerciseListScreen
import com.example.speakease.ui.viewmodel.AuthState
import com.example.speakease.ui.viewmodel.AuthViewModel


@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavHostController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Unauthenticated -> { navController.navigate("Login") }
            is AuthState.Authenticated -> { navController.navigate("MainDashboard") }
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Page", fontSize = 32.sp)
        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign Out")
        }
    }
}



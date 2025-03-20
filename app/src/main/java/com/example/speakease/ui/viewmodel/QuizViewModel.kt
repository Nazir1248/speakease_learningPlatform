package com.example.speakease.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var score by mutableStateOf(0)
        private set

    fun incrementScore() {
        score++
    }

    fun resetScore() {
        score = 0
    }
}
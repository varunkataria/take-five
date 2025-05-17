package com.example.thejournal.ui.home

/**
 * Data class to represent the UI state of [HomeScreen]
 */
data class HomeUiState(
    val name: String = "",
    val isMorningCompleted: Boolean = false,
    val isEveningCompleted: Boolean = false
)
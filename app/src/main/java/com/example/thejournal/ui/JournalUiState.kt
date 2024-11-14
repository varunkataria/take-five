package com.example.thejournal.ui

import java.time.LocalDate

/**
 * Data class to represent the UI state of [JournalScreen]
 */
data class JournalUiState(
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false,
    val amazingThings: List<String> = listOf("", "", ""),
    val thingsToImprove: List<String> = listOf(""),
    val isToday: Boolean = false,
    val isLoading: Boolean = false
)

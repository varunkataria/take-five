package com.example.thejournal.ui

import java.time.LocalDate

/**
 * Data class to represent the UI state of [JournalScreen]
 */
data class JournalUiState(
    val isMorningEntry: Boolean,
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false,
    val gratefulThings: List<String> = listOf("", ""),
    val intentions: List<String> = listOf("", "", ""),
    val amazingThings: List<String> = listOf("", "", ""),
    val thingsToImprove: List<String> = listOf("", ""),
    val isToday: Boolean = false,
    val isLoading: Boolean = false
)

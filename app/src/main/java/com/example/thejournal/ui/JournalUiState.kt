package com.example.thejournal.ui

import java.time.LocalDate

data class JournalUiState(
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false,
    val amazingThings: List<String> = listOf("", "", ""),
    val thingsToImprove: List<String> = listOf(""),
    val isToday: Boolean = false,
    val isLoading: Boolean = false
)

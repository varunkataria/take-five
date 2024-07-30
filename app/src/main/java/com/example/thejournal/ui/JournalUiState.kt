package com.example.thejournal.ui

import java.util.Date

data class JournalUiState(
    val date: Date = Date(),
    val completed: Boolean = false,
    val amazingThings: List<String> = listOf("", "", ""),
    val thingsToImprove: List<String> = listOf(""),
    val isCurrentDayToday: Boolean = false,
    val isLoading: Boolean = false
)

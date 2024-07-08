package com.example.thejournal.ui

import java.util.Date

data class JournalUiState(
    val date: Date = Date(),
    val amazingThings: List<String> = listOf("", "", ""),
    val thingsToImprove: List<String> = listOf(""),
    val isLoading: Boolean = false
)

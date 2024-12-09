package com.example.thejournal.ui

import java.time.LocalDate

/**
 * Data class to represent the UI state of [ArchiveScreen]
 */
data class ArchiveUiState(
    val completedDates: List<LocalDate>? = emptyList()
)

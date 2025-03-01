package com.example.thejournal.ui

import com.example.thejournal.data.JournalEntry
import java.time.LocalDate

/**
 * Data class to represent the UI state of [ArchiveScreen]
 */
data class ArchiveUiState(
    val completedMorningEntryDates: List<LocalDate>? = emptyList(),
    val completedEveningEntryDates: List<LocalDate>? = emptyList(),
    val entries: List<JournalEntry> = emptyList(),
)

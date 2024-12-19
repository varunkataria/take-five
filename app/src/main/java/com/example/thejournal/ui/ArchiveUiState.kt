package com.example.thejournal.ui

import com.example.thejournal.data.JournalEntryWithDetails
import java.time.LocalDate

/**
 * Data class to represent the UI state of [ArchiveScreen]
 */
data class ArchiveUiState(
    val completedDates: List<LocalDate>? = emptyList(),
    val journalEntries: List<JournalEntryWithDetails> = emptyList()
)

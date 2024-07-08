package com.example.thejournal.domain

import com.example.thejournal.data.repository.JournalRepository
import java.util.Date
import javax.inject.Inject

/**
 * Use case for adding a new journal entry.
 */
class AddJournalEntryUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun execute(date: Date, amazingThings: List<String>, thingsToImprove: List<String>) {
        journalRepository.addJournalEntry(date, amazingThings, thingsToImprove)
    }
}

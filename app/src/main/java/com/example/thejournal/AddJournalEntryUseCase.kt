package com.example.thejournal

import javax.inject.Inject

/**
 * Use case for adding a new journal entry.
 */
class AddJournalEntryUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun execute(date: String, amazingThings: List<String>, thingsToImprove: List<String>) {
        journalRepository.addJournalEntry(date, amazingThings, thingsToImprove)
    }
}

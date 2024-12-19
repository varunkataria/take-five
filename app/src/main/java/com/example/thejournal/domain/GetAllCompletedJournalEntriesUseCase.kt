package com.example.thejournal.domain

import com.example.thejournal.data.JournalEntryWithDetails
import com.example.thejournal.data.repository.JournalRepository
import javax.inject.Inject

/**
 * Use case for retrieving the dates of completed journal entries.
 */
class GetAllCompletedJournalEntriesUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun execute(): List<JournalEntryWithDetails> {
        return journalRepository.getAllJournalEntries()
    }
}

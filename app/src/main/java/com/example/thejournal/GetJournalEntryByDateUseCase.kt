package com.example.thejournal

import com.example.thejournal.data.JournalEntryWithDetails
import java.util.Date
import javax.inject.Inject

/**
 * Use case for retrieving a journal entry by date.
 */
class GetJournalEntryByDateUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun execute(date: Date): JournalEntryWithDetails? {
        return journalRepository.getJournalEntryByDate(date)
    }
}

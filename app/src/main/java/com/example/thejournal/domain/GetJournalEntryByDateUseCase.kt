package com.example.thejournal.domain

import com.example.thejournal.data.repository.JournalRepository
import com.example.thejournal.data.JournalEntryWithDetails
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case for retrieving a journal entry by date.
 */
class GetJournalEntryByDateUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun execute(date: LocalDate): JournalEntryWithDetails? {
        return journalRepository.getJournalEntryByDate(date)
    }
}

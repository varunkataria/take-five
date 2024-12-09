package com.example.thejournal.domain

import com.example.thejournal.data.repository.JournalRepository
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

/**
 * Use case for retrieving a journal entry by date.
 */
class GetCompletedJournalEntryDatesUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun execute(yearMonth: YearMonth): List<LocalDate>? {
        val entries = journalRepository.getAllJournalEntries()
        val filteredEntries = entries.filter { entry ->
            entry.journalEntry.date.year == yearMonth.year && entry.journalEntry.date.month == yearMonth.month
        }
        // Return the dates as a list of LocalDate
        return filteredEntries.map { it.journalEntry.date }
    }
}

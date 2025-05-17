package com.example.thejournal.domain

import com.example.thejournal.data.JournalRepository
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case for adding a new morning or evening journal entry.
 */
class AddJournalEntryUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun addMorningEntry(date: LocalDate, intentions: List<String>, gratefulThings: List<String>) {
        journalRepository.addMorningEntry(date, intentions, gratefulThings)
    }

    suspend fun addEveningEntry(date: LocalDate, amazingThings: List<String>, thingsToImprove: List<String>) {
        journalRepository.addEveningEntry(date, amazingThings, thingsToImprove)
    }
}

package com.example.thejournal.domain

import com.example.thejournal.data.repository.JournalRepository
import com.example.thejournal.data.JournalEntryWithDetails
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case for retrieving a journal entry by date.
 */
class GetJournalEntryByDateUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    fun execute(date: LocalDate): Flow<JournalEntryWithDetails?> {
        return journalRepository.getJournalEntryByDate(date)
    }
}

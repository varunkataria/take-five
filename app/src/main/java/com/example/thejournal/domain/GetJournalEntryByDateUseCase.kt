package com.example.thejournal.domain

import com.example.thejournal.data.repository.JournalRepository
import com.example.thejournal.data.EveningEntry
import com.example.thejournal.data.MorningEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case for retrieving a morning or evening journal entry by date.
 */
class GetJournalEntryByDateUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    fun getMorningEntry(date: LocalDate): Flow<MorningEntry?> {
        return journalRepository.getMorningEntryByDate(date)
    }

    fun getEveningEntry(date: LocalDate): Flow<EveningEntry?> {
        return journalRepository.getEveningEntryByDate(date)
    }
}
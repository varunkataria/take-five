package com.example.takefive.domain

import com.example.takefive.data.JournalRepository
import com.example.takefive.data.EveningEntry
import com.example.takefive.data.MorningEntry
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
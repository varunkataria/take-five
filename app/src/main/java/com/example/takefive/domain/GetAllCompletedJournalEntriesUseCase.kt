package com.example.takefive.domain

import com.example.takefive.data.EveningEntry
import com.example.takefive.data.MorningEntry
import com.example.takefive.data.JournalRepository
import javax.inject.Inject

/**
 * Use case for retrieving all  completed journal entries.
 */
class GetAllCompletedJournalEntriesUseCase @Inject constructor(
    private val journalRepository: JournalRepository
) {
    suspend fun getMorningEntries(): List<MorningEntry> {
        return journalRepository.getAllMorningEntries()
    }
    suspend fun getEveningEntries(): List<EveningEntry> {
        return journalRepository.getAllEveningEntries()
    }
}

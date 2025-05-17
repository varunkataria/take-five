package com.example.thejournal.domain

import com.example.thejournal.data.EveningEntry
import com.example.thejournal.data.MorningEntry
import com.example.thejournal.data.JournalRepository
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

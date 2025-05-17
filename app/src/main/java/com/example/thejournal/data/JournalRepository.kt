package com.example.thejournal.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing journal entries. This repository abstracts the data layer
 * and provides a clean API for data access to the rest of the app.
 *
 * Because minimal logic is needed, injecting the DAO directly into the repository rather than DataSource
 *
 * @property journalEntryDao The DAO for accessing journal entries and related entities.
 */
@Singleton
class JournalRepository @Inject constructor(
    private val journalEntryDao: JournalEntryDao
) {
    /**
     * Insert a new morning journal entry along with its associated intentions and grateful things.
     */
    suspend fun addMorningEntry(
        date: LocalDate,
        intentions: List<String>,
        gratefulThings: List<String>
    ) {
        // Insert the journal entry & set completed to true for the given date
        val entryId = journalEntryDao.insertJournalEntry(
            EntryDetails(
                date = date,
                completed = true,
                entryType = EntryType.MORNING
            )
        )

        intentions.forEach { intention ->
            journalEntryDao.insertIntention(
                Intention(
                    journalEntryId = entryId.toInt(),
                    description = intention
                )
            )
        }

        gratefulThings.forEach { thing ->
            journalEntryDao.insertGratefulThing(
                GratefulThing(
                    journalEntryId = entryId.toInt(),
                    description = thing
                )
            )
        }
    }

    /**
     * Insert a new evening journal entry along with its associated amazing things and things to improve.
     */
    suspend fun addEveningEntry(
        date: LocalDate,
        amazingThings: List<String>,
        thingsToImprove: List<String>
    ) {
        // Insert the journal entry & set completed to true for the given date
        val entryId = journalEntryDao.insertJournalEntry(
            EntryDetails(
                date = date,
                completed = true,
                entryType = EntryType.EVENING
            )
        )

        amazingThings.forEach { thing ->
            journalEntryDao.insertAmazingThing(
                AmazingThing(
                    journalEntryId = entryId.toInt(),
                    description = thing
                )
            )
        }

        thingsToImprove.forEach { thing ->
            journalEntryDao.insertThingToImprove(
                ThingToImprove(
                    journalEntryId = entryId.toInt(),
                    description = thing
                )
            )
        }
    }

    /**
     * Retrieve a morning journal entry and its associated intentions and grateful things.
     *
     * @return A MorningEntry object containing the journal entry and related entities.
     */
    fun getMorningEntryByDate(date: LocalDate): Flow<MorningEntry?> {
        return journalEntryDao.getMorningEntryByDate(date)
    }

    /**
     * Retrieve an evening journal entry and its associated amazing things and things to improve by date.
     *
     * @return A EveningEntry object containing the journal entry and related entities.
     */
    fun getEveningEntryByDate(date: LocalDate): Flow<EveningEntry?> {
        return journalEntryDao.getEveningEntryByDate(date)
    }

    /**
     * Retrieve all completed morning journal entries.
     *
     * @return A list containing all completed morning journal entries
     */
    suspend fun getAllMorningEntries(): List<MorningEntry> {
        return journalEntryDao.getMorningEntries()
    }

    /**
     * Retrieve all completed evening journal entries.
     *
     * @return A list containing all completed evening journal entries
     */
    suspend fun getAllEveningEntries(): List<EveningEntry> {
        return journalEntryDao.getEveningEntries()
    }
}

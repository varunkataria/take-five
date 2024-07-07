package com.example.thejournal

import com.example.thejournal.data.AmazingThing
import com.example.thejournal.data.JournalEntry
import com.example.thejournal.data.JournalEntryDao
import com.example.thejournal.data.JournalEntryWithDetails
import com.example.thejournal.data.ThingToImprove
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing journal entries. This repository abstracts the data layer
 * and provides a clean API for data access to the rest of the app.
 *
 * Because minimal logic is needed, you injecting the DAO directly into the repository rather than DataSource
 *
 * @property journalEntryDao The DAO for accessing journal entries and related entities.
 */
@Singleton
class JournalRepository @Inject constructor(
    private val journalEntryDao: JournalEntryDao
) {

    /**
     * Insert a new journal entry along with its associated amazing things and things to improve.
     */
    suspend fun addJournalEntry(date: String, amazingThings: List<String>, thingsToImprove: List<String>) {
        val entryId = journalEntryDao.insertJournalEntry(JournalEntry(date = date))

        amazingThings.forEach { thing ->
            journalEntryDao.insertAmazingThing(AmazingThing(journalEntryId = entryId.toInt(), description = thing))
        }

        thingsToImprove.forEach { thing ->
            journalEntryDao.insertThingToImprove(ThingToImprove(journalEntryId = entryId.toInt(), description = thing))
        }
    }

    /**
     * Retrieve a journal entry and its associated amazing things and things to improve by date.
     *
     * @return A JournalEntryWithDetails object containing the journal entry and related entities.
     */
    suspend fun getJournalEntryByDate(date: String): JournalEntryWithDetails? {
        return journalEntryDao.getEntryByDate(date)
    }
}

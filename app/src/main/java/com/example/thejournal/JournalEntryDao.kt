package com.example.thejournal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.thejournal.model.AmazingThing
import com.example.thejournal.model.JournalEntry
import com.example.thejournal.model.JournalEntryWithDetails
import com.example.thejournal.model.ThingToImprove

@Dao
interface JournalEntryDao {

    @Insert
    suspend fun insertJournalEntry(entry: JournalEntry): Long

    @Insert
    suspend fun insertAmazingThing(amazingThing: AmazingThing)

    @Insert
    suspend fun insertThingToImprove(thingToImprove: ThingToImprove)

    @Transaction
    @Query("SELECT * FROM journal_entries WHERE date = :date")
    suspend fun getEntryByDate(date: String): JournalEntryWithDetails?

    @Transaction
    @Query("SELECT * FROM journal_entries")
    suspend fun getAllEntries(): List<JournalEntryWithDetails>
}

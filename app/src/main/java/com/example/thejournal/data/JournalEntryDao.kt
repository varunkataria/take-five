package com.example.thejournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import java.util.Date

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
    @TypeConverters(Converters::class)
    suspend fun getEntryByDate(date: Date): JournalEntryWithDetails?
}

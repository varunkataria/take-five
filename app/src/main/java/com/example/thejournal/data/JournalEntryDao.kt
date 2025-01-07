package com.example.thejournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

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
    fun getEntryByDate(date: LocalDate): Flow<JournalEntryWithDetails?>

    @Transaction
    @Query("SELECT * FROM journal_entries")
    @TypeConverters(Converters::class)
    suspend fun getAllJournalEntries(): List<JournalEntryWithDetails>
}

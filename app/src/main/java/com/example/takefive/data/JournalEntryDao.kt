package com.example.takefive.data

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
    suspend fun insertJournalEntry(entry: EntryDetails): Long

    @Insert
    suspend fun insertAmazingThing(amazingThing: AmazingThing)

    @Insert
    suspend fun insertThingToImprove(thingToImprove: ThingToImprove)

    @Insert
    suspend fun insertIntention(intention: Intention)

    @Insert
    suspend fun insertGratefulThing(gratefulThing: GratefulThing)

    @Transaction
    @Query("SELECT * FROM journal_entries WHERE date = :date AND entryType = 'MORNING'")
    @TypeConverters(Converters::class)
    fun getMorningEntryByDate(date: LocalDate): Flow<MorningEntry?>

    @Transaction
    @Query("SELECT * FROM journal_entries WHERE date = :date AND entryType = 'EVENING'")
    @TypeConverters(Converters::class)
    fun getEveningEntryByDate(date: LocalDate): Flow<EveningEntry?>

    @Transaction
    @Query("SELECT * FROM journal_entries WHERE entryType = 'MORNING'")
    suspend fun getMorningEntries(): List<MorningEntry>

    @Transaction
    @Query("SELECT * FROM journal_entries WHERE entryType = 'EVENING'")
    suspend fun getEveningEntries(): List<EveningEntry>
}

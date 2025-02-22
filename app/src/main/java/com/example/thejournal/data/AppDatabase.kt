package com.example.thejournal.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [JournalEntry::class, AmazingThing::class, ThingToImprove::class, GratefulThing::class, Intention::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
}

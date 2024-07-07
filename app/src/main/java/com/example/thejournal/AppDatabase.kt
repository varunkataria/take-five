package com.example.thejournal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thejournal.model.AmazingThing
import com.example.thejournal.model.JournalEntry
import com.example.thejournal.model.ThingToImprove

@Database(entities = [JournalEntry::class, AmazingThing::class, ThingToImprove::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
}

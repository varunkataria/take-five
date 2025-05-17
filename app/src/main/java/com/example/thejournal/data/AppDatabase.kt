package com.example.thejournal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thejournal.data.user.UserDetails
import com.example.thejournal.data.user.UserDetailsDao

@Database(
    entities = [
        EntryDetails::class,
        AmazingThing::class,
        ThingToImprove::class,
        GratefulThing::class,
        Intention::class,
        UserDetails::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
    abstract fun userDetailsDao(): UserDetailsDao
}

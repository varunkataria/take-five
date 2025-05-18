package com.example.takefive

import android.app.Application
import androidx.room.Room
import com.example.takefive.data.AppDatabase
import com.example.takefive.data.JournalEntryDao
import com.example.takefive.data.user.UserDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@HiltAndroidApp
class JournalApp : Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "journal_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideJournalEntryDao(db: AppDatabase): JournalEntryDao = db.journalEntryDao()

    @Provides
    fun provideUserDetailsDao(db: AppDatabase): UserDetailsDao = db.userDetailsDao()
}
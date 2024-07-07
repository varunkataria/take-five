package com.example.thejournal.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing a journal entry with it's associated details.
 *
 * @property journalEntry The journal entry entity.
 * @property amazingThings List of associated amazing things.
 * @property thingsToImprove List of associated things to improve.
 */
data class JournalEntryWithDetails(
    @Embedded val journalEntry: JournalEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "journalEntryId"
    )
    val amazingThings: List<AmazingThing>,
    @Relation(
        parentColumn = "id",
        entityColumn = "journalEntryId"
    )
    val thingsToImprove: List<ThingToImprove>
)

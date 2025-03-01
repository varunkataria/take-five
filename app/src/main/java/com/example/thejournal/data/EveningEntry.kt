package com.example.thejournal.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing an evening journal entry with it's associated details.
 *
 * @property details The journal entry details entity.
 * @property amazingThings List of associated amazing things.
 * @property thingsToImprove List of associated things to improve.
 */
data class EveningEntry(
    @Embedded override val details: EntryDetails,
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
) : JournalEntry

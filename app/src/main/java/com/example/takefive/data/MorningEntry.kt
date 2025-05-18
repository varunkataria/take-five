package com.example.takefive.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing a journal entry with it's associated details.
 *
 * @property details The journal entry entity.
 * @property gratefulThings List of associated things you're grateful for.
 * @property intentions List of intentions for the day.
 */
data class MorningEntry(
    @Embedded override val details: EntryDetails,
    @Relation(
        parentColumn = "id",
        entityColumn = "journalEntryId"
    )
    val gratefulThings: List<GratefulThing>,
    @Relation(
        parentColumn = "id",
        entityColumn = "journalEntryId"
    )
    val intentions: List<Intention>
) : JournalEntry
package com.example.takefive.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Entity class representing something you're grateful for that day recorded in a journal entry.
 *
 * @property id Primary key auto-generated by Room.
 * @property journalEntryId Foreign key referencing the associated JournalEntry.
 * @property description Description of one thing you're grateful for that day.
 */
@Entity(
    tableName = "grateful_things",
    foreignKeys = [ForeignKey(
        entity = EntryDetails::class, // Parent table
        parentColumns = ["id"],       // Column(s) in the parent table
        childColumns = ["journalEntryId"], // Column(s) in the child table
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("journalEntryId")]
)
data class GratefulThing(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    override val journalEntryId: Int,
    override val description: String
) : SubmissionItem
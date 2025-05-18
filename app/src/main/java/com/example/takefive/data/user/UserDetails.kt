package com.example.takefive.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing user details.
 *
 * @property id Primary key for the user (assuming a single user, this can default to 1).
 * @property name The name of the user.
 */
@Entity(tableName = "user_details")
data class UserDetails(
    @PrimaryKey val id: Int = 1, // Assuming a single user
    val name: String
)
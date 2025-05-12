package com.example.thejournal.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing user details.
 *
 * @property userDetailsDao The DAO for accessing user details.
 */
@Singleton
class UserDetailsRepository @Inject constructor(
    private val userDetailsDao: UserDetailsDao
) {
    /**
     * Update user details into the database.
     */
    suspend fun updateUserDetails(newName: String) {
        userDetailsDao.updateUserDetails(UserDetails(name = newName))
    }

    /**
     * Retrieve user details as a Flow.
     */
    fun getUserDetails(userId: Int = 1): Flow<UserDetails?> {
        return userDetailsDao.getUserDetails(userId)
    }
}
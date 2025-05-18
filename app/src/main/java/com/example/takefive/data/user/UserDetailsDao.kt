package com.example.takefive.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserDetails(userDetails: UserDetails)

    @Query("SELECT * FROM user_details WHERE id = :userId")
    fun getUserDetails(userId: Int = 1): Flow<UserDetails?>
}
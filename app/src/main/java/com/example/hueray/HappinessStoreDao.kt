package com.example.hueray

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HappinessStoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: HappinessStore)

    @Query("DELETE FROM happy_table")
    suspend fun deleteAll()
}

package com.example.hueray

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HappinessScoreDao {

    @Query("SELECT * from happy_table ORDER BY score ASC")
    fun getAll(): List<HappinessScore>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg score: HappinessScore)

    @Query("DELETE FROM happy_table")
    suspend fun deleteAll()
}

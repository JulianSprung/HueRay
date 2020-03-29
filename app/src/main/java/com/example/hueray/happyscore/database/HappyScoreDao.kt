package com.example.hueray.happyscore.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HappyScoreDao {

    @Query("SELECT * from happyscore")
    fun getAll(): LiveData<List<HappyScore>>

    @Insert
    suspend fun insert(score: HappyScore)

    @Query("DELETE FROM happyscore")
    suspend fun deleteAll()
}

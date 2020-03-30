package com.example.hueray.happyscore.repository

import androidx.lifecycle.LiveData
import com.example.hueray.happyscore.database.HappyScore
import com.example.hueray.happyscore.database.HappyScoreDao


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class HueRayRepository(private val HappyScoreDao: HappyScoreDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allScores: LiveData<List<HappyScore>> = HappyScoreDao.getAll()
    val lastTenScores: LiveData<List<HappyScore>> = HappyScoreDao.getLastTen()

    suspend fun insert(score: HappyScore) {
        HappyScoreDao.insert(score)
    }
}
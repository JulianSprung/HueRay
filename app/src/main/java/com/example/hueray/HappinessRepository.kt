package com.example.hueray

import androidx.lifecycle.LiveData

class HappinessRepository(private val HappinessStoreDao: HappinessScoreDao) {


    val allScores: LiveData<List<HappinessScore>> = HappinessStoreDao.getSortedHappinesScore()
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    suspend fun insert(score: HappinessScore) {
        HappinessStoreDao.insert(score)
    }
}
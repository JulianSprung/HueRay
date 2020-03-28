package com.example.hueray

class HappinessRepository(private val HappinessStoreDao: HappinessStoreDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    suspend fun insert(score: HappinessStore) {
        HappinessStoreDao.insert(score)
    }
}
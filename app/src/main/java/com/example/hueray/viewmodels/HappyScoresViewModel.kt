package com.example.hueray.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hueray.happyscore.database.HappyScore
import com.example.hueray.happyscore.database.HappyScoreDatabase
import com.example.hueray.happyscore.repository.HueRayRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class HappyScoresViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: HueRayRepository
    // LiveData gives us updated HappyScores when they change.
    val allHappyScores: LiveData<List<HappyScore>>
    val lastTenScores: LiveData<List<HappyScore>>

    init {
        // Gets reference to HappyScoreDao from HappyScoreRoomDatabase to construct
        // the correct HappyScoreRepository.
        val HappyScoresDao = HappyScoreDatabase.getDatabase(application).HappyScoreDao()
        repository = HueRayRepository(HappyScoresDao)
        allHappyScores = repository.allScores
        lastTenScores = repository.lastTenScores
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(score: HappyScore) = viewModelScope.launch {
        repository.insert(score)
    }
}
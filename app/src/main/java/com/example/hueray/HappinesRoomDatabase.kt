package com.example.hueray

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(HappinessScore::class), version = 1, exportSchema = false)
public abstract class HappinessRoomDatabase : RoomDatabase() {

    abstract fun HappinessStoreDao(): HappinessScoreDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: HappinessRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): HappinessRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HappinessRoomDatabase::class.java,
                    "happy_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
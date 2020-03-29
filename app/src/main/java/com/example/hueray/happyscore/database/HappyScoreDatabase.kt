package com.example.architextureexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hueray.happyscore.database.HappyScore
import com.example.hueray.happyscore.database.HappyScoreDao

// Annotates class to be a Room Database with a table (entity) of the HappyScore class
@Database(entities = arrayOf(HappyScore::class), version = 1, exportSchema = true)
public abstract class HappyScoreDatabase : RoomDatabase() {

    abstract fun HappyScoreDao(): HappyScoreDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: HappyScoreDatabase? = null

        fun getDatabase(context: Context): HappyScoreDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HappyScoreDatabase::class.java,
                    "happyscore_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
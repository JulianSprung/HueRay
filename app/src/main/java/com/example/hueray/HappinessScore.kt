package com.example.hueray

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//
//data class HappyStore(val score:Int)

@Entity(tableName ="happy_table")
class HappinessScore(@PrimaryKey @ColumnInfo(name="score") val score: Int)


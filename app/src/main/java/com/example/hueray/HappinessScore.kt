package com.example.hueray

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

//
//data class HappyStore(val score:Int)

@Entity(tableName ="happy_table")
data class HappinessScore(

    @PrimaryKey(autoGenerate = false)
    var timestamp: String,

    @ColumnInfo(name = "score")
    var score: Int


)
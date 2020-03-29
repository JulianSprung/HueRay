package com.example.hueray.happyscore.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "happyscore")
data class HappyScore(
    @PrimaryKey(autoGenerate = false)
    var timestamp: String,

    @ColumnInfo(name = "score")
    var score: Int
)
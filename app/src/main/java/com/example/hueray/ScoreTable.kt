package com.example.hueray

import androidx.lifecycle.LiveData
import androidx.room.*


@Entity
data class Score(
    @PrimaryKey var Int: Int,
    @ColumnInfo(name = "value") var value: Int
)

@Entity(tableName = "scoretable")
data class ScoreTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "score") var score: Int
)

@Dao
interface Scores {
    @Query("SELECT * FROM scoretable")
    fun getAll(): LiveData<List<ScoreTable>>

    @Insert
    fun insertAll(vararg todo: ScoreTable)

    @Delete
    fun delete(todo: ScoreTable)

    @Update
    fun updateTodo(vararg todos: ScoreTable)

}

@Database(entities = arrayOf(ScoreTable::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoresDao(): Scores
}
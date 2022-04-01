package com.tekun.quizzapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tekun.quizzapp.data.database.entities.QuizEntity

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_table")
    suspend fun getAllQuiz(): List<QuizEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quiz: List<QuizEntity>)

    @Query("DELETE FROM quiz_table")
    suspend fun deleteAllQuizzies()
}
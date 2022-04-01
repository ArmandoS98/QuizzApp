package com.tekun.quizzapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tekun.quizzapp.domain.QuizItem

@Entity(tableName = "quiz_table")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "options") val options: String,
    @ColumnInfo(name = "answer") val answer: Int
)

fun QuizItem.toDatabase() =
    QuizEntity(question = question, imageUrl = imageUrl, options = options, answer = answer)

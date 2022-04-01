package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.database.entities.QuizEntity
import com.tekun.quizzapp.data.model.QuizModel

data class QuizItem(
    val question: String,
    val imageUrl: String,
    val options: String,
    val answer: Int
)

fun QuizModel.toDomain() = QuizItem(question, imageUrl, options, answer)
fun QuizEntity.toDomain() = QuizItem(question, imageUrl, options, answer)
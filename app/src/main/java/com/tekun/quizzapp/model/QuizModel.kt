package com.tekun.quizzapp.model

data class QuizModel(
    val question: String,
    val imageUrl: String,
    val options: String,
    val answer: Int
)

package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.QuizRepository
import com.tekun.quizzapp.data.model.QuizModel

class GetQuizUseCase {
    private val repository = QuizRepository()

    suspend operator fun invoke(): List<QuizModel>? = repository.getAllQuizz()
}
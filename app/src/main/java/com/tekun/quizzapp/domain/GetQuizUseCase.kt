package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.QuizRepository
import com.tekun.quizzapp.data.model.QuizModel
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(): List<QuizModel>? = repository.getAllQuizz()
}
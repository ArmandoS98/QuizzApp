package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.QuizRepository
import com.tekun.quizzapp.data.database.entities.toDatabase
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(): List<QuizItem> {
        val quizzes = repository.getAllQuizzFromApi()

        return if (quizzes.isNotEmpty()) {
            repository.clearQuizzes()
            repository.insertQuizzes(quizzes.map { it.toDatabase() })
            quizzes
        } else {
            repository.getAllQuizzFromDatabase()
        }
    }
}
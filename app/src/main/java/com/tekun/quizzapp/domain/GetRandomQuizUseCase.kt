package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.QuizRepository
import javax.inject.Inject

class GetRandomQuizUseCase @Inject constructor(private val repository: QuizRepository) {

   suspend operator fun invoke(): QuizItem? {
        val quizz = repository.getAllQuizzFromDatabase()
        if (!quizz.isNullOrEmpty()) {
            val randomNumber = (quizz.indices).random()
            return quizz[randomNumber]
        }
        return null
    }
}
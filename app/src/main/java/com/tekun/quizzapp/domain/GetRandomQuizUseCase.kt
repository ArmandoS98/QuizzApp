package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.QuizRepository
import com.tekun.quizzapp.data.model.QuizModel
import com.tekun.quizzapp.data.model.QuizProvider

class GetRandomQuizUseCase {

    operator fun invoke(): QuizModel? {
        val quizz = QuizProvider.quizzes
        if (!quizz.isNullOrEmpty()) {
            val randomNumber = (quizz.indices).random()
            return quizz[randomNumber]
        }
        return null
    }
}
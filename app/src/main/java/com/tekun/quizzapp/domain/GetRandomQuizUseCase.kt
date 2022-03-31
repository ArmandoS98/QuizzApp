package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.model.QuizModel
import com.tekun.quizzapp.data.model.QuizProvider
import javax.inject.Inject

class GetRandomQuizUseCase @Inject constructor(private val quizProvider: QuizProvider) {

    operator fun invoke(): QuizModel? {
        val quizz = quizProvider.quizzes
        if (!quizz.isNullOrEmpty()) {
            val randomNumber = (quizz.indices).random()
            return quizz[randomNumber]
        }
        return null
    }
}
package com.tekun.quizzapp.data

import com.tekun.quizzapp.data.model.QuizModel
import com.tekun.quizzapp.data.model.QuizProvider
import com.tekun.quizzapp.data.network.QuizService

class QuizRepository {
    private val api = QuizService()

    suspend fun getAllQuizz(): List<QuizModel> {
        val response = api.getQuiz()
        QuizProvider.quizzes = response
        return response
    }
}
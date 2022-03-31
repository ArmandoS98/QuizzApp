package com.tekun.quizzapp.data

import com.tekun.quizzapp.data.model.QuizModel
import com.tekun.quizzapp.data.model.QuizProvider
import com.tekun.quizzapp.data.network.QuizService
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val api: QuizService,
    private val quizProvider: QuizProvider
) {

    suspend fun getAllQuizz(): List<QuizModel> {
        val response = api.getQuiz()
        quizProvider.quizzes = response
        return response
    }
}
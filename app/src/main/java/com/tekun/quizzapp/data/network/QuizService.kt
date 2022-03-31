package com.tekun.quizzapp.data.network

import com.tekun.quizzapp.data.model.QuizModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizService @Inject constructor(private val api: QuizApiClient) {
    suspend fun getQuiz(): List<QuizModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuiz()
            response.body() ?: emptyList()
        }
    }
}
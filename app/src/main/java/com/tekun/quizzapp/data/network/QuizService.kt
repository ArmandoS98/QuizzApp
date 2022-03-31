package com.tekun.quizzapp.data.network

import com.tekun.quizzapp.core.RetrofitHelper
import com.tekun.quizzapp.data.model.QuizModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getQuiz(): List<QuizModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuizApiClient::class.java).getAllQuiz()
            response.body() ?: emptyList()
        }
    }
}
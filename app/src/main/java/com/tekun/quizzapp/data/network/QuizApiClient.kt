package com.tekun.quizzapp.data.network

import com.tekun.quizzapp.data.model.QuizModel
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiClient {
    @GET("/.json")
    suspend fun getAllQuiz(): Response<List<QuizModel>>
}
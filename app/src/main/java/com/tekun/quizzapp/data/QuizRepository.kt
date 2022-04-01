package com.tekun.quizzapp.data

import com.tekun.quizzapp.data.database.dao.QuizDao
import com.tekun.quizzapp.data.database.entities.QuizEntity
import com.tekun.quizzapp.data.network.QuizService
import com.tekun.quizzapp.domain.QuizItem
import com.tekun.quizzapp.domain.toDomain
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val api: QuizService,
    private val quizDao: QuizDao
) {

    suspend fun getAllQuizzFromApi(): List<QuizItem> {
        val response = api.getQuiz()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuizzFromDatabase(): List<QuizItem> {
        val response = quizDao.getAllQuiz()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuizzes(quizzes: List<QuizEntity>) {
        quizDao.insertAll(quizzes)
    }

    suspend fun clearQuizzes() {
        quizDao.deleteAllQuizzies()
    }
}
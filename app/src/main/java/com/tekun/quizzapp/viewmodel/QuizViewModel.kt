package com.tekun.quizzapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tekun.quizzapp.model.QuizModel
import com.tekun.quizzapp.model.QuizProvider

class QuizViewModel : ViewModel() {
    val quizModel = MutableLiveData<QuizModel>()

    fun randomQuestion() {
        val currentQuestion = QuizProvider.random()
        quizModel.postValue(currentQuestion)
    }

    fun onCreate() {
        randomQuestion()
    }
}
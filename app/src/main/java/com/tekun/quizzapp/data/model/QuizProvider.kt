package com.tekun.quizzapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizProvider @Inject constructor() {
    var quizzes: List<QuizModel> = emptyList()
}
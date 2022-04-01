package com.tekun.quizzapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekun.quizzapp.domain.GetQuizUseCase
import com.tekun.quizzapp.domain.GetRandomQuizUseCase
import com.tekun.quizzapp.domain.QuizItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase,
    private val getRandomQuizUseCase: GetRandomQuizUseCase
) : ViewModel() {
    val quizModel = MutableLiveData<QuizItem>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            val result = getQuizUseCase()
            if (!result.isNullOrEmpty()) {
                quizModel.postValue(result[0])
            }
        }
    }

    fun randomQuestion() {
        viewModelScope.launch {
            val quizz = getRandomQuizUseCase()
            if (quizz != null)
                quizModel.postValue(quizz)
        }
    }
}
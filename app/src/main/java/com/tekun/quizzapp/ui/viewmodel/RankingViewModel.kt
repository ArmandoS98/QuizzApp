package com.tekun.quizzapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekun.quizzapp.domain.GetRankingUseCase
import com.tekun.quizzapp.domain.InsertRankingUseCase
import com.tekun.quizzapp.domain.RankingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val getRankingUseCase: GetRankingUseCase,
    private val insertRankingUseCase: InsertRankingUseCase
) : ViewModel() {
    val rankingModel = MutableLiveData<List<RankingItem>>()
    val insertWinnerModel = MutableLiveData<Long>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            val result = getRankingUseCase()
            if (!result.isNullOrEmpty()) {
                rankingModel.postValue(result)
            } else {
                rankingModel.postValue(emptyList())
            }
        }
    }

    fun insertWinner(winner: RankingItem) {
        viewModelScope.launch {
            val result = insertRankingUseCase.invoke(winner)
            insertWinnerModel.postValue(result!!)
        }
    }
}
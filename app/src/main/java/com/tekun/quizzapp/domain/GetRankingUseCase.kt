package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.RankingRepository
import javax.inject.Inject

class GetRankingUseCase @Inject constructor(private val repository: RankingRepository) {
    suspend operator fun invoke(): List<RankingItem>? {
        val ranking = repository.getRanking()
        if (!ranking.isNullOrEmpty()) {
            return ranking
        }
        return null
    }
}
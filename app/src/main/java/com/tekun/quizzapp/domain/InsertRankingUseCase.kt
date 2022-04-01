package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.RankingRepository
import com.tekun.quizzapp.data.database.entities.toDatabase
import javax.inject.Inject

class InsertRankingUseCase @Inject constructor(private val repository: RankingRepository) {
    suspend operator fun invoke(winner: RankingItem): Long? {
        val insert = repository.insertWinner(winner.toDatabase())
        return if (insert > 0) {
            insert
        } else
            -1
    }
}
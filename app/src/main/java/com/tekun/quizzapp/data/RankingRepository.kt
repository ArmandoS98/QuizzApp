package com.tekun.quizzapp.data

import com.tekun.quizzapp.data.database.dao.RankingDao
import com.tekun.quizzapp.data.database.entities.RankingEntity
import com.tekun.quizzapp.domain.RankingItem
import com.tekun.quizzapp.domain.toDomain
import javax.inject.Inject

class RankingRepository @Inject constructor(private val ranking: RankingDao) {
    suspend fun getRanking(): List<RankingItem> {
        val response = ranking.getRanking()
        return response.map { it.toDomain() }
    }

    suspend fun insertWinner(winner: RankingEntity): Long {
        return ranking.insertWinner(winner)
    }

    suspend fun clearQuizzes() {
        ranking.deleteRanking()
    }
}
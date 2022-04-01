package com.tekun.quizzapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tekun.quizzapp.data.database.entities.RankingEntity

@Dao
interface RankingDao {
    @Query("SELECT * FROM ranking_table")
    suspend fun getRanking(): List<RankingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWinner(winner: RankingEntity): Long

    @Query("DELETE FROM ranking_table")
    suspend fun deleteRanking()
}
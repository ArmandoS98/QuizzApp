package com.tekun.quizzapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tekun.quizzapp.data.database.dao.QuizDao
import com.tekun.quizzapp.data.database.dao.RankingDao

import com.tekun.quizzapp.data.database.entities.QuizEntity
import com.tekun.quizzapp.data.database.entities.RankingEntity

@Database(entities = [QuizEntity::class, RankingEntity::class], version = 2)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun getQuizDao(): QuizDao
    abstract fun getRankingDao(): RankingDao
}
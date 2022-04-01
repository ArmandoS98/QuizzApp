package com.tekun.quizzapp.di

import android.content.Context
import androidx.room.Room
import com.tekun.quizzapp.data.database.QuizDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val QUIZ_DATABASE_NAME = "quiz_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuizDatabase::class.java, QUIZ_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuizDao(db: QuizDatabase) = db.getQuizDao()

    @Singleton
    @Provides
    fun provideRankingDao(db: QuizDatabase) = db.getRankingDao()
}
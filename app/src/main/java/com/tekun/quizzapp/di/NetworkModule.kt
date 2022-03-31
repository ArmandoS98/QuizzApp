package com.tekun.quizzapp.di

import com.tekun.quizzapp.data.network.QuizApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://quizzapp-2172a-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Quiz
    @Singleton
    @Provides
    fun provideQuizApiClient(retrofit: Retrofit): QuizApiClient {
        return retrofit.create(QuizApiClient::class.java)
    }
}
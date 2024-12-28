package com.m5.counter.app

import android.app.Application
import androidx.room.Room
import com.m5.counter.data.local.HistoryDao
import com.m5.counter.data.local.HistoryDatabase
import com.m5.counter.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHistoryDatabase(application: Application): HistoryDatabase {
        return Room.databaseBuilder(application, HistoryDatabase::class.java, "history_database").allowMainThreadQueries().build()
    }


    @Provides
    @Singleton
    fun provideHistoryDao(historyDatabase: HistoryDatabase): HistoryDao {
        return historyDatabase.historyDao()
    }



    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://love-calculator.p.rapidapi.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }



}
package com.example.quranoffline.di

import android.app.Application
import com.example.quranoffline.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule : Application() {

    @Provides
    @Singleton
    fun provideApi(): RetrofitInstance {
        return RetrofitInstance
    }
}
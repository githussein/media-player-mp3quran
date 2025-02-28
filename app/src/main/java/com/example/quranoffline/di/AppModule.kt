package com.example.quranoffline.di

import android.app.Application
import com.example.quranoffline.data.Mp3QuranService
import com.example.quranoffline.data.QuranApiService
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
    fun provideMp3Api(): Mp3QuranService {
        return Mp3QuranService
    }

    @Provides
    @Singleton
    fun provideQuranApi(): QuranApiService {
        return QuranApiService
    }
}
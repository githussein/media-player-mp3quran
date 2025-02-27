package com.example.quranoffline.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class QSurahResponse(val surah: List<QSurah>)

@Module
@InstallIn(SingletonComponent::class)
object QuranApiService {
    val api: QuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl("quranapi.pages.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuranApiService::class.java)
    }

    interface QuranApiService {
        @GET("surah.json")
        suspend fun getQSurahs(): QSurahResponse
    }
}

data class QSurah(
    val surahName: String,
    val surahNameArabic: String,
    val surahNameArabicLong: String,
    val surahNameTranslation: String,
    val revelationPlace: String,
    val totalAyah: Int
)

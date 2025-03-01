package com.example.quranoffline.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class ChaptersResponse(val chaptersList: List<QSurah>)
data class ChapterScriptResponse(val chapterScript: ChapterScriptModel)

@Module
@InstallIn(SingletonComponent::class)
object QuranApiService {
    val api: QuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://quranapi.pages.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuranApiService::class.java)
    }

    interface QuranApiService {
        @GET("surah.json")
        suspend fun getQSurahs(): List<QSurah>

        @GET("{surahNo}.json")
        suspend fun getChapterScript(@Path("surahNo") surahNo: String): ChapterScriptModel
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

data class ChapterScriptModel(
    val surahName: String,
    val surahNameArabic: String,
    val surahNameArabicLong: String,
    val surahNameTranslation: String,
    val revelationPlace: String,
    val totalAyah: Int,
    val surahNo: Int,
    val english: List<String>,
    val arabic1: List<String>,
    val arabic2: List<String>
)
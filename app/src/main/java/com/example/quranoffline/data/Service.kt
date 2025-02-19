package com.example.quranoffline.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class ReciterResponse(val reciters: List<Reciter>)
data class SurahResponse(val suwar: List<Surah>)


@Module
@InstallIn(SingletonComponent::class)
object RecitersApi {
    val api: ReciterApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.mp3quran.net/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReciterApiService::class.java)
    }

    interface ReciterApiService {
        @GET("reciters")
        suspend fun getAllReciters(
            @Query("language") language: String = "eng"
        ): ReciterResponse

        @GET("reciters")
        suspend fun getReciterById(
            @Query("language") language: String = "eng",
            @Query("reciter") reciterId: String
        ): ReciterResponse

        @GET("suwar")
        suspend fun getSurahName(
            @Query("language") language: String = "eng",
        ): SurahResponse
    }
}
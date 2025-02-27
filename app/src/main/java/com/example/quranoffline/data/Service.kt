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
data class RadioStationsResponse(val radios: List<Radio>)


@Module
@InstallIn(SingletonComponent::class)
object Mp3QuranApi {
    val api: Mp3QuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.mp3quran.net/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Mp3QuranApiService::class.java)
    }

    interface Mp3QuranApiService {
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

        @GET("radios")
        suspend fun getRadioStations(
            @Query("language") language: String = "eng"
        ): RadioStationsResponse
    }
}
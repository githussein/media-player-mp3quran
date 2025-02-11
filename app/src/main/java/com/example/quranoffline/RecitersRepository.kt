package com.example.quranoffline

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit API Service
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

// Data Models
data class ReciterResponse(val reciters: List<Reciter>)
data class SurahResponse(val suwar: List<Surah>)

data class Reciter(
    val id: Int,
    val name: String,
    val letter: String,
    val moshaf: List<Moshaf>
)

data class Moshaf(
    val id: Int,
    val name: String,
    val server: String,
    val surah_total: Int,
    val moshaf_type: Int,
    val surah_list: String
)

data class Surah(
    val id: Int,
    val name: String,
    val start_page: Int,
    val end_page: Int,
    val makkia: Int,
    val type: Int
)

// Retrofit Instance
object RetrofitInstance {
    val api: ReciterApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.mp3quran.net/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReciterApiService::class.java)
    }
}
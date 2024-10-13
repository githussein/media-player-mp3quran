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
}

// Data Models
data class ReciterResponse(val reciters: List<Reciter>)

data class Reciter(
    val id: Int,
    val name: String,
    val letter: String,
    val moshaf: List<Moshaf>
)

data class Moshaf(
    val id: Int,
    val name: String,
    val surah_total: Int,
    val moshaf_type: Int,
    val surah_list: String
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
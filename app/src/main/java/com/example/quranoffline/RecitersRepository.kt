package com.example.quranoffline

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit API Service
interface ReciterApiService {
    @GET("reciters")
    suspend fun getReciters(@Query("language") language: String = "eng"): ReciterResponse
}

// Data Models
data class ReciterResponse(val reciters: List<Reciter>)

data class Reciter(
    val id: Int,
    val name: String,
    val letter: String,
    val moshaf: List<Moshaf> // Added moshaf property
)

data class Moshaf(
    val id: Int,
    val name: String,
    val server: String,
    val surah_total: Int
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
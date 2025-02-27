package com.example.quranoffline.ui.AllRecitersScreen

import com.example.quranoffline.data.Moshaf
import com.example.quranoffline.data.Mp3QuranApi
import com.example.quranoffline.data.Reciter
import com.example.quranoffline.data.ReciterResponse
import javax.inject.Inject


interface IRecitersRepository {
    suspend fun getAllReciters(): ReciterResponse
    suspend fun getReciterById(reciterId: String): ReciterResponse
}

class RecitersRepository @Inject constructor(
    private val apiService: Mp3QuranApi
) : IRecitersRepository {
    override suspend fun getAllReciters(): ReciterResponse = apiService.api.getAllReciters()
    override suspend fun getReciterById(reciterId: String) = apiService.api.getReciterById(reciterId = reciterId, language = "en")
}

class MockRecitersRepository : IRecitersRepository {
    override suspend fun getAllReciters(): ReciterResponse {
        return ReciterResponse(
            listOf(
                Reciter(id = 1, name = "Abdullah Basfar", letter = "A", moshaf = Moshaf.generateRandomMoshafList()),
                Reciter(id = 2, name = "Abdul Basit", letter = "B", moshaf = Moshaf.generateRandomMoshafList()),
            )
        )
    }

    override suspend fun getReciterById(reciterId: String): ReciterResponse {
        TODO("Not yet implemented")
    }
}
package com.example.quranoffline.ui.RadioStationsScreen

import com.example.quranoffline.data.Mp3QuranService
import com.example.quranoffline.data.RadioStationsResponse
import javax.inject.Inject

interface IRadioStationRepository {
    suspend fun getRadioStations(): RadioStationsResponse
}

class RadioStationsRepository @Inject constructor(
    private val apiService: Mp3QuranService
) : IRadioStationRepository {
    override suspend fun getRadioStations(): RadioStationsResponse = apiService.api.getRadioStations()
}
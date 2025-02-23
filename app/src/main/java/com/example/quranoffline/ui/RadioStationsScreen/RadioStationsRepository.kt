package com.example.quranoffline.ui.RadioStationsScreen

import com.example.quranoffline.data.RadioStationsResponse
import com.example.quranoffline.data.RecitersApi
import javax.inject.Inject

interface IRadioStationRepository {
    suspend fun getRadioStations(): RadioStationsResponse
}

class RadioStationsRepository @Inject constructor(
    private val apiService: RecitersApi
) : IRadioStationRepository {
    override suspend fun getRadioStations(): RadioStationsResponse = apiService.api.getRadioStations()
}
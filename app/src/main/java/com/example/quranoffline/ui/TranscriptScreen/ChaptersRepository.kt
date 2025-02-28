package com.example.quranoffline.ui.TranscriptScreen

import com.example.quranoffline.data.QSurahResponse
import com.example.quranoffline.data.QuranApiService
import javax.inject.Inject

interface IChaptersRepository {
    suspend fun getChapters(): QSurahResponse
}

class ChaptersRepository @Inject constructor(
    private val apiService: QuranApiService
) : IChaptersRepository {
    override suspend fun getChapters(): QSurahResponse {
        val surahList = apiService.api.getQSurahs()
        return QSurahResponse(chaptersList = surahList)
    }
}
package com.example.quranoffline.ui.TranscriptScreen

import com.example.quranoffline.data.ChapterScriptResponse
import com.example.quranoffline.data.ChaptersResponse
import com.example.quranoffline.data.QuranApiService
import javax.inject.Inject

interface IChaptersRepository {
    suspend fun getChapters(): ChaptersResponse
    suspend fun getChapterScript(chapterId: String): ChapterScriptResponse
}

class ChaptersRepository @Inject constructor(
    private val apiService: QuranApiService
) : IChaptersRepository {
    override suspend fun getChapters(): ChaptersResponse {
        val surahList = apiService.api.getQSurahs()
        return ChaptersResponse(chaptersList = surahList)
    }

    override suspend fun getChapterScript(chapterId: String): ChapterScriptResponse {
        val chapterScript = apiService.api.getChapterScript(chapterId)
        return ChapterScriptResponse(chapterScript = chapterScript)
    }
}
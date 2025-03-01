package com.example.quranoffline.ui.TranscriptScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quranoffline.ui.components.ComponentLoadingState

@Composable
fun ChapterScriptScreen(
    modifier: Modifier,
    chapterId: String,
    viewModel: ChaptersViewModel = hiltViewModel()
) {
    LaunchedEffect(chapterId) {
        viewModel.getChapterScript(chapterId)
    }
    val chapterScript by viewModel.chapterScript.collectAsState()
    val resultState by viewModel.resultState.collectAsState()

    when (resultState) {
        ChaptersResultState.Idle -> Text("idle")

        ChaptersResultState.Loading -> ComponentLoadingState()

        is ChaptersResultState.Success -> {}

        is ChaptersResultState.ScriptSuccess -> Box(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(10))
                .fillMaxSize()
//                .fillMaxWidth()
//                .height(200.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF4A0F6F),
                            Color(0xFF662222)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(chapterScript?.chapterScript?.surahNameArabicLong.orEmpty())
                Text(chapterScript?.chapterScript?.arabic1?.firstOrNull().orEmpty())
                Text(chapterScript?.chapterScript?.english?.firstOrNull().orEmpty())
            }
        }


        is ChaptersResultState.Failure -> Text("error")
    }

}
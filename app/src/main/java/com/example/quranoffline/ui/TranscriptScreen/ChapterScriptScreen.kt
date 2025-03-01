package com.example.quranoffline.ui.TranscriptScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val script by viewModel.chapterScript.collectAsState()
    val resultState by viewModel.resultState.collectAsState()

    when (resultState) {
        ChaptersResultState.Idle -> Text("idle")

        ChaptersResultState.Loading -> ComponentLoadingState()

        is ChaptersResultState.Success -> {}

        is ChaptersResultState.ScriptSuccess -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(10))
                            .fillMaxWidth()
                            .height(200.dp)
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(script?.chapterScript?.surahName.orEmpty(), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                            Text(script?.chapterScript?.surahNameTranslation.orEmpty(), color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
                            Text(script?.chapterScript?.surahNameArabicLong.orEmpty(), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
                            Text("${script?.chapterScript?.totalAyah.toString()} verses - ${script?.chapterScript?.revelationPlace.orEmpty()}", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }

                script?.chapterScript?.arabic1?.forEachIndexed { index, arabic ->
                    item {
                        VerseScriptItem(index, arabic, script?.chapterScript?.english?.get(index).toString())
                    }
                }
            }
        }

        is ChaptersResultState.Failure -> Text("error")
    }
}

@Composable
fun VerseScriptItem(index: Int, arabic: String, english: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(arabic, modifier = Modifier.padding(vertical = 16.dp), fontSize = 18.sp, textAlign = TextAlign.Center)
        Text("( ${index + 1} )", modifier = Modifier.padding(bottom = 16.dp), textAlign = TextAlign.Center)
        Text(english, modifier = Modifier.padding(bottom = 16.dp))
        HorizontalDivider(thickness = 0.3.dp, color = Color.Gray)
    }
}
package com.example.quranoffline.ui.TranscriptScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quranoffline.ui.components.ComponentLoadingState
import com.example.quranoffline.ui.components.TranscriptChapterItem

@Composable
fun ChaptersScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: ChaptersViewModel = hiltViewModel()
) {
    val resultState by viewModel.resultState.collectAsState()

    when (resultState) {
        ChaptersResultState.Idle -> Text("idle")

        ChaptersResultState.Loading -> ComponentLoadingState()

        is ChaptersResultState.Success -> LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            (resultState as ChaptersResultState.Success).response.chaptersList.forEachIndexed { index, chapter ->
                item {
                    TranscriptChapterItem(
                        index = (index + 1).toString(),
                        chapter = chapter
                    ) {
//                        navController.navigate(Reciter(reciter.id.toString()))
                    }
                }
            }
        }


        is ChaptersResultState.Failure -> Text("error")
    }

}

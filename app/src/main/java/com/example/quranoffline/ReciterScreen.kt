package com.example.quranoffline

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReciterScreen(modifier: Modifier, viewModel: ReciterViewModel, reciterId: String) {

    LaunchedEffect(reciterId) {
        viewModel.fetchReciterById(reciterId)
        viewModel.fetchSurahList()
    }
    val reciter = viewModel.selectedReciter.value
    val surahList = viewModel.surahList.value

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp), text = reciter?.name.orEmpty(), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        if (surahList.isNotEmpty() && reciter != null) {
            val availableSurahId = reciter.moshaf.first().surah_list.split(",").map { it.toInt() }.toList()
            availableSurahId.forEach {
                item { ComposeSurahItem(surahList.find { surah -> surah.id == it }) }
            }
        } else {
            item { CircularProgressIndicator() }
        }

    }
}

@Composable
private fun ComposeSurahItem(surah: Surah?) {
    if (surah == null) return

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
//            .clickable(onClick = { onSurahClick(surah.id.toString()) }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = surah.id.toString(), color = Color.Gray)

        Text(
            text = surah.name,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            modifier = Modifier
                .border(4.dp, Color.Companion.Gray, CircleShape)
                .padding(4.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "play icon"
        )
    }
}
package com.example.quranoffline

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
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
    }
    val reciter = viewModel.selectedReciter.value

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(reciter?.name.orEmpty(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        reciter?.let {
            it.moshaf.first().surah_list.split(",").forEach {
                item {
                    ComposeSurahItem(surah = it)
                }
            }
        }
    }
}

@Composable
private fun ComposeSurahItem(surah: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
//            .clickable(onClick = { onReciterClick(reciter.id.toString()) }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        ) {
            Text(text = surah, color = Color.Gray)
//            reciter.moshaf.forEach {
//                Text(text = "(${it.surah_total}) ${it.name.substringBefore("-")}", color = Color.Gray, fontSize = 14.sp, maxLines = 1)
//            }
        }

        Icon(
            modifier = Modifier
                .border(4.dp, Color.Companion.Gray, CircleShape)
                .padding(4.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "play icon"
        )
    }
}
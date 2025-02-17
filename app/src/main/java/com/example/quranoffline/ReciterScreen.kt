package com.example.quranoffline

import android.media.MediaPlayer
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

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


        if (reciter?.moshaf != null && reciter.moshaf.size > 1) {
            item { ReciterDropdownMenu(reciter = reciter) }
        }


        if (surahList.isNotEmpty() && reciter != null) {
            val availableSurahId = reciter.moshaf.first().surah_list.split(",").map { it.toInt() }.toList()
            availableSurahId.forEach {
                item {
                    ComposeSurahItem(surahList.find { surah -> surah.id == it }, reciter.moshaf.first().server, isPlaying = viewModel.isPlaying?.value ?: false) { url ->
                        viewModel.playStopAudio(url)
                    }
                }
            }
        } else {
            item { CircularProgressIndicator() }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReciterDropdownMenu(
    reciter: Reciter,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val moshafList = reciter.moshaf.map { it.name.substringBefore("-") }
    var selectedMoshaf by remember { mutableStateOf<String?>(null) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = selectedMoshaf ?: "Select a Moshaf",
            onValueChange = {}, // todo update availableSurahList
            readOnly = true,

            trailingIcon = { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "arrow down icon") },
            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .menuAnchor() // Ensure the dropdown is anchored to the TextField
                .clickable { expanded = true } // Make the TextField clickable
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            moshafList.forEach { moshafName ->
                DropdownMenuItem(
                    text = { Text(text = moshafName) },
                    onClick = {
                        selectedMoshaf = moshafName
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun ComposeSurahItem(surah: Surah?, server: String, isPlaying: Boolean, onClick: (String) -> Unit) {
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
                .border(4.dp, Color.Gray, CircleShape)
                .padding(4.dp)
                .clickable {
                    onClick.invoke(server.formatServerUrl(surah.id))
                },
            imageVector = if (isPlaying) Icons.Default.Menu else Icons.Default.PlayArrow,
            contentDescription = if (isPlaying) "Pause icon" else "Play icon"
        )
    }
}

fun String.formatServerUrl(surahId: Int) = "${this}${String.format("%03d", surahId)}.mp3"
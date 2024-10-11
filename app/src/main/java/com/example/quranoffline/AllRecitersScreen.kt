package com.example.quranoffline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AllRecitersScreen(modifier: Modifier, viewModel: ReciterViewModel, onReciterClick: (String) -> Unit) {
    val reciters: List<Reciter> = viewModel.reciters.value

    LazyColumn(
        modifier = modifier
    ) {
        reciters.forEach {
            item { ComposeReciterItem(it, onReciterClick) }
        }
    }
}

@Composable
fun ComposeReciterItem(reciter: Reciter, onReciterClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onReciterClick(reciter.id.toString()) }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        ) {
            Text(text = reciter.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            reciter.moshaf.forEach {
                Text(text = "(${it.surah_total}) ${it.name.substringBefore("-")}", color = Color.Gray, fontSize = 14.sp, maxLines = 1)
            }
        }

        Icon(
            modifier = Modifier
//                .border(4.dp, Color.Companion.Gray, CircleShape)
                .padding(4.dp),
//            imageVector = Icons.Default.PlayArrow,
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "play icon"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ReciterItemPreview() {
    ComposeReciterItem(
        Reciter(
            id = 1,
            name = "Mahmoud Al-Hussary",
            letter = "M",
            moshaf = listOf(
                Moshaf(
                    id = 1,
                    name = "Morattal",
                    server = "",
                    surah_total = 114
                )
            )
        )
    ) {}
}
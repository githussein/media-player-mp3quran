package com.example.quranoffline


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranoffline.ui.components.ComposeReciterItem

@Composable
fun AllRecitersScreen(modifier: Modifier, viewModel: ReciterViewModel, onReciterClick: (String) -> Unit) {
    val reciters: List<Reciter> = viewModel.reciters.value

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp), text = "The Holy Quran", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        reciters.forEach {
            item { ComposeReciterItem(it, onReciterClick) }
        }
    }
}

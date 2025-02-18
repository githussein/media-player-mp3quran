package com.example.quranoffline


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quranoffline.ui.components.ComposeReciterItem

@Composable
fun AllRecitersScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: ReciterViewModel = hiltViewModel()
) {
    val reciters: List<Reciter> = viewModel.reciters.value

    if (viewModel.isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF4A0F6F))
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            item {
                Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp), text = "The Holy Quran", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            reciters.forEach { reciter ->
                item {
                    ComposeReciterItem(reciter) {
                        navController.navigate(ReciterNavigation(reciter.id.toString()))
                    }
                }
            }
        }
    }
}

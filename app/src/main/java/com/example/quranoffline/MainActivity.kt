package com.example.quranoffline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranoffline.ui.theme.QuranOfflineTheme

class MainActivity : ComponentActivity() {
    private val reciterViewModel: ReciterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuranOfflineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AllReciters(
                        modifier = Modifier
                            .padding(innerPadding)
                            .systemBarsPadding(),
                        viewModel = reciterViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun AllReciters(modifier: Modifier, viewModel: ReciterViewModel) {
    val reciters: List<Reciter> = viewModel.reciters.value

    LazyColumn(
        modifier = modifier
    ) {
        reciters.forEach {
            item {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(modifier = Modifier.padding(end = 16.dp)) {
                        Text(text = it.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        it.moshaf.forEach {
                            Text(text = it.name)
                        }
                    }

                    Icon(
                        modifier = Modifier
                            .padding()
                            .border(4.dp, Color.Companion.Gray, CircleShape)
                            .padding(4.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "play icon"
                    )
                }
                HorizontalDivider(thickness = 1.dp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AllRecitersPreview() {
    QuranOfflineTheme {
        AllReciters(Modifier, ReciterViewModel())
    }
}
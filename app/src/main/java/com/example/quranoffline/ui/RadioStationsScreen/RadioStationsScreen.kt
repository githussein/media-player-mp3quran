package com.example.quranoffline.ui.RadioStationsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quranoffline.data.Radio
import com.example.quranoffline.ui.components.ComponentLoadingState
import com.example.quranoffline.ui.components.ComponentRadioPoster

@Composable
fun RadioStationsScreen(
    modifier: Modifier,
    viewModel: RadioStationsViewModel = hiltViewModel()
) {
    val resultState by viewModel.resultState.collectAsState()
    val scrollState = rememberScrollState()

    when (resultState) {
        RadiosResultState.Idle -> Text("idle")

        RadiosResultState.Loading -> ComponentLoadingState()

        is RadiosResultState.Success -> Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp), text = "Radio Stations", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            val radioStations = (resultState as RadiosResultState.Success).response.radios.take(20)
            RadioStationsGrid(radioStations)
        }

        is RadiosResultState.Failure -> Text("error")
    }

}

@Composable
fun RadioStationsGrid(elements: List<Radio>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        elements.chunked(2).forEach { rowElements ->
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                rowElements.forEach { element ->
                    ComponentRadioPoster(Modifier.weight(1f), element.name, false)
                }
                if (rowElements.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
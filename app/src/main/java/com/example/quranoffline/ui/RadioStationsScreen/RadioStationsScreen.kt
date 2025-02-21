package com.example.quranoffline.ui.RadioStationsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranoffline.ui.components.ComponentRadioPoster

@Composable
fun RadioStationsScreen(modifier: Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp), text = "Radio Stations", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            ComponentRadioPoster(modifier, "Radio Station", false)
            Spacer(modifier = modifier.width(16.dp))
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
        }
        Row(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
            Spacer(modifier = modifier.width(16.dp))
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
        }
        Row(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
            Spacer(modifier = modifier.width(16.dp))
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
        }
        Row(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
            Spacer(modifier = modifier.width(16.dp))
            ComponentRadioPoster(modifier = modifier, "Radio Station", false)
        }
    }
}
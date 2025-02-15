package com.example.quranoffline

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranoffline.ui.components.ComponentRadioPoster
import com.example.quranoffline.ui.components.ComposeReciterItem


@Composable
fun HomeScreen(modifier: Modifier) {
    val verticalScrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tilawah App", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("Radio Stations", "view all")
        Spacer(modifier = Modifier.height(8.dp))

        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ComponentRadioPoster(modifier = modifier, imageId = R.drawable.masjid2)
            ComponentRadioPoster(modifier = modifier, imageId = R.drawable.masjid7)
            ComponentRadioPoster(modifier = modifier, imageId = R.drawable.masjid5)
        }
        Spacer(modifier = modifier.height(32.dp))

        SectionHeader("Reciters", "view all")
        Spacer(modifier = Modifier.height(8.dp))

        ComposeReciterItem(
            Reciter(
                id = 1,
                name = "Mahmoud Al-Hussary",
                letter = "M",
                moshaf = Moshaf.generateRandomMoshafList()
            ),
            {}
        )
        ComposeReciterItem(
            Reciter(
                id = 1,
                name = "Mishary Alafasi",
                letter = "M",
                moshaf = Moshaf.generateRandomMoshafList()
            ),
            {}
        )
        ComposeReciterItem(
            Reciter(
                id = 1,
                name = "Mohamemed Al-Minshawy",
                letter = "M",
                moshaf = Moshaf.generateRandomMoshafList()
            ),
            {}
        )

        Spacer(modifier = modifier.height(32.dp))



        SectionHeader("Quran & Hadith Scripts", null)
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
private fun SectionHeader(left: String, right: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(left, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(right.orEmpty(), color = Color.Blue)
    }
}

package com.example.quranoffline.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComponentScriptPoster(modifier: Modifier, title: String, description: String, painterResourceId: Int) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(10))
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF4A0F6F),
                        Color(0xFF662222)
                    )
                )
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(description, color = Color.White.copy(alpha = 0.8f))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.White.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(100),
                    modifier = modifier.padding(top = 8.dp),
                ) {
                    Text("Read more", color = Color.Black)
                }
            }

            Image(
                painter = painterResource(id = painterResourceId),
                contentDescription = "photo of a quran",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(20.dp)
                    .size(150.dp)
            )
        }
    }
}
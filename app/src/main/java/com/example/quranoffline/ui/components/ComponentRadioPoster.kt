package com.example.quranoffline.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quranoffline.R

@Composable
fun ComponentRadioPoster(modifier: Modifier, imageId: Int = R.drawable.masjid3) {
    Box(
        modifier = Modifier
            .size(180.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "photo of a masjid",
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.5f))
                .align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "play icon",
                tint = Color.White,
                modifier = modifier
                    .size(36.dp)
                    .align(Alignment.Center)

            )
        }
    }
}
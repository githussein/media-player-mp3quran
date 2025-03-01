package com.example.quranoffline.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ComponentGradientBox(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color(0xFF4A0F6F),
        Color(0xFF662222)
    ),
    height: Dp = 200.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(12))
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.horizontalGradient(colors = colors)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
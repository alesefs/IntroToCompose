package com.example.introtocompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun GradientBorderCardAngle18(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val angle = Math.toRadians(18.0)
    val colors = listOf(Color(0xFFA6DBD9), Color(0xFFF5DE3E))

    Card(
        modifier = modifier
            .drawBehind {
                val strokeWidth = 3.dp.toPx()
                val radius = 24.dp.toPx()

                val x = cos(angle).toFloat()
                val y = sin(angle).toFloat()
                val end = Offset(size.width * x, size.height * y)

                val brush = Brush.linearGradient(colors, start = Offset.Zero, end = end)
                drawRoundRect(
                    brush = brush,
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                    style = Stroke(width = strokeWidth)
                )
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Preview
@Composable
private fun PreviewGradientBorderCardAngle18() {
    GradientBorderCardAngle18(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp)
    ) {
        // Conteúdo interno
    }
}

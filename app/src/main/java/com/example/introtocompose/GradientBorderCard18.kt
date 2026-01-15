package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun GradientBorderCard18(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val angleInDegrees = 18f
    val angleInRadians = Math.toRadians(angleInDegrees.toDouble())

    // Calcula o vetor da direção do gradiente
    val x = cos(angleInRadians).toFloat()
    val y = sin(angleInRadians).toFloat()

    val borderBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFF5DE3E), // Amarelo
            Color(0xFFA6DBD9)  // Verde-água
        ),
        start = Offset.Zero,
        end = Offset(x, y)
    )

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(3.dp, borderBrush),
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
fun PreviewGradientCard18() {
    GradientBorderCard18(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp)
    ) {
        // Conteúdo interno
    }
}

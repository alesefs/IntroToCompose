package com.example.introtocompose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun TypingDotsAnimation(
    modifier: Modifier = Modifier,
    dotSize: Dp = 16.dp,
    dotColor: Color = Color.White,
    jumpHeight: Dp = 10.dp,
    spaceBetween: Dp = 12.dp
) {

    val dot1 = remember { Animatable(0f) }
    val dot2 = remember { Animatable(0f) }
    val dot3 = remember { Animatable(0f) }

    val jump = with(LocalDensity.current) {
        jumpHeight.toPx()
    }

    LaunchedEffect(Unit) {

        suspend fun animateDot(dot: Animatable<Float, AnimationVector1D>) {

            // sobe
            dot.animateTo(
                targetValue = -jump,
                animationSpec = tween(
                    durationMillis = 150,
                    easing = FastOutSlowInEasing
                )
            )

            // permanece em cima
            delay(150)

            // desce
            dot.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 150,
                    easing = FastOutSlowInEasing
                )
            )
        }

        while (true) {
            animateDot(dot1)
            animateDot(dot2)
            animateDot(dot3)
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Dot(dot1.value, dotSize, dotColor)
        Dot(dot2.value, dotSize, dotColor)
        Dot(dot3.value, dotSize, dotColor)
    }
}

@Composable
private fun Dot(
    offsetY: Float,
    size: Dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = 0,
                    y = offsetY.roundToInt()
                )
            }
            .size(size)
            .background(color, CircleShape)
    )
}

@Preview
@Composable
fun ChatBubbleTyping() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFAED8FF))
            .padding(horizontal = 32.dp, vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        TypingDotsAnimation()
    }
}
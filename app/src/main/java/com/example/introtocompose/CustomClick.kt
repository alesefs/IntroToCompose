package com.example.introtocompose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PressableBoxWithFeedback(
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (isPressed) Color.DarkGray else Color.Gray,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 250),
        label = "BackgroundColor"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(animatedColor)
            .pointerInput(Unit) {
                while (true) {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        isPressed = true
                        down.consume()

                        do {
                            val event = awaitPointerEvent()
                            if (event.changes.any { it.changedToUp() }) {
                                isPressed = false
                                onClick() // chamado ao soltar
                                break
                            }
                        } while (true)
                    }
                }
            }
    ) {
        Text(
            text = if (isPressed) "Pressionado..." else "Toque Aqui",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreen() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        PressableBoxWithFeedback(
            onClick = {
                println("Clique completo detectado!")
            }
        )
    }
}
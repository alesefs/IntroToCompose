package com.example.introtocompose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Composable
fun CoachMarkExample() {
    var showCoachMark by remember { mutableStateOf(true) }
    var targetRect by remember { mutableStateOf<Rect?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .onGloballyPositioned {
                        // Captura posição e tamanho do botão na tela
                        val pos = it.positionInRoot()
                        val size = it.size.toSize()
                        targetRect = Rect(pos, size)
                    }
            ) {
                Text("Clique aqui")
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { showCoachMark = true }) {
                Text("Mostrar Coach Mark")
            }
        }

        // Overlay Coach Mark
        if (showCoachMark && targetRect != null) {
            CoachMarkOverlay(
                targetRect = targetRect!!,
                onDismiss = { showCoachMark = false },
                message = "Toque aqui para iniciar!"
            )
        }
    }
}

@Composable
fun CoachMarkOverlay(
    targetRect: Rect,
    message: String,
    onDismiss: () -> Unit
) {
    val density = LocalDensity.current
    val animatedAlpha by animateFloatAsState(1f, label = "")

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f * animatedAlpha))
            .pointerInput(Unit) {
                detectTapGestures { onDismiss() }
            }
    ) {
        // “Furo” no overlay
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                addRect(Rect(Offset.Zero, size))
                addRoundRect(
                    RoundRect(
                        rect = targetRect,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                )
                fillType = PathFillType.EvenOdd
            }
            drawPath(path, color = Color.Black.copy(alpha = 0.6f * animatedAlpha))
        }

        // Mensagem abaixo do alvo
        Column(
            Modifier
                .offset {
                    IntOffset(
                        targetRect.left.toInt(),
                        (targetRect.bottom + with(density) { 16.dp.toPx() }).toInt()
                    )
                }
                .width(200.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp),
                    color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview_coachmark() {
    IntroToComposeTheme {
        CoachMarkExample()
    }
}

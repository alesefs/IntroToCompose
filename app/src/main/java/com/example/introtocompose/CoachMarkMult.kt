package com.example.introtocompose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.Alignment
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

// -----------------------------
// MODELO DE DADOS
// -----------------------------
data class CoachStep(
    val message: String,
    val targetRect: Rect? = null
)

// -----------------------------
// COACH MARK OVERLAY
// -----------------------------
@Composable
fun CoachMarkOverlay(
    step: CoachStep,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    isLastStep: Boolean
) {
    val targetRect = step.targetRect ?: return
    val density = LocalDensity.current
    val alpha by animateFloatAsState(targetValue = 1f, label = "")

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures {} } // bloqueia toque no fundo
    ) {
        // Fundo escuro com recorte
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                addRect(Rect(Offset.Zero, size))
                addRoundRect(
                    RoundRect(
                        targetRect,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                )
                fillType = PathFillType.EvenOdd
            }
            drawPath(
                path,
                color = Color.Black.copy(alpha = 0.6f * alpha)
            )
        }

        // Balão com texto + botão
        Column(
            Modifier
                .offset {
                    IntOffset(
                        targetRect.left.toInt(),
                        (targetRect.bottom + with(density) { 16.dp.toPx() }).toInt()
                    )
                }
                .widthIn(max = 250.dp)
                .padding(horizontal = 16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(step.message, color = Color.Black)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = { if (isLastStep) onFinish() else onNext() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(if (isLastStep) "Concluir" else "Próximo")
                    }
                }
            }
        }
    }
}

// -----------------------------
// EXEMPLO COMPLETO
// -----------------------------
@Composable
fun CoachSequenceExample() {
    var showCoach by remember { mutableStateOf(true) }

    // Guarda as posições dos elementos
    var rectButton1 by remember { mutableStateOf<Rect?>(null) }
    var rectButton2 by remember { mutableStateOf<Rect?>(null) }
    var rectButton3 by remember { mutableStateOf<Rect?>(null) }

    // Passos do tutorial
    val steps = listOf(
        CoachStep("Este é o primeiro botão importante", rectButton1),
        CoachStep("Aqui está o segundo botão", rectButton2),
        CoachStep("E este é o terceiro! Fim do tutorial.", rectButton3)
    )

    var currentStepIndex by remember { mutableStateOf(0) }

    Box(Modifier.fillMaxSize()) {
        // Conteúdo principal
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .onGloballyPositioned {
                        val pos = it.positionInRoot()
                        val size = it.size.toSize()
                        rectButton1 = Rect(pos, size)
                    }
            ) {
                Text("Botão 1")
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .onGloballyPositioned {
                        val pos = it.positionInRoot()
                        val size = it.size.toSize()
                        rectButton2 = Rect(pos, size)
                    }
            ) {
                Text("Botão 2")
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .onGloballyPositioned {
                        val pos = it.positionInRoot()
                        val size = it.size.toSize()
                        rectButton3 = Rect(pos, size)
                    }
            ) {
                Text("Botão 3")
            }

            Button(onClick = {
                showCoach = true
            }) {
                Text("Reiniciar Coach")
            }
        }

        // Mostra o overlay se estiver ativo e posição disponível
        if (showCoach &&
            steps.getOrNull(currentStepIndex)?.targetRect != null
        ) {
            CoachMarkOverlay(
                step = steps[currentStepIndex],
                onNext = { currentStepIndex++ },
                onFinish = { showCoach = false },
                isLastStep = currentStepIndex == steps.lastIndex
            )
        }
    }
}

@Preview
@Composable
private fun Preview_coachmark() {
    IntroToComposeTheme {
        CoachSequenceExample()
    }
}
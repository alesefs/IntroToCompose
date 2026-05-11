package com.example.introtocompose

//@Composable
//fun OtpInput(
//    otpLength: Int = 6,
//    onOtpComplete: (String) -> Unit = {}
//) {
//    var otpValue by remember { mutableStateOf("") }
//
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .padding(16.dp)
//    ) {
//        // Criamos caixas visuais para cada dígito
//        repeat(otpLength) { index ->
//            Box(
//                modifier = Modifier
//                    .size(width = 48.dp, height = 56.dp)
//                    .border(
//                        width = 2.dp,
//                        color = if (index < otpValue.length) Color.Black else Color.Gray,
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = otpValue.getOrNull(index)?.toString() ?: "",
//                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                )
//            }
//        }
//    }
//
//    // Campo invisível para capturar a digitação
//    BasicTextField(
//        value = otpValue,
//        onValueChange = { newText ->
//            if (newText.length <= otpLength/* && newText.all { it.isDigit() }*/) {
//                otpValue = newText
//                if (otpValue.length == otpLength) {
//                    onOtpComplete(otpValue)
//                }
//            }
//        },
//        modifier = Modifier
//            .size(1.dp) // invisível
//    )
//}

//@Composable
//fun OtpInput(
//    otpLength: Int = 6,
//    onOtpComplete: (String) -> Unit = {}
//) {
//    var otpValue by remember { mutableStateOf("") }
//    val focusRequester = remember { FocusRequester() }
//    val focusManager = LocalFocusManager.current
//
//    // Campo invisível para capturar texto
//    BasicTextField(
//        value = otpValue,
//        onValueChange = { newText ->
//            if (newText.length <= otpLength && newText.all { it.isDigit() }) {
//                otpValue = newText
//                if (newText.length == otpLength) onOtpComplete(newText)
//            }
//        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
//        modifier = Modifier
//            .size(1.dp) // renderiza mas fica invisível
//            .focusRequester(focusRequester)
//            .focusable() // IMPORTANTÍSSIMO: agora aceita foco
//    )
//
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .padding(16.dp)
//    ) {
//        repeat(otpLength) { index ->
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .border(
//                        width = 2.dp,
//                        color = if (index < otpValue.length) Color.Black else Color.Gray,
//                        shape = RoundedCornerShape(8.dp)
//                    )
//                    .clickable {
//                        // força o campo invisível a receber foco → teclado sobe
//                        focusRequester.requestFocus()
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = otpValue.getOrNull(index)?.toString() ?: "",
//                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                )
//            }
//        }
//    }
//}

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Composable
fun OtpInputWorking(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit = {}
) {
    var otpValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    // Texto "invisível" que recebe foco/teclado
    BasicTextField(
        value = otpValue,
        onValueChange = { newText ->
            val digitsOnly = newText.filter { it.isDigit() }
            if (digitsOnly.length <= otpLength) {
                otpValue = digitsOnly
                if (digitsOnly.length == otpLength) {
                    onOtpComplete(digitsOnly)
                    // opcional: fechar teclado
                    focusManager.clearFocus()
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = androidx.compose.ui.text.input.ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        cursorBrush = SolidColor(Color.Unspecified), // sem cursor visível
        textStyle = TextStyle(fontSize = 0.sp), // texto invisível visualmente
        modifier = Modifier
            .size(1.dp) // precisa existir para o sistema aceitar foco
            .focusRequester(focusRequester)
            .alpha(0f) // invisível visualmente mas focável
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        repeat(otpLength) { index ->
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .border(
                        width = 2.dp,
                        color = if (index < otpValue.length) Color.Black else Color.Gray,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        // garante foco no campo oculto E força abertura do teclado
                        focusRequester.requestFocus()
                        keyboard?.show()
                    }
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = otpValue.getOrNull(index)?.toString() ?: "",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Otp_preview() {
    IntroToComposeTheme {
        OtpInputWorking(
            otpLength = 6
        ) { otp ->
            // Aqui você recebe o código completo
            Log.d("OTP", "Código: $otp")
        }
    }
}
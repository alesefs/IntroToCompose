package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Composable
fun OtpInput(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit = {}
) {
    var otpValue by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        // Criamos caixas visuais para cada dígito
        repeat(otpLength) { index ->
            Box(
                modifier = Modifier
                    .size(width = 48.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = if (index < otpValue.length) Color.Black else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = otpValue.getOrNull(index)?.toString() ?: "",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }

    // Campo invisível para capturar a digitação
    BasicTextField(
        value = otpValue,
        onValueChange = { newText ->
            if (newText.length <= otpLength/* && newText.all { it.isDigit() }*/) {
                otpValue = newText
                if (otpValue.length == otpLength) {
                    onOtpComplete(otpValue)
                }
            }
        },
        modifier = Modifier
            .size(0.dp) // invisível
    )
}

@Preview(showBackground = true)
@Composable
private fun Otp_preview() {
    IntroToComposeTheme {
        OtpInput(
            otpLength = 6
        ) { otp ->
            // Aqui você recebe o código completo
            Log.d("OTP", "Código: $otp")
        }
    }
}
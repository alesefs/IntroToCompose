package com.example.introtocompose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

// Máscara dinâmica para telefone (10 ou 11 dígitos)
class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text.filter { it.isDigit() }.take(10)

        val formatted = buildString {
            for (i in raw.indices) {
                when (i) {
                    0 -> append("(").append(raw[i])
                    1 -> append(raw[i]).append(") ")
                    5 -> append(raw[i]).append("-")
                    else -> append(raw[i])
                }
            }
        }

        // Mapeamento do cursor entre texto cru e texto formatado
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 0 -> offset
                    offset <= 1 -> offset + 1          // pula "("
                    offset <= 2 -> offset + 2          // pula "()"
                    offset <= 6 -> offset + 3          // inclui espaço
                    offset <= 10 -> offset + 4         // inclui hífen
                    else -> formatted.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 0 -> offset
                    offset <= 2 -> offset - 1          // dentro do "(X"
                    offset <= 4 -> offset - 2          // dentro do "(XX"
                    offset <= 9 -> offset - 3          // após ") "
                    offset <= 14 -> offset - 4         // após hífen
                    else -> raw.length
                }
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

@Composable
fun PhoneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { new ->
            val digits = new.filter { it.isDigit() }.take(11)
            onValueChange(digits)
        },
        visualTransformation = PhoneVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        placeholder = { Text("(00) 0000-0000") },
        modifier = modifier.fillMaxWidth()
    )
}

// Exemplo de uso
@Preview(showBackground = true)
@Composable
fun PhoneDemo() {
    var phone by remember { mutableStateOf("") }
    PhoneTextField(value = phone, onValueChange = { phone = it })
}

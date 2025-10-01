package com.example.introtocompose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

// Máscara de KG no formato XXX.XXX,XX
class KgVisualTransformation : VisualTransformation {
    private val formatter = DecimalFormat("#,##0.00 Kg", DecimalFormatSymbols(Locale("pt", "BR")))

    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(12) // limite de segurança
        val value = digits.toLongOrNull() ?: 0L

        // divide por 100 para garantir 2 casas decimais
        val formatted = formatter.format(value / 100.0)

        return TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = formatted.length
                override fun transformedToOriginal(offset: Int): Int = digits.length
            }
        )
    }
}

@Composable
fun KgTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { new ->
            val digits = new.filter { it.isDigit() }.take(12)
            onValueChange(digits)
        },
        visualTransformation = KgVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        placeholder = { Text("0,00") },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right), // alinhado à direita
        modifier = modifier.fillMaxWidth()
    )
}

// Exemplo de uso
@Preview(showBackground = true)
@Composable
fun KgDemo() {
    var kg by remember { mutableStateOf("") }
    KgTextField(value = kg, onValueChange = { kg = it })
}

@Preview(showBackground = true)
@Composable
fun RightAlignedBasicTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(
            fontSize = 18.sp,
            textAlign = TextAlign.Right // 🔹 Alinha à direita
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

package com.example.introtocompose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class CustomTextValuesStyle(val textColor: Color) {
    Dark(textColor = Color.Black),
    Light(textColor = Color.White),
    Colors(textColor = Color.Gray)
}

private val patternCurrencySign = Regex("[a-zA-Z]*\\p{Sc}")

@Composable
fun CustomTextValues(
    value: String,
    style: CustomTextValuesStyle = CustomTextValuesStyle.Dark,
    showValue: Boolean = true
) {
    val textValuesDescription: String = "O valor é de: $value reais"

    val valueToDouble: Double = value.trim()
        .replace(patternCurrencySign, "")
        .replace(" ", "")
        .replace(",", ".")
        .toDouble()

    val colorsToValue: Color = if (style == CustomTextValuesStyle.Colors) {
        if(showValue) {
            if(valueToDouble < 0) {
                Color.Red
            } else if (valueToDouble > 0) {
                Color.Green
            } else {
                Color.Gray
            }
        } else {
            style.textColor
        }
    } else {
        style.textColor
    }

    /*val currency: String = if (valueToDouble <= 0) {
        value
    } else {
        "+ $value"
    }
    val currencyHidden: String = patternCurrencySign.find(currency)?.value.orEmpty()
    text = if (showValue) currency else "$currencyHidden ******",
    */

    val currencyHidden: String = patternCurrencySign.find(value)?.value.orEmpty()

    Text(
        text = if (showValue) value else "$currencyHidden ******",
        maxLines = 1,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorsToValue
        ),
        modifier = Modifier.semantics {
            contentDescription = textValuesDescription
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextValuesPreview() {
    CustomTextValues(
        value = "R$ 100,70"
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF005599)
@Composable
fun CustomTextValuesPreviewList() {
    val allCasesStyleValues = listOf(
        Triple(CustomTextValuesStyle.Dark, "R$ 100,70", true),
        Triple(CustomTextValuesStyle.Dark, "R$ -100,70", true),
        Triple(CustomTextValuesStyle.Dark, "R$ 0,00", true),
        Triple(CustomTextValuesStyle.Dark, "R$ -100,70", false),
        Triple(CustomTextValuesStyle.Light, "R$ 100,70", true),
        Triple(CustomTextValuesStyle.Light, "R$ -100,70", true),
        Triple(CustomTextValuesStyle.Light, "R$ 0,00", true),
        Triple(CustomTextValuesStyle.Light, "R$ 100,70", false),
        Triple(CustomTextValuesStyle.Colors, "R$ 100,70", true),
        Triple(CustomTextValuesStyle.Colors, "-R$ 100,70", true),
        Triple(CustomTextValuesStyle.Colors, "R$ 0,00", true),
        Triple(CustomTextValuesStyle.Colors, "-R$ 100,70", false),
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = allCasesStyleValues) { values ->
            CustomTextValues(
                value = values.second,
                style = values.first,
                showValue = values.third
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }

}


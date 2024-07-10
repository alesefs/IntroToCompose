package com.example.introtocompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.absoluteValue

@Composable
fun NumberToWords(
    number: Double
) {
    val num = number.absoluteValue
    val isNegative = number.compareTo(0.0)
    val negative = if (isNegative < 0) {
        "valor negativo, "
    } else {
        ""
    }

    Box (modifier = Modifier.fillMaxWidth()) {
        Text(text = "$number -> $negative ${num.numberToWords()}")
    }
}

@Preview(showBackground = true)
@Composable
fun NumberToWordsPreview() {
    Column {
        NumberToWords(-0.73)
        NumberToWords(1.0)
        NumberToWords(-21.01)
        NumberToWords(105.0)
        NumberToWords(234.0)
        NumberToWords(10034.78)
        NumberToWords(110134.41)
        NumberToWords(9110034.41)
        NumberToWords(-1110034.41)
    }
}

internal fun Double.numberToWords(): String {
    return numberToWordz(this)
}

fun numberToWordz(number: Double): String {
    if (number == 0.0) return "zero"

    val units = arrayOf(
        "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove",
        "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"
    )

    val tens = arrayOf(
        "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"
    )

    val hundreds = arrayOf(
        "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"
    )

    val thousands = arrayOf(
        "", "mil", "milhão", "bilhão", "trilhão"
    )

    fun convertPart(number: Int): String {
        if (number == 0) return ""

        var word = ""

        if (number < 20) {
            word = units[number]
        } else if (number < 100) {
            word = tens[number / 10]
            if (number % 10 != 0) word += " e " + units[number % 10]
        } else {
            word = hundreds[number / 100]
            if (number % 100 != 0) {
                val remainder = number % 100
                if (remainder < 20) {
                    word += " e " + units[remainder]
                } else {
                    word += " e " + tens[remainder / 10]
                    if (remainder % 10 != 0) word += " e " + units[remainder % 10]
                }
            } else if (number == 100) {
                word = "cem"
            }
        }

        return word
    }

    fun convert(number: Long): String {
        if (number == 0L) return "zero"

        var num = number
        var idx = 0
        var words = ""

        while (num > 0) {
            val part = (num % 1000).toInt()
            if (part > 0) {
                val partWord = convertPart(part)
                words = if (idx > 0) {
                    if (part == 1 && idx == 1) {
                        thousands[idx] + (if (words.isNotEmpty()) " e " else "") + words
                    } else {
                        "$partWord " + (
                            if (part > 1 && idx > 1) {
                                thousands[idx].dropLast(2) + "ões"
                            } else {
                                thousands[idx]
                            }
                        ) + (
                            if (words.isNotEmpty()) " e " else ""
                        ) + words
                    }
                } else {
                    partWord
                }
            }
            num /= 1000
            idx++
        }

        return words
    }

    val integerPart = number.toLong()
    val decimalPart = ((number - integerPart) * 100).toInt()

    val integerWords = convert(integerPart)
    val decimalWords =
        if (decimalPart > 0)
            if (integerPart > 0) {
                "e " + convertPart(decimalPart) + " centavos"
            } else {
                convertPart(decimalPart) + " centavos"
            }
        else
            ""

    val finalValue =
        if (integerWords.equals("zero", ignoreCase = true)) {
            decimalWords.trim()
        } else if (integerWords.equals("um", ignoreCase = true)) {
            "$integerWords real $decimalWords".trim()
        } else {
            "$integerWords reais $decimalWords".trim()
        }

    return finalValue
}

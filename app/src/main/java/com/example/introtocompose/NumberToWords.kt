package com.example.introtocompose

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import kotlin.math.absoluteValue

@Composable
fun NumberToWords(
    number: Double
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                contentDescription = number.numberToWords()
            },
        text = "$number"
    )
}

@Composable
fun BigDecimalToWords(
    number: BigDecimal
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                contentDescription = number.bigDecimalToWords()
            },
        text = "$number"
    )
}

@Preview(showBackground = true)
@Composable
fun NumberToWordsPreview() {
    Column {
        Text(text = "Double - atual")

        Spacer(modifier = Modifier.size(8.dp))

        NumberToWords(-0.73)
        NumberToWords(1.0)
        NumberToWords(-21.01)
        NumberToWords(105.0)
        NumberToWords(234.0)
        NumberToWords(10034.78)
        NumberToWords(110134.41)
        NumberToWords(9110034.41)
        NumberToWords(10000000.99)
        NumberToWords(-1110034.41)
        NumberToWords(-10000000.99)

        Spacer(modifier = Modifier.size(12.dp))

        Text(text = "BigDecimal - possivel mudança")

        Spacer(modifier = Modifier.size(8.dp))

        BigDecimalToWords((-0.73).toBigDecimal())
        BigDecimalToWords(1.0.toBigDecimal())
        BigDecimalToWords((-21.01).toBigDecimal())
        BigDecimalToWords(105.0.toBigDecimal())
        BigDecimalToWords(234.0.toBigDecimal())
        BigDecimalToWords(10034.78.toBigDecimal())
        BigDecimalToWords(110134.41.toBigDecimal())
        BigDecimalToWords(9110034.41.toBigDecimal())
        BigDecimalToWords(10000000.99.toBigDecimal())
        BigDecimalToWords((-1110030.42).toBigDecimal())
        BigDecimalToWords((-10000000.99).toBigDecimal())
    }
}

internal fun Double.numberToWords(): String = this.toBigDecimal().bigDecimalToWords()

internal fun BigDecimal.bigDecimalToWords(): String {
    val numberReceive = this

    val isNegative = numberReceive.compareTo(0.0.toBigDecimal())
    val negative = if (isNegative < 0) {
        "valor negativo, "
    } else {
        ""
    }

    val number = numberReceive.abs()

    if (number == 0.0.toBigDecimal()) return "zero"
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
    val decimalPart = ((number - integerPart.toBigDecimal()).times(100.0.toBigDecimal())).toInt()
    val integerWords = convert(integerPart)
    val decimalWords =
        if (decimalPart > 0)
            if (integerPart > 0) {
                if (decimalPart == 1) {
                    "e " + convertPart(decimalPart) + " centavo"
                } else {
                    "e " + convertPart(decimalPart) + " centavos"
                }
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
    return "$negative $finalValue"
}

internal fun BigDecimal.toBigDecimalStringArray(context: Context): String {
    val numberReceive = this

    val emptyString = context.getString(R.string.number_to_word_empty)
    val andString = context.getString(R.string.number_to_word_and)
    val zeroString = context.getString(R.string.number_to_word_zero)
    val oneString = context.getString(R.string.number_to_word_one)
    val hundredString = context.getString(R.string.number_to_word_hundred)
    val units = context.resources.getStringArray(R.array.number_to_word_units)
    val tens = context.resources.getStringArray(R.array.number_to_word_tens)
    val hundreds = context.resources.getStringArray(R.array.number_to_word_hundreds)
    val thousands = context.resources.getStringArray(R.array.number_to_word_thousands)
    val negativeString = context.getString(R.string.number_to_word_negative)
    val isNegative = numberReceive.compareTo(0.0.toBigDecimal())
    val negative = if (isNegative < 0) {
        negativeString
    } else {
        emptyString
    }

    val cent = context.resources.getString(R.string.number_to_word_cent)
    val cents = context.resources.getString(R.string.number_to_word_cents)
    val moneyUnit = context.resources.getString(R.string.number_to_word_money_unit)
    val moneyMult = context.resources.getString(R.string.number_to_word_money_mult)

    val number = numberReceive.abs()

    if (number == 0.0.toBigDecimal()) return zeroString

    fun convertPart(number: Int): String {
        if (number == 0) return emptyString
        var word: String
        if (number < 20) {
            word = units[number]
        } else if (number < 100) {
            word = tens[number / 10]
            if (number % 10 != 0) word += andString + units[number % 10]
        } else {
            word = hundreds[number / 100]
            if (number % 100 != 0) {
                val remainder = number % 100
                if (remainder < 20) {
                    word += andString + units[remainder]
                } else {
                    word += andString + tens[remainder / 10]
                    if (remainder % 10 != 0) word += andString + units[remainder % 10]
                }
            } else if (number == 100) {
                word = hundredString
            }
        }
        return word
    }

    fun convert(number: Long): String {
        if (number == 0L) return zeroString
        var num = number
        var idx = 0
        var words = emptyString
        while (num > 0) {
            val part = (num % 1000).toInt()
            if (part > 0) {
                val partWord = convertPart(part)
                words = if (idx > 0) {
                    if (part == 1 && idx == 1) {
                        thousands[idx] + (if (words.isNotEmpty()) andString else emptyString) + words
                    } else {
                        "$partWord " + (
                                if (part > 1 && idx > 1) {
                                    thousands[idx].dropLast(2) + context.getString(R.string.number_to_word_millions)
                                } else {
                                    thousands[idx]
                                }
                                ) + (
                                if (words.isNotEmpty()) andString else emptyString
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
    val decimalPart = ((number - integerPart.toBigDecimal()).times(100.0.toBigDecimal())).toInt()
    val integerWords = convert(integerPart)
    val decimalWords =
        if (decimalPart > 0)
            if (integerPart > 0) {
                if (decimalPart == 1) {
                    andString + convertPart(decimalPart) + " $cent"
                } else {
                    andString + convertPart(decimalPart) + " $cents"
                }
            } else {
                convertPart(decimalPart) + " $cents"
            }
        else
            emptyString

    val finalValue =
        if (integerWords.equals(zeroString, ignoreCase = true)) {
            decimalWords.trim()
        } else if (integerWords.equals(oneString, ignoreCase = true)) {
            "$integerWords $moneyUnit $decimalWords".trim()
        } else {
            "$integerWords $moneyMult $decimalWords".trim()
        }
    return "$negative $finalValue"
}
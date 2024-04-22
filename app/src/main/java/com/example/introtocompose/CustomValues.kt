package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import com.example.introtocompose.utils.thenIf
import kotlin.math.absoluteValue
import kotlin.random.Random


data class CustomIconAndDescription(
    val icon: ImageVector,
    val iconDescription: String?
)

//val customIconAndDescription: Pair<ImageVector, String?> = Pair(Icons.Default.AccountCircle, null)

@Composable
fun CustomValues(
    value: Double,
    text: String?,
    icon: CustomIconAndDescription?,
    showValue: Boolean = true,
    financialFractor: Boolean = false,
    darkContentColor: Boolean = true,
    absoluteColor: Boolean = false,
    inline: Boolean = false
) {
    val contentColor =
        if (darkContentColor) {
            Color.Black
        } else {
            Color.White
        }

    val contentValueColor =
        if (absoluteColor) {
            contentColor
        } else {
            if (!showValue) {
                contentColor
            } else {
                if (value > 0) {
                    Color.Green
                } else if (value < 0) {
                    Color.Red
                } else {
                    contentColor
                }
            }
        }

    if(!inline) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TitleRow(text, contentColor, icon, inline)

            ValueRow(value, showValue, contentValueColor, financialFractor, contentColor, inline)
        }
    } else {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleRow(text, contentColor, icon, inline)

            ValueRow(value, showValue, contentValueColor, financialFractor, contentColor, inline)
        }
    }
}

@Composable
private fun ValueRow(
    value: Double,
    showValue: Boolean,
    contentValueColor: Color,
    financialFractor: Boolean,
    contentColor: Color,
    inline: Boolean
) {
    Row(
        modifier = Modifier
            .thenIf(inline) {
                wrapContentWidth()
            }
            .thenIf(!inline) {
                fillMaxWidth()
            }
            .wrapContentHeight()
    ) {

        val spacing = if (value.toInt() != 0) {
            4.dp
        } else {
            0.dp
        }

        if (showValue) {
            Text(
                modifier = Modifier.padding(end = spacing),
                text = if (value > 0) {
                    "+"
                } else if (value < 0) {
                    "-"
                } else {
                    ""
                },
                color = contentValueColor,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            )
        }

        Text(
            text = "R$ ",
            color = contentValueColor,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        )

        if (showValue) {
            Text(
                text = if (financialFractor) {
                    String.format("%.4f", value.absoluteValue)
                } else {
                    String.format("%.2f", value.absoluteValue)
                },
                color = contentValueColor,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            )
        } else {
            Text(
                text = "••••••",
                color = contentColor,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
            )
        }

    }
}

@Composable
private fun TitleRow(
    text: String?,
    contentColor: Color,
    icon: CustomIconAndDescription?,
    inline: Boolean
) {
    Row(
        modifier = Modifier
            .thenIf(inline) {
                wrapContentWidth()
            }
            .thenIf(!inline) {
                fillMaxWidth()
            }
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        text?.let {
            Text(
                text = text,
                color = contentColor,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
        }

        icon?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = icon.icon,
                contentDescription = icon.iconDescription,
                tint = contentColor
            )
        }
    }
}


@Preview
@Composable
fun CustomValuesPreview() {
    IntroToComposeTheme {

        val showValue = remember {
            mutableStateOf(true)
        }

        val showValueText = remember {
            mutableStateOf("hide")
        }

        val decimals = remember {
            mutableStateOf(true)
        }

        val decimalsText = remember {
            mutableStateOf("2")
        }

        val contentColor = remember {
            mutableStateOf(true)
        }

        val contentColorText = remember {
            mutableStateOf("Light")
        }

        val value = remember {
            mutableDoubleStateOf(0.00)
        }

        val absoluteColor = remember {
            mutableStateOf(false)
        }

        val inline = remember {
            mutableStateOf(false)
        }

        Column (
            modifier = Modifier
                .background(Color.Gray)
        ) {
            CustomValues(
                value = value.doubleValue,
                text = "teste value",
                icon = CustomIconAndDescription(Icons.Default.AccountCircle, null),
                showValue = showValue.value,
                financialFractor = decimals.value,
                darkContentColor = contentColor.value,
                absoluteColor = absoluteColor.value,
                inline = inline.value
            )

            Spacer(modifier = Modifier.size(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    value.doubleValue =  Random.nextDouble(-1000.0000, 1000.0000)
                }) {
                    Text(text = "new value")
                }

                Button(onClick = {
                    value.doubleValue =  0.0000
                }) {
                    Text(text = "reset value")
                }

                Button(onClick = {
                    showValue.value = !showValue.value
                    showValueText.value = if (showValue.value) "hide" else "show"
                }) {
                    Text(text = showValueText.value)
                }

                Button(onClick = {
                    decimals.value = !decimals.value
                    decimalsText.value = if (decimals.value) "2" else "4"
                }) {
                    Text(text = "${decimalsText.value} dec")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    contentColor.value = !contentColor.value
                    contentColorText.value = if (contentColor.value) "Light" else "Dark"
                }) {
                    Text(text = contentColorText.value)
                }

                Button(onClick = {
                    absoluteColor.value = !absoluteColor.value
                }) {
                    Text(text = "absoluteColor")
                }

                Button(onClick = {
                    inline.value = !inline.value
                }) {
                    Text(text = "inline")
                }
            }

        }
    }
}
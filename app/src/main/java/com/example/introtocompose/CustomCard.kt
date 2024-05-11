package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

data class CustomCardCornerRadius(
    val radius: Dp,
    val topStart: Boolean,
    val topEnd: Boolean,
    val bottomStart: Boolean,
    val bottomEnd: Boolean,
)

data class CustomCardStroke(
    val size: Dp,
    val color: Color
)

data class CustomCardDecoration(
    val size: Dp,
    val color: Color
)

sealed class CustomCardStyle(
    val backgroundColor: Color,
    val cardRadius: CustomCardCornerRadius,
    val strokeStyle: CustomCardStroke? = null,
    val elevationValue: Dp = 0.dp
) {
    class Base(
        cardRadiusBase: CustomCardCornerRadius,
    ) : CustomCardStyle(
        backgroundColor = Color.White,
        cardRadius = cardRadiusBase
    )

    class Stroke(
        backgroundColor: Color? = null,
        cardRadiusStroke: CustomCardCornerRadius,
        strokeStyle: CustomCardStroke
    ) : CustomCardStyle(
        backgroundColor = backgroundColor ?: Color.White,
        cardRadius = cardRadiusStroke,
        strokeStyle = strokeStyle
    )

    class Elevation(
        backgroundColor: Color? = null,
        cardRadiusElevation: CustomCardCornerRadius,
        elevationValue: Dp
    ) : CustomCardStyle(
        backgroundColor = backgroundColor ?: Color.White,
        cardRadius = cardRadiusElevation,
        elevationValue = elevationValue
    )

    class Emphasis(
        cardRadiusEmphasis: CustomCardCornerRadius,
    ) : CustomCardStyle(
        backgroundColor = Color.Gray,
        cardRadius = cardRadiusEmphasis
    )

}

@Composable
fun CustomCard(
    style: CustomCardStyle,
    modifier: Modifier = Modifier,
    decorated: CustomCardDecoration? = null,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = style.backgroundColor
            ),
            shape = with(style.cardRadius) {
                RoundedCornerShape(
                    topStart = if (topStart) radius else 0.dp,
                    topEnd = if (topEnd) radius else 0.dp,
                    bottomStart = if (bottomStart) radius else 0.dp,
                    bottomEnd = if (bottomEnd) radius else 0.dp,
                )
            },
            elevation = CardDefaults.cardElevation(
                defaultElevation = style.elevationValue
            ),
            border = with(style.strokeStyle) {
                BorderStroke(this?.size ?: 0.dp, this?.color ?: Color.Transparent)
            }
        ) {
            Row (modifier = Modifier.fillMaxSize()) {
                decorated?.let {
                    Box(
                        modifier = Modifier
                            .width(decorated.size)
                            .fillMaxHeight()
                            .background(decorated.color)
                            .clip(
                                shape = with(style.cardRadius) {
                                    RoundedCornerShape(
                                        topStart = if (topStart) radius else 0.dp,
                                        topEnd = 0.dp,
                                        bottomStart = if (bottomStart) radius else 0.dp,
                                        bottomEnd = 0.dp,
                                    )
                                }
                            ),
                    )
                }

                content.invoke()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomCardPreview() {
    IntroToComposeTheme {
        Box(
            modifier = Modifier.fillMaxWidth().height(210.dp)
        ) {
            CustomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                style = CustomCardStyle.Elevation(
                    Color.LightGray,
                    CustomCardCornerRadius(
                        radius = 16.dp,
                        topStart = true,
                        topEnd = true,
                        bottomStart = true,
                        bottomEnd = true
                    ),
                    elevationValue = 2.dp
                ),
                decorated = CustomCardDecoration(size = 12.dp, color = Color.Green),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "NewmanHaas ATCKS",
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomCardAllCasesPreview() {
    IntroToComposeTheme {

        val allCasesCardStyles = listOf(
            CustomCardStyle.Base(
                cardRadiusBase = CustomCardCornerRadius(
                    radius = 16.dp,
                    topStart = true,
                    topEnd = true,
                    bottomStart = true,
                    bottomEnd = true
                )
            ),
            CustomCardStyle.Stroke(
                backgroundColor = Color.LightGray,
                cardRadiusStroke = CustomCardCornerRadius(
                    radius = 16.dp,
                    topStart = true,
                    topEnd = true,
                    bottomStart = true,
                    bottomEnd = true
                ),
                strokeStyle = CustomCardStroke(2.dp, Color.Blue)
            ),
            CustomCardStyle.Elevation(
                backgroundColor = Color.White,
                cardRadiusElevation = CustomCardCornerRadius(
                    radius = 16.dp,
                    topStart = true,
                    topEnd = true,
                    bottomStart = true,
                    bottomEnd = true
                ),
                elevationValue = 2.dp
            ),
            CustomCardStyle.Emphasis(
                cardRadiusEmphasis = CustomCardCornerRadius(
                    radius = 16.dp,
                    topStart = true,
                    topEnd = true,
                    bottomStart = true,
                    bottomEnd = true
                )
            )
        )

        Column {
            allCasesCardStyles.forEach { style ->
                CustomCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    style = style
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "NewmanHaas ATCKS",
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.size(8.dp))
            }
        }

    }
}
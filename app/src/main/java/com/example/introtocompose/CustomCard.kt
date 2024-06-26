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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
        cardRadius: CustomCardCornerRadius,
    ) : CustomCardStyle(
        backgroundColor = Color.White,
        cardRadius = cardRadius
    )

    class Stroke(
        backgroundColor: Color = Color.White,
        cardRadius: CustomCardCornerRadius,
        strokeStyle: CustomCardStroke
    ) : CustomCardStyle(
        backgroundColor = backgroundColor,
        cardRadius = cardRadius,
        strokeStyle = strokeStyle
    )

    class Elevation(
        backgroundColor: Color = Color.White,
        cardRadius: CustomCardCornerRadius,
        elevationValue: Dp
    ) : CustomCardStyle(
        backgroundColor = backgroundColor,
        cardRadius = cardRadius,
        elevationValue = elevationValue
    )

    class Emphasis(
        cardRadius: CustomCardCornerRadius,
    ) : CustomCardStyle(
        backgroundColor = Color.LightGray,
        cardRadius = cardRadius
    )

}

@Composable
fun CustomCard(
    style: CustomCardStyle,
    modifier: Modifier = Modifier,
    decorated: CustomCardDecoration? = null,
    content: @Composable () -> Unit
) {
//    var heightIs by remember { mutableStateOf(0.dp) }
//    val currentDensity = LocalDensity.current

    Box(modifier = modifier) {
        Card(
//            modifier = Modifier.onGloballyPositioned { coordinates ->
//                heightIs = with(currentDensity) { coordinates.size.height.toDp() }
//            },
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
            /*Row {
                decorated?.let {
                    Box(
                        modifier = Modifier
                            .background(decorated.color)
                            .width(decorated.size)
                            .height(heightIs)
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
            }*/

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (decorateRef, contentRef) = createRefs()
                val guidelineStartContent = createGuidelineFromStart(decorated?.size ?: 0.dp)

                decorated?.let {
                    Box(
                        modifier = Modifier
                            .background(decorated.color)
                            .constrainAs(decorateRef) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(guidelineStartContent)
                                width = Dimension.value(decorated.size)
                                height = Dimension.fillToConstraints
                            }
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

                Box(
                    modifier = Modifier.constrainAs(contentRef) {
                        top.linkTo(parent.top)
                        start.linkTo(decorateRef.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                ) {
                    content.invoke()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomCardPreview() {
    IntroToComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        ) {
            CustomCard(
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                decorated = CustomCardDecoration(size = 12.dp, color = Color.Green)
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
                cardRadius = CustomCardCornerRadius(
                    radius = 16.dp,
                    topStart = true,
                    topEnd = true,
                    bottomStart = true,
                    bottomEnd = true
                )
            ),
            CustomCardStyle.Stroke(
                backgroundColor = Color.Yellow,
                cardRadius = CustomCardCornerRadius(
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
                cardRadius = CustomCardCornerRadius(
                    radius = 16.dp,
                    topStart = true,
                    topEnd = true,
                    bottomStart = true,
                    bottomEnd = true
                ),
                elevationValue = 2.dp
            ),
            CustomCardStyle.Emphasis(
                cardRadius = CustomCardCornerRadius(
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
                    style = style,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    decorated = CustomCardDecoration(8.dp, Color.Magenta)
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
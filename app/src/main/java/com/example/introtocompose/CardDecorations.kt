package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
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

sealed class CardDecorationsStyle {
    class Standard(
        val bgColor: Color = Color.White,
        val cornerShape: Dp = 0.dp
    ) : CardDecorationsStyle()

    class Border(
        val bgColor: Color = Color.White,
        val cornerShape: Dp = 0.dp,
        val borderColor: Color = Color.Black,
        val borderSize: Dp = 1.dp
    ) : CardDecorationsStyle()

    class Shadowed(
        val bgColor: Color = Color.White,
        val cornerShape: Dp = 0.dp,
        val elevation: Dp = 1.dp
    ) : CardDecorationsStyle()

    class Decorated(
        val bgColor: Color = Color.White,
        val cornerShape: Dp = 0.dp,
        val decorationSize: Dp = 12.dp,
        val decorationColor: Color = Color.Yellow
    ) : CardDecorationsStyle()
}

@Composable
fun CardDecoration(
    style: CardDecorationsStyle,
    modifier: Modifier = Modifier,
    composable: @Composable ColumnScope.() -> Unit = {}
) {
    Row (
        modifier.padding(
            if (style is CardDecorationsStyle.Shadowed) {
                style.elevation
            } else {
                0.dp
            }
        )
    ) {
        if (style is CardDecorationsStyle.Decorated) {
            Box(
                modifier = Modifier
                    .width(style.decorationSize)
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topStart = CornerSize(style.cornerShape),
                            topEnd = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(style.cornerShape)
                        )
                    )
                    .background(style.decorationColor)
            )
        }

        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = when (style) {
                    is CardDecorationsStyle.Border -> style.bgColor
                    is CardDecorationsStyle.Decorated -> style.bgColor
                    is CardDecorationsStyle.Shadowed -> style.bgColor
                    is CardDecorationsStyle.Standard -> style.bgColor
                }
            ),
            shape = when (style) {
                is CardDecorationsStyle.Border -> RoundedCornerShape(style.cornerShape)
                is CardDecorationsStyle.Decorated -> RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = style.cornerShape,
                    bottomStart = 0.dp,
                    bottomEnd = style.cornerShape,
                )
                is CardDecorationsStyle.Shadowed -> RoundedCornerShape(style.cornerShape)
                is CardDecorationsStyle.Standard -> RoundedCornerShape(style.cornerShape)
            },
            elevation = CardDefaults.cardElevation(
                defaultElevation = when (style) {
                    is CardDecorationsStyle.Border -> 0.dp
                    is CardDecorationsStyle.Decorated -> 0.dp
                    is CardDecorationsStyle.Shadowed -> style.elevation
                    is CardDecorationsStyle.Standard -> 0.dp
                }
            ),
            border = when (style) {
                is CardDecorationsStyle.Border -> BorderStroke(style.borderSize, style.borderColor)
                is CardDecorationsStyle.Decorated -> null
                is CardDecorationsStyle.Shadowed -> null
                is CardDecorationsStyle.Standard -> null
            },
            content = composable
        )
    }
}

@Preview
@Composable
fun CardDecorationPreview() {
    IntroToComposeTheme {
        CardDecoration(
            style = CardDecorationsStyle.Decorated(
                bgColor = Color.Yellow,
                cornerShape = 12.dp,
                decorationSize = 12.dp,
                decorationColor = Color.Red
            ),
            /*style = CardDecorationsStyle.Shadowed(
                bgColor = Color.Yellow,
                cornerShape = 12.dp,
                elevation = 2.dp
            ),*/
            /*style = CardDecorationsStyle.Border(
                bgColor = Color.Yellow,
                cornerShape = 12.dp,
                borderColor = Color.Red,
                borderSize = 2.dp
            ),*/
            modifier = Modifier.size(150.dp, 100.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
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
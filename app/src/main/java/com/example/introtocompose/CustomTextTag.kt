package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Composable
fun CustomTextTag1(
    text: String,
    backgroundColor: Color,
    borderColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(size = 999.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(size = 999.dp))
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .wrapContentWidth()
            .height(16.dp)
            .wrapContentSize()
        ,
        text = text,
        color = contentColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        textAlign = TextAlign.Center,
        softWrap = true,

    )
}

@Composable
fun CustomTextTag2(
    text: String,
    backgroundColor: Color,
    borderColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = contentColor,
        style = TextStyle(
            fontSize = 12.sp,
            lineHeight = 12.sp,
            textAlign = TextAlign.Center,
        ),
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    Color(backgroundColor.value),
                    cornerRadius = CornerRadius(999.dp.toPx())
                )
            }
            .border(1.dp, borderColor, RoundedCornerShape(size = 999.dp))
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .wrapContentWidth()
            .height(16.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        softWrap = true,
    )
}

@Preview
@Composable
fun CustomTextTagPreview() {
    IntroToComposeTheme {
        Column {
            CustomTextTag1(
                text = "Joao Joao Jose",
                backgroundColor = Color.Cyan,
                borderColor = Color.White,
                contentColor = Color.White
            )
            Spacer(modifier = Modifier.size(8.dp))
            CustomTextTag2(
                text = "Joao Joao Jose",
                backgroundColor = Color.Green,
                borderColor = Color.Yellow,
                contentColor = Color.Yellow
            )
        }

    }
}
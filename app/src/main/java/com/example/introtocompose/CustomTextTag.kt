package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Composable
fun CustomTextTag(

) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(size = 999.dp))
            .background(Color.Yellow)
            .border(1.dp, Color.Red, RoundedCornerShape(size = 999.dp))
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .wrapContentWidth()
            .height(16.dp)
        ,
        text = "joao joao joao",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        softWrap = true,
        lineHeight = 12.sp,

    )
}

@Preview
@Composable
fun CustomTextPreview() {
    IntroToComposeTheme {
        CustomTextTag()
    }
}
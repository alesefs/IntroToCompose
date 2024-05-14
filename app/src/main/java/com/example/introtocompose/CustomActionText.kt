package com.example.introtocompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomActionText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Red,
    )
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.padding(all = 1.dp),
            text = text,
            style = style,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
fun CustomActionTextPreview() {
    CustomActionText("text")
}
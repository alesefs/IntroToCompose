package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import com.example.introtocompose.utils.thenIf

object CustomIconsColor {
    val LightGreen = Color(0xFF90EE90)
    val DarkGreen = Color(0xFF004100)
    val LightYellow = Color(0xFFfefeb1)
    val DarkYellow = Color(0xFF494900)
    val LightRed = Color(0xFFfeb1b1)
    val DarkRed = Color(0xFF490000)
    val LightCyan = Color(0xFFc9f0fe)
    val DarkCyan = Color(0xFF002939)
}

enum class CustomIconsStyle(val shapeColor: Color, val iconColor: Color) {
    Success(CustomIconsColor.LightGreen, CustomIconsColor.DarkGreen),
    Warning(CustomIconsColor.LightYellow, CustomIconsColor.DarkYellow),
    Error(CustomIconsColor.LightRed, CustomIconsColor.DarkRed),
    Info(CustomIconsColor.LightCyan, CustomIconsColor.DarkCyan),
    Neutral(Color.LightGray, Color.DarkGray)
}

enum class CustomIconsSize(val padding: Dp, val iconSize: Dp, val shapeSize: Dp) {
    Large(12.dp, 24.dp, 48.dp),
    Medium(8.dp, 16.dp, 32.dp),
    Small(4.dp, 16.dp, 24.dp)
}

@Preview(showBackground = true)
@Composable
fun CustomIconsPreview() {
    IntroToComposeTheme {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomIcons(
                CustomIconsSize.Large,
                CustomIconsStyle.Success,
                showShape = true
            )

            CustomIcons(
                CustomIconsSize.Large,
                CustomIconsStyle.Success,
                showShape = false
            )

            CustomIcons(
                CustomIconsSize.Medium,
                CustomIconsStyle.Warning,
                showShape = true
            )

            CustomIcons(
                CustomIconsSize.Medium,
                CustomIconsStyle.Warning,
                showShape = false
            )

            CustomIcons(
                CustomIconsSize.Small,
                CustomIconsStyle.Error,
                showShape = true
            )

            CustomIcons(
                CustomIconsSize.Small,
                CustomIconsStyle.Error,
                showShape = false
            )

            CustomIcons(
                CustomIconsSize.Medium,
                CustomIconsStyle.Info,
                showShape = true
            )

            CustomIcons(
                CustomIconsSize.Medium,
                CustomIconsStyle.Info,
                showShape = false
            )

            CustomIcons(
                CustomIconsSize.Medium,
                CustomIconsStyle.Neutral,
                showShape = true
            )

            CustomIcons(
                CustomIconsSize.Medium,
                CustomIconsStyle.Neutral,
                showShape = false
            )
        }
    }
}

@Composable
fun CustomIcons(
    size: CustomIconsSize,
    style: CustomIconsStyle,
    showShape: Boolean,
) {
    Box(modifier = Modifier
        .size(size.shapeSize)
        .thenIf(showShape) {
            clip(CircleShape).background(style.shapeColor)
        }
        .padding(size.padding)
    ) {
        Icon(
            Icons.Filled.Add,
            null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(size.iconSize),
            tint = style.iconColor
        )
    }
}
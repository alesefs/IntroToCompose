package com.example.introtocompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

sealed class CustomActionStyle {
    class Icon(
        val actionIcon: Painter,
        val action: () -> Unit
    ) : CustomActionStyle()

    class Text(
        val actionText: String,
        val style: TextStyle? = null,
        val action: () -> Unit
    ) : CustomActionStyle()
}




@Composable
fun CustomAction(
    style: CustomActionStyle,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when(style) {
            is CustomActionStyle.Icon -> {
                CustomIcons(
                    modifier = Modifier.clickable {
                        style.action.invoke()
                    },
                    size = CustomIconsSize.Large,
                    style = CustomIconStyle.Custom(
                        shapedColor = Color.Transparent,
                        iconeColor = Color.Red,
                        showCustomShape = false
                    ),
                    icon = style.actionIcon
                )
            }
            is CustomActionStyle.Text -> {
                CustomActionText(
                    modifier = Modifier.clickable {
                        style.action.invoke()
                    },
                    text = style.actionText,
                    style = style.style ?: TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomActionPreview() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAction(style = CustomActionStyle.Text("text", style = null) {})
        CustomAction(style = CustomActionStyle.Icon(rememberVectorPainter(Icons.Sharp.Info)) {})
    }
}
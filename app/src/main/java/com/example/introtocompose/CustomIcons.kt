package com.example.introtocompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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

/*enum class CustomIconsStyle(val shapeColor: Color, val iconColor: Color) {
    Success(CustomIconsColor.LightGreen, CustomIconsColor.DarkGreen),
    Warning(CustomIconsColor.LightYellow, CustomIconsColor.DarkYellow),
    Error(CustomIconsColor.LightRed, CustomIconsColor.DarkRed),
    Info(CustomIconsColor.LightCyan, CustomIconsColor.DarkCyan),
    Neutral(Color.LightGray, Color.DarkGray)
}*/

sealed class CustomIconStyle(
    val shapeColor: Color,
    val iconColor: Color,
    val borderColor: Color,
    val showShape: Boolean = true,
    val showBorder: Boolean = false
) {
    class Success : CustomIconStyle(
        shapeColor = CustomIconsColor.LightGreen,
        iconColor = CustomIconsColor.DarkGreen,
        borderColor = CustomIconsColor.DarkGreen,
    )

    class Warning : CustomIconStyle(
        shapeColor = CustomIconsColor.LightYellow,
        iconColor = CustomIconsColor.DarkYellow,
        borderColor = CustomIconsColor.DarkYellow,
    )

    class Error : CustomIconStyle(
        shapeColor = CustomIconsColor.LightRed,
        iconColor = CustomIconsColor.DarkRed,
        borderColor = CustomIconsColor.DarkRed
    )

    class Info : CustomIconStyle(
        shapeColor = CustomIconsColor.LightCyan,
        iconColor = CustomIconsColor.DarkCyan,
        borderColor = CustomIconsColor.DarkCyan,
    )

    class Neutral(val showNeutralShape: Boolean = true) : CustomIconStyle(
        shapeColor = Color.LightGray,
        iconColor = Color.DarkGray,
        borderColor = Color.DarkGray,
        showShape = showNeutralShape
    )

    data class Custom(
        val shapedColor: Color = Color.LightGray,
        val iconeColor: Color = Color.DarkGray,
        val borderedColor: Color = Color.DarkGray,
        val showCustomShape: Boolean = true,
        val showCustomBorder: Boolean = false
    ) : CustomIconStyle(
        shapeColor = shapedColor,
        iconColor = iconeColor,
        borderColor = borderedColor,
        showShape = showCustomShape,
        showBorder = showCustomBorder
    )
}

enum class CustomIconsSize(val padding: Dp, val iconSize: Dp, val shapeSize: Dp) {
    Large(12.dp, 24.dp, 48.dp),
    Medium(8.dp, 16.dp, 32.dp),
    Small(4.dp, 16.dp, 24.dp)
}

/*@Preview(showBackground = true)
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
}*/

@Preview(showBackground = true)
@Composable
fun CustomIconsPreview2() {
    IntroToComposeTheme {
        val data = listOf(
            Triple(
                CustomIconsSize.Large,
                CustomIconStyle.Success(),
                Icons.Filled.CheckCircle
            ),
            Triple(
                CustomIconsSize.Medium,
                CustomIconStyle.Success(),
                Icons.Filled.CheckCircle
            ),
            Triple(
                CustomIconsSize.Small,
                CustomIconStyle.Success(),
                Icons.Filled.CheckCircle
            )
        )

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                data.forEach { item ->
                    val size = item.first
                    val style = item.second
                    val icon = rememberVectorPainter(image = item.third)

                    CustomIcons(size = size, style = style, icon = icon)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomIconsPreview() {
    IntroToComposeTheme {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcons(
                    CustomIconsSize.Large,
                    CustomIconStyle.Success(),
                )

                CustomIcons(
                    CustomIconsSize.Medium,
                    CustomIconStyle.Success(),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Success(),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Success(),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcons(
                    CustomIconsSize.Large,
                    CustomIconStyle.Warning(),
                )

                CustomIcons(
                    CustomIconsSize.Medium,
                    CustomIconStyle.Warning(),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Warning(),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcons(
                    CustomIconsSize.Large,
                    CustomIconStyle.Error(),
                )

                CustomIcons(
                    CustomIconsSize.Medium,
                    CustomIconStyle.Error(),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Error(),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcons(
                    CustomIconsSize.Large,
                    CustomIconStyle.Info(),
                )

                CustomIcons(
                    CustomIconsSize.Medium,
                    CustomIconStyle.Info(),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Info(),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcons(
                    CustomIconsSize.Large,
                    CustomIconStyle.Custom(Color.Magenta, Color.Cyan),
                )

                CustomIcons(
                    CustomIconsSize.Medium,
                    CustomIconStyle.Custom(Color.Magenta, Color.Cyan),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Custom(Color.Magenta, Color.Cyan),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Custom(Color.Magenta, Color.Cyan, showCustomShape = false),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomIcons(
                    CustomIconsSize.Large,
                    CustomIconStyle.Neutral(showNeutralShape = true),
                )

                CustomIcons(
                    CustomIconsSize.Medium,
                    CustomIconStyle.Neutral(showNeutralShape = true),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Neutral(showNeutralShape = true),
                )

                CustomIcons(
                    CustomIconsSize.Small,
                    CustomIconStyle.Neutral(showNeutralShape = false),
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomIconPreview2() {
    IntroToComposeTheme {

        val allCasesStyles = listOf(
            CustomIconStyle.Success(),
            CustomIconStyle.Warning(),
            CustomIconStyle.Error(),
            CustomIconStyle.Info(),
            CustomIconStyle.Neutral(showNeutralShape = true),
            CustomIconStyle.Neutral(showNeutralShape = false),
            CustomIconStyle.Custom(
                shapedColor = Color.Cyan,
                iconeColor = Color.Black,
                showCustomShape = true
            ),
            CustomIconStyle.Custom(
                shapedColor = Color.Cyan,
                iconeColor = Color.Red,
                showCustomShape = false
            ),
            CustomIconStyle.Custom(
                shapedColor = Color.Cyan,
                iconeColor = Color.Red,
                borderedColor = Color.Red,
                showCustomShape = false,
                showCustomBorder = true
            ),
            CustomIconStyle.Custom(
                shapedColor = Color.Cyan,
                iconeColor = Color.Red,
                borderedColor = Color.Red,
                showCustomShape = true,
                showCustomBorder = true
            ),
        )

        val allCasesSizes = listOf(
            CustomIconsSize.Large,
            CustomIconsSize.Medium,
            CustomIconsSize.Small,
        )

        Column {
            allCasesStyles.forEach { style ->
                Column {
                    Row {
                        allCasesSizes.forEach { size ->
                            CustomIcons(size = size, style = style)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomIcons(
    size: CustomIconsSize,
    style: CustomIconStyle,
    modifier: Modifier = Modifier,
    icon: Painter = rememberVectorPainter(image = Icons.Filled.CheckCircle),
    contentDescription: String? = null
) {
    /*val showShaped = when(style) {
        is CustomIconStyle.Custom -> style.showShape
        is CustomIconStyle.Neutral -> style.showShape
        else -> true
    }*/
    //val showShaped = if (style is CustomIconStyle.Neutral) style.showShape else true

    /*Box(modifier = Modifier
        .thenIf(showShaped) {
            size(size.shapeSize)
            .clip(CircleShape)
            .background(style.shapeColor)
            .padding(size.padding)
        }
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(size.iconSize),
            colorFilter = ColorFilter.tint(style.iconColor, BlendMode.SrcIn)
        )
    }*/

    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .thenIf(style.showBorder && !style.showShape) {
                    size(size.shapeSize)
                        .border(width = 2.dp, color = style.borderColor, shape = CircleShape)
                        .padding(size.padding)
                }
                .thenIf(style.showShape && !style.showBorder) {
                    size(size.shapeSize)
                        .clip(CircleShape)
                        .background(style.shapeColor)
                        .padding(size.padding)
                }
                .thenIf(style.showShape && style.showBorder) {
                    size(size.shapeSize)
                        .border(width = 2.dp, color = style.borderColor, shape = CircleShape)
                        .clip(CircleShape)
                        .background(style.shapeColor)
                        .padding(size.padding)
                }
                .size(size.iconSize),
            painter = icon,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(style.iconColor, BlendMode.SrcIn)
        )
    }
}
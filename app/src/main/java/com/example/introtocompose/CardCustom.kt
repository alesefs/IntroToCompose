package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

enum class CardCustomTypes {
    Active,
    Border,
    Elevation,
    Deactivate
}

object CardCustomDefaults {
    fun backgroundColor(
        type: CardCustomTypes,
        backgroundColor: Color = Color.White,
        deactivateColor: Color = Color.LightGray
    ) : Color {
        return when(type) {
            CardCustomTypes.Deactivate -> deactivateColor
            else -> backgroundColor
        }
    }

    fun roundCornerShape(
        borderRadiusStyle: BorderRadiusStyle,
        borderRadiusValue: Dp
    ): RoundedCornerShape {
        return when(borderRadiusStyle) {
            BorderRadiusStyle.All -> RoundedCornerShape(size = borderRadiusValue)
            BorderRadiusStyle.None -> RoundedCornerShape(size = 0.dp)
            BorderRadiusStyle.TopStart -> RoundedCornerShape(topStart = borderRadiusValue)
            BorderRadiusStyle.TopEnd -> RoundedCornerShape(topEnd = borderRadiusValue)
            BorderRadiusStyle.BottomStart -> RoundedCornerShape(bottomStart = borderRadiusValue)
            BorderRadiusStyle.BottomEnd -> RoundedCornerShape(bottomEnd = borderRadiusValue)
            BorderRadiusStyle.Top -> RoundedCornerShape(topStart = borderRadiusValue, topEnd = borderRadiusValue)
            BorderRadiusStyle.Bottom -> RoundedCornerShape(bottomStart = borderRadiusValue, bottomEnd = borderRadiusValue)
            BorderRadiusStyle.Start -> RoundedCornerShape(bottomStart = borderRadiusValue, topStart = borderRadiusValue)
            BorderRadiusStyle.End -> RoundedCornerShape(bottomEnd = borderRadiusValue, topEnd = borderRadiusValue)
        }
    }

    fun borderSize(
        type: CardCustomTypes,
        size: BorderValues
    ) : Dp {
        return when(type) {
            CardCustomTypes.Border -> size.value
            else -> 0.dp
        }
    }

    fun borderColor(
        type: CardCustomTypes,
        color: Color = Color.Black
    ) : Color {
        return when(type) {
            CardCustomTypes.Border -> color
            else -> backgroundColor(type)
        }
    }

    fun elevationSize(
        type: CardCustomTypes,
        size: ElevationValues
    ) : Dp {
        return when(type) {
            CardCustomTypes.Elevation -> size.value
            else -> 0.dp
        }
    }
}

enum class BorderRadiusStyle {
    All,
    None,
    TopStart,
    TopEnd,
    BottomStart,
    BottomEnd,
    Top,
    Bottom,
    Start,
    End
}

enum class BorderRadiusValues(val value: Dp) {
    XSmall(2.dp),
    Small(4.dp),
    Medium(8.dp),
    Default(12.dp),
    Large(16.dp),
    XLarge(32.dp),
    Round(999.dp),
    Sharp(0.dp)
}

enum class BorderValues(val value: Dp) {
    Small(1.dp),
    Medium(2.dp),
    Large(4.dp),
    XLarge(8.dp)
}

enum class ElevationValues(val value: Dp) {
    Small(1.dp),
    Medium(2.dp),
    Large(4.dp),
    XLarge(8.dp)
}

sealed class CustomCardStyles {
    class Active(
        val bgColor: Color,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues>
    ) : CustomCardStyles() {
//        val color = CardCustomDefaults.backgroundColor(CardCustomTypes.Active)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )
    }

    class Deactivate(
        val bgColor: Color,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues>,
    ) : CustomCardStyles() {
//        val color = CardCustomDefaults.backgroundColor(CardCustomTypes.Deactivate)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )
    }

    class Border (
        val bgColor: Color,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues>,
        borderStyle: Pair<BorderValues, Color>
    ) : CustomCardStyles() {
//        val color = CardCustomDefaults.backgroundColor(CardCustomTypes.Border)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )

        val borderStroke = BorderStroke(
            CardCustomDefaults.borderSize(CardCustomTypes.Border, borderStyle.first),
            CardCustomDefaults.borderColor(CardCustomTypes.Border, borderStyle.second)
        )
    }

    class Elevation (
        val bgColor: Color,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues>,
        elevationValue: ElevationValues
    ) : CustomCardStyles() {
//        val color = CardCustomDefaults.backgroundColor(CardCustomTypes.Elevation)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )

        val elevationSize = CardCustomDefaults.elevationSize(CardCustomTypes.Elevation, elevationValue)
    }
}

@Composable
fun CardCustomSta(
    customCardStyles: CustomCardStyles,//???
//    type: CardCustomTypes,
//    shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues>,
//    borderSize: BorderValues,
//    elevationSize: ElevationValues,
    modifier: Modifier = Modifier,
    composable: @Composable ColumnScope.() -> Unit
) {
    Card (
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = when(customCardStyles) {
                is CustomCardStyles.Active -> customCardStyles.bgColor
                is CustomCardStyles.Border -> customCardStyles.bgColor
                is CustomCardStyles.Deactivate -> customCardStyles.bgColor
                is CustomCardStyles.Elevation -> customCardStyles.bgColor
            }
            //CardCustomDefaults.backgroundColor(type, Color.Green)
        ),
        shape = when(customCardStyles) {
            is CustomCardStyles.Active -> customCardStyles.shape
            is CustomCardStyles.Border -> customCardStyles.shape
            is CustomCardStyles.Deactivate -> customCardStyles.shape
            is CustomCardStyles.Elevation -> customCardStyles.shape
        }
        /*CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )*/,
        elevation = CardDefaults.cardElevation(
            defaultElevation = when(customCardStyles) {
                is CustomCardStyles.Elevation -> customCardStyles.elevationSize
                else -> 0.dp
            }
            //CardCustomDefaults.elevationSize(type, elevationValue)
        ),
        border = when(customCardStyles) {
            is CustomCardStyles.Border -> customCardStyles.borderStroke
            else -> null
        }
        /*BorderStroke(
            CardCustomDefaults.borderSize(type, borderSize),
            CardCustomDefaults.borderColor(type, Color.Red)
        )*/,
        content = composable
    )
}


@Preview
@Composable
fun CustomCardPreview() {
    IntroToComposeTheme {
        CardCustomSta(
//            type = CardCustomTypes.Border,
            customCardStyles = CustomCardStyles.Border(
                Color.Green,
                Pair(BorderRadiusStyle.TopEnd, BorderRadiusValues.Default),
                Pair(BorderValues.Medium, Color.Magenta)
            ),
//            shapeStyle = Pair(BorderRadiusStyle.TopEnd, BorderRadiusValues.Default),
//            borderSize = BorderValues.Medium,
//            elevationSize = ElevationValues.Medium
        ) {
            Box (
                modifier = Modifier.size(300.dp, 100.dp)
            ) {

            }
        }
    }
}

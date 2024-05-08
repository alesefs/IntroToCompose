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
import com.example.introtocompose.utils.BorderRadiusStyle
import com.example.introtocompose.utils.BorderRadiusValues
import com.example.introtocompose.utils.BorderValues
import com.example.introtocompose.utils.CardCustomTypes
import com.example.introtocompose.utils.ElevationValues

class CustomCardRadius(
    val radius: Dp,
    val topStart: Boolean = true,
    val topEnd: Boolean = true,
    val bottomStart: Boolean = true,
    val bottomEnd: Boolean = true,
)

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
            BorderRadiusStyle.TopStartBottomEnd -> RoundedCornerShape(topStart = borderRadiusValue, bottomEnd = borderRadiusValue)
            BorderRadiusStyle.TopEndBottomStart -> RoundedCornerShape(topEnd = borderRadiusValue, bottomStart = borderRadiusValue)
            BorderRadiusStyle.SharpTopStart -> RoundedCornerShape(topStart = 0.dp, topEnd = borderRadiusValue, bottomStart = borderRadiusValue, bottomEnd = borderRadiusValue)
            BorderRadiusStyle.SharpTopEnd -> RoundedCornerShape(topStart = borderRadiusValue, topEnd = 0.dp, bottomStart = borderRadiusValue, bottomEnd = borderRadiusValue)
            BorderRadiusStyle.SharpBottomStart -> RoundedCornerShape(topStart = borderRadiusValue, topEnd = borderRadiusValue, bottomStart = 0.dp, bottomEnd = borderRadiusValue)
            BorderRadiusStyle.SharpBottomEnd -> RoundedCornerShape(topStart = borderRadiusValue, topEnd = borderRadiusValue, bottomStart = borderRadiusValue, bottomEnd = 0.dp)
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

sealed class CustomCardStyles {
    class Active(
        bgColor: Color = Color.White,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues> = Pair(BorderRadiusStyle.All, BorderRadiusValues.Default),
    ) : CustomCardStyles() {
        val color = CardCustomDefaults.backgroundColor(type = CardCustomTypes.Active, backgroundColor = bgColor)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )
    }

    class Deactivate(
        deactivateColor: Color = Color.LightGray,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues> = Pair(BorderRadiusStyle.All, BorderRadiusValues.Default),
    ) : CustomCardStyles() {
        val color = CardCustomDefaults.backgroundColor(type = CardCustomTypes.Deactivate, deactivateColor = deactivateColor)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )
    }

    class Border (
        bgColor: Color = Color.White,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues> = Pair(BorderRadiusStyle.All, BorderRadiusValues.Default),
        borderStyle: Pair<BorderValues, Color> = Pair(BorderValues.Medium, Color.Black)
    ) : CustomCardStyles() {
        val color = CardCustomDefaults.backgroundColor(type = CardCustomTypes.Border, backgroundColor = bgColor)

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
        bgColor: Color = Color.White,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusValues> = Pair(BorderRadiusStyle.All, BorderRadiusValues.Default),
        elevationValue: ElevationValues = ElevationValues.Medium
    ) : CustomCardStyles() {
        val color = CardCustomDefaults.backgroundColor(type = CardCustomTypes.Elevation, backgroundColor = bgColor)

        val shape = CardCustomDefaults.roundCornerShape(
            shapeStyle.first,
            shapeStyle.second.value
        )

        val elevationSize = CardCustomDefaults.elevationSize(CardCustomTypes.Elevation, elevationValue)
    }
}

@Composable
fun CardCustomSta(
    customCardStyles: CustomCardStyles,
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
                is CustomCardStyles.Active -> customCardStyles.color
                is CustomCardStyles.Border -> customCardStyles.color
                is CustomCardStyles.Deactivate -> customCardStyles.color
                is CustomCardStyles.Elevation -> customCardStyles.color
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
fun CustomCardPreview2() {
    IntroToComposeTheme {
        CardCustomSta(
//            type = CardCustomTypes.Border,
            customCardStyles = CustomCardStyles.Border(
                bgColor = Color.Black,
                shapeStyle = Pair(BorderRadiusStyle.SharpBottomEnd, BorderRadiusValues.Default),
                borderStyle = Pair(BorderValues.XLarge, Color.Magenta)
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

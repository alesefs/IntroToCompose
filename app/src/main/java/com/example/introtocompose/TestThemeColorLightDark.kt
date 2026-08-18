package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LightDarkColorModel(
    val lightColor: Color = Color(0xFFFFFFFF),
    val lightColorName: String = "White",
    val darkColor: Color = Color(0xFF000000),
    val darkColorName: String = "Black",
    val brush: Brush? = null,
    val brushName: String? = null
)

val listColorLightDark = listOf(
    LightDarkColorModel(
        lightColor = Color(0xFFF2F2F2),
        lightColorName = "Day",
        darkColor = Color(0xFF222222),
        darkColorName = "Night"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFFFFFFF),
        lightColorName = "White",
        darkColor = Color(0xFF000000),
        darkColorName = "Black"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFFEB254),
        lightColorName = "Burnt peach",
        darkColor = Color(0xFF3F5A61),
        darkColorName = "Deep Steel Blue"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFDEF72B),
        lightColorName = "Battery Glow",
        darkColor = Color(0xFF878787),
        darkColorName = "Studio Gray"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFCDF22B),
        lightColorName = "Volt",
        darkColor = Color(0xFF03045E),//(0xFF1E45FB)//Blue,
        darkColorName = "Eletric Blue"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFFAEBD8),
        lightColorName = "Warm Linen",
        darkColor = Color(0xFFCC8799),
        darkColorName = "Pink Clay"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFC2D8C4),
        lightColorName = "Matcha Mist",
        darkColor = Color(0xFF222222),
        darkColorName = "Night"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFF2F2F2),
        lightColorName = "Light",
        darkColor = Color(0xFF961B2B),
        darkColorName = "Acid Berry"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFE6E2C5),
        lightColorName = "Parchment",
        darkColor = Color(0xFF2B4593),
        darkColorName = "Marian Blue"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFD1D0E2),
        lightColorName = "Pale Iris",
        darkColor = Color(0xFF566129),
        darkColorName = "Dusty Moss"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFD6FFF6),
        lightColorName = "Mint Green",
        darkColor = Color(0xFF231651),
        darkColorName = "Russian Violet"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFF92A9E1),
        lightColorName = "Lavender Haze",
        darkColor = Color(0xFF303030),
        darkColorName = "Soft Graphite"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFEBEBDF),
        lightColorName = "OatMilk Latte",
        darkColor = Color(0xFFE9631A),
        darkColorName = "Atomic Orange"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFDFAF34),
        lightColorName = "Mustard Yellow",
        darkColor = Color(0xFF0F7476),
        darkColorName = "Teal"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFFAF0CA),
        lightColorName = "Lemon Chiffon",
        darkColor = Color(0xFF0D3B66),
        darkColorName = "Yale Blue"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFACBDAA),
        lightColorName = "Latte Green",
        darkColor = Color(0xFF1E2D4C),
        darkColorName = "Navy Blue"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFCEC0BB),
        lightColorName = "Greyish Brown",
        darkColor = Color(0xFF3E251E),
        darkColorName = "Dark Coffee"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFDBD4CC),
        lightColorName = "dust Gray",
        darkColor = Color(0xFFD95122),
        darkColorName = "Grenadine"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFF7D929E),
        lightColorName = "cool Steel",
        darkColor = Color(0xFF023436),
        darkColorName = "MidNight Green"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFD7263D),
        lightColorName = "crimson",
        darkColor = Color(0xFF02182B),
        darkColorName = "MidNight Blue"
    ),
    LightDarkColorModel(
        lightColor = Color(0xFFE8A736),
        lightColorName = "Goldenrod",
        darkColor = Color(0xFF253B4E),
        darkColorName = "Charcoal"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF6A1B9A),
                Color(0xFF3F51B5),
                Color(0xFF2196F3)
            ),
            start = Offset(0f, 0f),
            end = Offset.Infinite
        ),
        brushName = "Dawn"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFFBE240),
                Color(0xFF8FF090),
                Color(0xFF0FFCEF)
            ),
            start = Offset(0f, 0f),
            end = Offset.Infinite
        ),
        brushName = "Brazil"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFFF2626),
                Color(0xFFFF95F4)
            ),
            start = Offset(0f, 0f),
            end = Offset.Infinite
        ),
        brushName = "Valentines"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFFFE53B),
                Color(0xFFFF2525)
            ),
            start = Offset(0f, 50f),
            end = Offset.Infinite
        ),
        brushName = "Summer"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFACB6E5),
                Color(0xFF86FDEB)
            ),
            start = Offset(0f, 50f),
            end = Offset.Infinite
        ),
        brushName = "Snow Flakes"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF545454),
                Color(0xFF222222)
            ),
            start = Offset(0f, 50f),
            end = Offset.Infinite
        ),
        brushName = "Darkest"
    ),
    LightDarkColorModel(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFF3AEEF),
                Color(0xFFBFF9FE)
            ),
            start = Offset(50f, 0f),
            end = Offset.Infinite
        ),
        brushName = "Cotton candy"
    ),
)

@Preview
@Composable
fun LightDarkTest(
    listColor: List<LightDarkColorModel> = listColorLightDark
) {

    LazyRow {
        items(items = listColor) { modelColor ->
            Box(
                modifier = Modifier.size(120.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .clip(
                            RoundedCornerShape(topStart = 12.dp)
                        )
                        .then(
                            if(modelColor.brush != null) {
                                Modifier.background(modelColor.brush)
                            } else {
                                Modifier.background(modelColor.lightColor)
                            }
                        )
                        .fillMaxSize()
                        .weight(1f)
                    ) {
                        val hexString = String.format("#%08X", modelColor.lightColor.toArgb())

                        Text(
                            modifier = Modifier.padding(8.dp).align(Alignment.TopStart),
                            text = hexString,
                            fontSize = 8.sp,
                            color = modelColor.darkColor,
                            fontFamily = FontFamily.Monospace
                        )

                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = modelColor.brushName?.uppercase() ?: modelColor.lightColorName.uppercase(),
                            fontSize = 16.sp,
                            color = modelColor.darkColor,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Box(modifier = Modifier
                        .clip(
                            RoundedCornerShape(bottomEnd = 12.dp)
                        )
                        .then(
                            if(modelColor.brush != null) {
                                Modifier.background(modelColor.brush)
                            } else {
                                Modifier.background(modelColor.darkColor)
                            }
                        )
                        .fillMaxSize()
                        .weight(1f)
                    ) {
                        val hexString = String.format("#%08X", modelColor.darkColor.toArgb())

                        Text(
                            modifier = Modifier.padding(8.dp).align(Alignment.BottomEnd),
                            text = hexString,
                            fontSize = 8.sp,
                            color = modelColor.lightColor,
                            fontFamily = FontFamily.Monospace
                        )

                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = modelColor.brushName?.uppercase() ?: modelColor.darkColorName.uppercase(),
                            fontSize =  16.sp,
                            color = modelColor.lightColor,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Spacer(Modifier.size(2.dp))
                }
            }
        }
    }
}


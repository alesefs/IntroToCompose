package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.offsetNoSpace(x: Dp = 0.dp, y: Dp = 0.dp) =
    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(
                x.roundToPx(),
                y.roundToPx()
            )
        }
    }

val colorList: List<Color> = listOf(
    Color.Blue.copy(alpha = 0.5f),
    Color.Yellow.copy(alpha = 0.5f),
    Color.Red.copy(alpha = 0.5f),
    Color.Gray.copy(alpha = 0.5f),
    Color.Green.copy(alpha = 0.5f),
    Color.Magenta.copy(alpha = 0.5f),
)

@Preview
@Composable
fun OffsetNoSpaceExample() {
    /*Row(
        Modifier
//            .fillMaxWidth()
//            .width((60 + (16 * 5) + 120).dp)
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0..5) {
            Box(
                Modifier
                    .offsetNoSpace(x = (-16 * i).dp) // move sem reservar espaço
                    .size(60.dp)
                    .background(colorList[i])
            ) {
                Text(text = "$i", modifier = Modifier.align(alignment = Alignment.Center))
            }
        }*/

    Box(
        Modifier
//            .fillMaxWidth()
//            .width((60 + (16 * 5) + 120).dp)
            .background(Color.LightGray),
//        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0..5) {
            //(size * i) - (16 * i)
            Box(
                Modifier
                    .padding(start = (60 * i).minus(16 * i).dp)
                    .size(60.dp)
                    .background(colorList[i])
            ) {
                Text(text = "$i", modifier = Modifier.align(alignment = Alignment.Center))
            }
        }

        /*Box(
            Modifier
                .size(60.dp)
                .background(Color.Red.copy(alpha = 0.5f))
        )

        Box(
            Modifier
                .padding(start = 50.dp)
                .size(60.dp)
                .background(Color.Gray.copy(alpha = 0.5f))
        )*/


//        Box(
//            Modifier
//                .size(60.dp)
//                .background(Color.Red.copy(alpha = 0.5f))
//        )
//
//        Box(
//            Modifier
//                .offsetNoSpace(x = (-16).dp) // move sem reservar espaço
//                .size(60.dp)
//                .background(Color.Blue.copy(alpha = 0.5f))
//        )
//
//        Box(
//            Modifier
//                .offsetNoSpace(x = (-32).dp) // move sem reservar espaço
//                .size(60.dp)
//                .background(Color.Green.copy(alpha = 0.25f))
//        )
//
//        Box(
//            Modifier
//                .offsetNoSpace(x = (-48).dp) // move sem reservar espaço
//                .size(60.dp)
//                .background(Color.Blue.copy(alpha = 0.5f))
//        )
//
//        Box(
//            Modifier
//                .offsetNoSpace(x = (-64).dp) // move sem reservar espaço
//                .size(60.dp)
//                .background(Color.Green.copy(alpha = 0.25f))
//        )


    }
}
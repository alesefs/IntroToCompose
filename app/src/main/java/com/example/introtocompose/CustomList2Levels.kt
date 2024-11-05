package com.example.introtocompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun CustomList2Levels(
    sizeIcon: Dp = 24.dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(sizeIcon)
                    .align(Alignment.CenterVertically)
                    .background(Color.White)
            ) {
                Checkbox(checked = true, onCheckedChange = null)
            }
            Box(
                modifier = Modifier
                    .width(sizeIcon)
                    .align(Alignment.CenterVertically)
                    .background(Color.White)
            ) {
                Image(
                    modifier = Modifier.size(sizeIcon),
                    painter = rememberVectorPainter(image = Icons.AutoMirrored.Rounded.Send),
                    contentDescription = null,
                )
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .align(Alignment.CenterVertically)
                    .weight(1f, fill = true)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(text = "Text 1", color = Color.Blue,
                        modifier = Modifier
                            .drawBehind {
                                drawRoundRect(
                                    Color.Cyan,
                                    cornerRadius = CornerRadius(999.dp.toPx())
                                )
                                drawRoundRect(
                                    Color.Blue,
                                    cornerRadius = CornerRadius(999.dp.toPx()),
                                    style = Stroke()
                                )
                            }
                            .padding(horizontal = 4.dp)
                    )
                    Text(text = "Text 2", color = Color.Blue)
                    Text(text = "Text 3", color = Color.Blue)
                    Text(text = "Text 4", color = Color.Blue)
                }
            }
            Box(
                modifier = Modifier
                    .width(sizeIcon)
                    .align(Alignment.CenterVertically)
                    .background(Color.White)
            ) {
                Image(
                    modifier = Modifier.size(sizeIcon),
                    painter = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowForward),
                    contentDescription = null,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = "Text 1", color = Color.DarkGray,
                    modifier = Modifier
                        .drawBehind {
                            drawRoundRect(
                                Color.LightGray,
                                cornerRadius = CornerRadius(999.dp.toPx())
                            )
                            drawRoundRect(
                                Color.DarkGray,
                                cornerRadius = CornerRadius(999.dp.toPx()),
                                style = Stroke()
                            )
                        }
                        .padding(horizontal = 4.dp)
                )
                Text(text = "Text 2", color = Color.Blue)
                Text(text = "Text 3", color = Color.Blue)
                Text(text = "Text 4", color = Color.Blue)
            }
        }
    }
}

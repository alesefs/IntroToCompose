package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Composable
fun CustomChipsC(
    name: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color.Blue else Color.White)
                .border(
                    width = 1.dp,
                    color = if (isSelected) Color.White else Color.Blue,
                    shape = CircleShape
                )
                .padding(vertical = 4.dp)
                .padding(start = 4.dp)
                .padding(end = 8.dp)
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(name)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            CustomImageCircleMedium()
            Image(
                painter = painterResource(R.drawable.baseline_10k_40),
                contentDescription = "avatar",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .size(32.dp)
                    .padding(1.dp)
//                    .border(1.dp, Color.White, CircleShape)
                    .clip(CircleShape)

            )

            Text(
                modifier = Modifier.widthIn(0.dp, 150.dp),
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (isSelected) Color.White else Color.Black
            )

            CustomImageCircleSmall()
        }
    }
}

@Preview
@Composable
fun CustomChipsC_Preview() {
    IntroToComposeTheme {
        CustomChipsC(
            name = "Chips Chips Chips Chips",
            isSelected = false,
        )
    }
}
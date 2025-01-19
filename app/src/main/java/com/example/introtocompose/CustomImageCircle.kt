package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.utils.Colors

/*@Preview
@Composable
fun CustomImageCircleMedium() {
    Box (Modifier.size(40.dp)) {
//        Box(Modifier
//            .size(32.dp)
//            .clip(CircleShape)
//            .align(Alignment.Center)
//        ) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
                    .scale(1.06f),
                contentScale = ContentScale.None,
                painter = painterResource(id = R.drawable.baseline_10k_40),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Blue)
            )
//        }
    }
}*/

@Preview
@Composable
fun CustomImageCircleMedium() {
    Box (Modifier.size(40.dp)) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Red,
                    shape = CircleShape
                ),
            painter = painterResource(id = R.drawable.baseline_10k_40),
            contentDescription = null,
            contentScale = ContentScale.None,

        )
    }
}

@Preview
@Composable
fun CustomImageCircleSmall() {
    Box (Modifier.size(32.dp)) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .aspectRatio(1f)
//                .padding(1.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Red,
                    shape = CircleShape
                )
                .align(Alignment.Center),
            contentScale = ContentScale.None,
            painter = painterResource(id = R.drawable.baseline_10k_32),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Blue)
        )
    }
}

@Preview
@Composable
fun TestChips() {
    FilterChip(
        modifier = Modifier,
        enabled = true,
        selected = false,
        border = BorderStroke(1.dp, Color.Red),
        leadingIcon = {
            CustomImageCircleMedium()
        },
        shape = CircleShape,
        trailingIcon = {
            CustomImageCircleSmall()
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            labelColor = Color.Black,
            iconColor = Color.Black,
            selectedContainerColor = Color.Blue,
            selectedLabelColor = Color.White,
            selectedLeadingIconColor = Color.White,
            selectedTrailingIconColor = Color.White
        ),
        label = {
            Box (Modifier.height(40.dp)) {
                Text(
                    modifier = Modifier
                        .widthIn(0.dp, 150.dp)
                        .align(Alignment.Center),
                    text = "teste",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        onClick = {

        }
    )
}

@Preview
@Composable
fun TestCustomChips() {
    FilterChip(
        modifier = Modifier,
        enabled = true,
        selected = false,
        border = BorderStroke(1.dp, Color.Red),
        leadingIcon = {
            CustomImageCircleMedium()
        },
        shape = CircleShape,
        trailingIcon = {
            CustomImageCircleSmall()
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            labelColor = Color.Black,
            iconColor = Color.Black,
            selectedContainerColor = Color.Blue,
            selectedLabelColor = Color.White,
            selectedLeadingIconColor = Color.White,
            selectedTrailingIconColor = Color.White
        ),
        label = {
            Box (Modifier.height(40.dp)) {
                Text(
                    modifier = Modifier
                        .widthIn(0.dp, 150.dp)
                        .align(Alignment.Center),
                    text = "teste",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        onClick = {

        }
    )
}
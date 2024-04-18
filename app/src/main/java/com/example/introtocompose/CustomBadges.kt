package com.example.introtocompose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import com.example.introtocompose.utils.thenIf

sealed class CustomBadgesType {
    data class Counter(val count: Int = 0) : CustomBadgesType()
    class Indicator(val indicatorColor: BadgeIndicatorColor = BadgeIndicatorColor.Notify) :
        CustomBadgesType()
}

sealed class BadgeIndicatorColor {
    data object Notify : BadgeIndicatorColor()
    data object Alert : BadgeIndicatorColor()
    data class Custom(val color: Color) : BadgeIndicatorColor()
}

@Composable
fun CustomBadges(
    modifier: Modifier = Modifier,
    type: CustomBadgesType = CustomBadgesType.Counter(count = 0),
    show: Boolean = true,
    border: Boolean = true
) {
    if (show) {
        /*when(type) {
            is CustomBadgesType.Counter -> {
                if(type.count > 0) {
                    Badge(
                        modifier = modifier
                            .thenIf(border) {
                                border(1.dp, Color.White, CircleShape)
                            }
                            .clip(CircleShape),
                        containerColor = Color.Red
                    ) {
                        Text(
                            text = if (type.count > 9) "+9" else "${type.count}",
                            color = Color.White
                        )
                    }
                }
            }
            is CustomBadgesType.Indicator -> {
                Badge(
                    modifier = modifier
                        .thenIf(border) {
                            border(1.dp, Color.White, CircleShape)
                        }
                        .size(8.dp)
                        .clip(CircleShape),
                    containerColor = when (type.indicatorColor) {
                        BadgeIndicatorColor.Alert -> Color.Red
                        is BadgeIndicatorColor.Custom -> type.indicatorColor.color
                        BadgeIndicatorColor.Notify -> Color.Green
                    },
                    contentColor = Color.White
                )
            }
        }*/

        if (type is CustomBadgesType.Counter && type.count > 0 || type is CustomBadgesType.Indicator) {
            Badge(
                modifier = modifier
                    .thenIf(border) {
                        border(1.dp, Color.White, CircleShape)
                    }
                    .thenIf(type is CustomBadgesType.Indicator) {
                        size(8.dp)
                    }
                    .clip(CircleShape),
                containerColor = if (type is CustomBadgesType.Indicator) {
                    when (type.indicatorColor) {
                        BadgeIndicatorColor.Alert -> Color.Red
                        is BadgeIndicatorColor.Custom -> type.indicatorColor.color
                        BadgeIndicatorColor.Notify -> Color.Green
                    }
                } else {
                    Color.Red
                },
                contentColor = Color.White
            ) {
                if (type is CustomBadgesType.Counter) {
                    Text(
                        text = if (type.count > 9) "+9" else "${type.count}",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomBadgesPreview() {
    IntroToComposeTheme {
        val showBadge = remember {
            mutableStateOf(true)
        }

        val showBorder = remember {
            mutableStateOf(true)
        }

        val counter = remember {
            mutableIntStateOf(0)
        }

        val textShowBadge = remember {
            mutableStateOf("Hide Badge")
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                    CustomBadges(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp),
                        type = CustomBadgesType.Indicator(),
                        border = showBorder.value,
                        show = showBadge.value
                    )
                }

                Box(
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                    CustomBadges(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp),
                        type = CustomBadgesType.Indicator(indicatorColor = BadgeIndicatorColor.Alert),
                        border = showBorder.value,
                        show = showBadge.value
                    )
                }

                Box(
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                    CustomBadges(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp),
                        type = CustomBadgesType.Indicator(
                            indicatorColor = BadgeIndicatorColor.Custom(
                                Color.Blue
                            )
                        ),
                        border = showBorder.value,
                        show = showBadge.value
                    )
                }

                Box(
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                    CustomBadges(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp),
                        type = CustomBadgesType.Counter(counter.intValue),
                        border = showBorder.value,
                        show = showBadge.value
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    counter.intValue += 1
                }) {
                    Text(text = "Add ${counter.intValue}")
                }

                Button(onClick = {
                    counter.intValue = 0
                }) {
                    Text(text = "Reset")
                }

                Button(onClick = {
                    showBorder.value = !showBorder.value
                }) {
                    Text(text = "Border")
                }

                Button(onClick = {
                    showBadge.value = !showBadge.value
                    textShowBadge.value = if (showBadge.value) "Hide Badge" else "Show Bagde"
                }) {
                    Text(text = textShowBadge.value)
                }
            }
        }
    }
}
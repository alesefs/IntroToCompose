package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.introtocompose.utils.Colors
import com.example.introtocompose.utils.thenIf

enum class ChipType {
    Neutral,
    NeutralHighlighted,
    NeutralMinimal,
    HighlightedMinimal
}

enum class ChipSize {
    Medium,
    Small
}

enum class ChipSupportVisualType {
    Icon,
    Image
}

data class ChipSupportVisualData(
    val supportVisual: Int,
    val supportVisualType: ChipSupportVisualType,
)

sealed class ChipSelectionType(
    val icon: Int? = null
) {
    data object Dismissible : ChipSelectionType(
        icon = R.drawable.baseline_electric_car_24
    )

    data object Toogle : ChipSelectionType()

    data object Dropdown : ChipSelectionType(
        icon = R.drawable.baseline_card_giftcard_24
    )
}

@Stable
internal class ChipColorStyle(
    val backgroundColor: Color,
    val contentColor: Color,
    val leadingIconColor: Color,
    val actionIconColor: Color,
    val borderColor: Color,
    val selectedBackgroundColor: Color,
    val selectedContentColor: Color,
    val selectedLeadingIconColor: Color,
    val selectedActionIconColor: Color,
    val selectedBorderColor: Color = Color.Transparent,
    val disabledContentColor: Color,
    val disabledBackgroundColor: Color,
    val disabledLeadingIconColor: Color,
    val disabledActionIconColor: Color,
    val disabledBorderColor: Color = Color.Transparent,
)

object ChipColorDefaults {
    @Composable
    internal fun chipColor(type: ChipType): ChipColorStyle {
        return when (type) {
            ChipType.Neutral -> ChipColorStyle(
                backgroundColor = Color.White,
                contentColor = Color.DarkGray,
                leadingIconColor = Color.DarkGray,
                actionIconColor = Color.Blue,
                borderColor = Color.LightGray,
                selectedBackgroundColor = Color.Blue,
                selectedContentColor = Color.White,
                selectedLeadingIconColor = Color.White,
                selectedActionIconColor = Color.White,
                disabledContentColor = Color.DarkGray,
                disabledBackgroundColor = Color.Gray,
                disabledLeadingIconColor = Color.DarkGray,
                disabledActionIconColor = Color.DarkGray,
            )

            ChipType.NeutralHighlighted -> ChipColorStyle(
                backgroundColor = Color.Gray,
                contentColor = Color.DarkGray,
                leadingIconColor = Color.DarkGray,
                actionIconColor = Color.Blue,
                borderColor = Color.LightGray,
                selectedBackgroundColor = Color.Blue,
                selectedContentColor = Color.White,
                selectedLeadingIconColor = Color.White,
                selectedActionIconColor = Color.White,
                disabledContentColor = Color.DarkGray,
                disabledBackgroundColor = Color.Gray,
                disabledLeadingIconColor = Color.DarkGray,
                disabledActionIconColor = Color.DarkGray,
            )

            ChipType.NeutralMinimal -> ChipColorStyle(
                backgroundColor = Color.White,
                contentColor = Color.DarkGray,
                leadingIconColor = Color.DarkGray,
                actionIconColor = Color.Blue,
                borderColor = Color.LightGray,
                selectedBackgroundColor = Color.Cyan,
                selectedContentColor = Color.Magenta,
                selectedLeadingIconColor = Color.DarkGray,
                selectedActionIconColor = Color.Magenta,
                selectedBorderColor = Color.Magenta,
                disabledContentColor = Color.DarkGray,
                disabledBackgroundColor = Color.Gray,
                disabledLeadingIconColor = Color.DarkGray,
                disabledActionIconColor = Color.DarkGray,
                disabledBorderColor = Color.LightGray
            )

            ChipType.HighlightedMinimal -> ChipColorStyle(
                backgroundColor = Color.Gray,
                contentColor = Color.DarkGray,
                leadingIconColor = Color.DarkGray,
                actionIconColor = Color.Blue,
                borderColor = Color.LightGray,
                selectedBackgroundColor = Color.Cyan,
                selectedContentColor = Color.Magenta,
                selectedLeadingIconColor = Color.DarkGray,
                selectedActionIconColor = Color.Magenta,
                selectedBorderColor = Color.Magenta,
                disabledContentColor = Color.DarkGray,
                disabledBackgroundColor = Color.Gray,
                disabledLeadingIconColor = Color.DarkGray,
                disabledActionIconColor = Color.DarkGray,
                disabledBorderColor = Color.LightGray
            )
        }
    }
}

data class ChipSpacingStyle(
    val supportVisualContainerSize: Dp,
    val supportVisualSize: Dp
)

object ChipSpacingDefaults {
    @Composable
    internal fun chipSpacing(
        size: ChipSize,
        supportVisualType: ChipSupportVisualType
    ): ChipSpacingStyle {
        return when (size) {
            ChipSize.Medium -> {
                when (supportVisualType) {
                    ChipSupportVisualType.Icon -> {
                        ChipSpacingStyle(
                            supportVisualContainerSize = 40.dp,
                            supportVisualSize = 24.dp
                        )
                    }

                    ChipSupportVisualType.Image -> {
                        ChipSpacingStyle(
                            supportVisualContainerSize = 40.dp,
                            supportVisualSize = 32.dp
                        )
                    }
                }
            }

            ChipSize.Small -> {
                when (supportVisualType) {
                    ChipSupportVisualType.Icon -> {
                        ChipSpacingStyle(
                            supportVisualContainerSize = 32.dp,
                            supportVisualSize = 16.dp
                        )
                    }

                    ChipSupportVisualType.Image -> {
                        ChipSpacingStyle(
                            supportVisualContainerSize = 32.dp,
                            supportVisualSize = 24.dp
                        )
                    }
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomChip(
    title: String,
    type: ChipType,
    size: ChipSize,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    supportVisual: ChipSupportVisualData? = null,
    selectionType: ChipSelectionType,
    onSelectionChanged: (String) -> Unit
) {
    val textStyle =
        if (isSelected) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.titleMedium

    val colors = ChipColorDefaults.chipColor(type)
    val spacing = supportVisual?.let { ChipSpacingDefaults.chipSpacing(size, it.supportVisualType) }

    val borderColor =
        when (type) {
            ChipType.NeutralMinimal, ChipType.HighlightedMinimal -> {
                if (isEnabled) {
                    if (isSelected) {
                        BorderStroke(
                            width = 1.dp,
                            color = colors.selectedBorderColor
                        )
                    } else {
                        BorderStroke(
                            width = 1.dp,
                            color = colors.borderColor
                        )
                    }
                } else {
                    if (isSelected) {
                        BorderStroke(
                            width = 1.dp,
                            color = colors.disabledBorderColor
                        )
                    } else {
                        null
                    }
                }
            }

            else -> {
                if (isEnabled && !isSelected) BorderStroke(
                    width = 1.dp,
                    color = colors.borderColor
                ) else null
            }
        }

    val supportVisualColor: Color
    val actionColor: Color

    if (isEnabled) {
        if (isSelected) {
            supportVisualColor = colors.selectedLeadingIconColor
            actionColor = colors.selectedActionIconColor
        } else {
            supportVisualColor = colors.leadingIconColor
            actionColor = colors.actionIconColor
        }
    } else {
        supportVisualColor = colors.disabledLeadingIconColor
        actionColor = colors.disabledActionIconColor
    }

//    val actionButtonSize = if (size == ChipSize.Medium) 24.dp else 16.dp

    Box(modifier = modifier) {
        FilterChip(
            modifier = Modifier,
            enabled = isEnabled,
            selected = isSelected,
            border = borderColor,
            leadingIcon = {
                SupportVisual(
                    supportVisual = supportVisual,
                    supportVisualContainerSize = spacing?.supportVisualContainerSize ?: 0.dp,
                    supportVisualSize = spacing?.supportVisualSize ?: 0.dp,
                    isEnabled = isEnabled,
                    supportVisualColor = supportVisualColor
                )
            },
            shape = RoundedCornerShape(50),
            trailingIcon = {
                ActionIcon(
                    title = title,
                    selectionType = selectionType,
                    isEnabled = isEnabled,
                    actionColor = actionColor,
                    onSelectionChanged = onSelectionChanged
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = colors.backgroundColor,
                labelColor = colors.contentColor,
                iconColor = colors.leadingIconColor,
                disabledContainerColor = colors.disabledBackgroundColor,
                disabledLabelColor = colors.disabledContentColor,
                disabledLeadingIconColor = colors.disabledLeadingIconColor,
                disabledTrailingIconColor = colors.disabledActionIconColor,
                disabledSelectedContainerColor = Colors.Teal,
                selectedContainerColor = colors.selectedBackgroundColor,
                selectedLabelColor = colors.selectedContentColor,
                selectedLeadingIconColor = colors.selectedLeadingIconColor,
                selectedTrailingIconColor = colors.selectedActionIconColor
            ),
            label = {
                Box (Modifier.height(spacing?.supportVisualContainerSize ?: 40.dp)) {
                    Text(
                        modifier = Modifier
                            .widthIn(0.dp, 150.dp)
                            .align(Alignment.Center),
                        text = title,
                        maxLines = 1,
                        style = textStyle,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            onClick = {
                if (isEnabled) {
                    when (selectionType) {
                        ChipSelectionType.Dropdown, ChipSelectionType.Toogle -> onSelectionChanged(title)
                        else -> Unit
                    }
                }
            }
        )
    }
}


@Composable
private fun SupportVisual(
    supportVisual: ChipSupportVisualData?,
    supportVisualContainerSize: Dp,
    supportVisualSize: Dp,
    isEnabled: Boolean,
    supportVisualColor: Color
) {
    if (supportVisual != null) {
        Box(
            Modifier.size(supportVisualContainerSize)
        ) {
            Image(
                modifier = Modifier
                    .size(supportVisualSize)
                    .align(Alignment.Center)
                    .thenIf(!isEnabled && supportVisual.supportVisualType == ChipSupportVisualType.Image) {
                        disabled()
                    },
                painter = painterResource(id = supportVisual.supportVisual),
                contentDescription = null,
                colorFilter = if (supportVisual.supportVisualType == ChipSupportVisualType.Icon) {
                    ColorFilter.tint(
                        color = supportVisualColor
                    )
                } else {
                    null
                }
            )
        }
    }
}

fun Modifier.disabled(disabledOpacity: Float = 0.4f) = this
    .grayScale()
    .then(alpha(disabledOpacity))

fun Modifier.grayScale(): Modifier {
    val saturationMatrix = ColorMatrix().apply {
        setToSaturation(0f)
    }
    val saturationFilter = ColorFilter.colorMatrix(saturationMatrix)
    val paint = Paint().apply {
        colorFilter = saturationFilter
    }

    return drawWithCache {
        val canvasBounds = Rect(Offset.Zero, size)
        onDrawWithContent {
            drawIntoCanvas {
                it.saveLayer(canvasBounds, paint)
                drawContent()
                it.restore()
            }
        }
    }
}

@Composable
private fun ActionIcon(
    title: String,
    isEnabled: Boolean,
    actionColor: Color,
    selectionType: ChipSelectionType,
    onSelectionChanged: (String) -> Unit
) {
    if (selectionType.icon != null) {
        Box(
            Modifier
                .size(40.dp)
                .thenIf(selectionType == ChipSelectionType.Dismissible) {
                    clickable(enabled = isEnabled) {
                        onSelectionChanged(title)
                    }
                }
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = selectionType.icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = actionColor
                )
            )
        }
    }
}

@Preview
@Composable
private fun PBChipPreview() {
    MaterialTheme {
        CustomChip(
            title = "chip item chip item chip item chip item chip item chip item",
            supportVisual = ChipSupportVisualData(
                supportVisual = R.drawable.baseline_card_giftcard_24,
                supportVisualType = ChipSupportVisualType.Icon
            ),
            type = ChipType.Neutral,
            isSelected = false,
            size = ChipSize.Medium,
            selectionType = ChipSelectionType.Dismissible,
            onSelectionChanged = {
                println("click!")
            },
        )
    }
}

@Preview
@Composable
private fun PBChipItemsPreview(
    @PreviewParameter(ChipPreviewParameterProvider::class) chipData: ChipPreviewData,
) {
    MaterialTheme {
        Box(
            modifier = Modifier
        ) {
            CustomChip(
                title = chipData.title,
                type = chipData.style,
                size = chipData.size,
                isSelected = chipData.isSelected,
                isEnabled = chipData.isEnabled,
                supportVisual = if (chipData.supportVisual != null) {
                    ChipSupportVisualData(
                        supportVisual = chipData.supportVisual.first,
                        supportVisualType = chipData.supportVisual.second
                    )
                } else null,
                selectionType = chipData.selectionType,
                onSelectionChanged = chipData.onSelectionChanged
            )
        }
    }
}

private data class ChipPreviewData(
    val title: String,
    val style: ChipType,
    val size: ChipSize,
    val isSelected: Boolean,
    val isEnabled: Boolean,
    val selectionType: ChipSelectionType,
    val supportVisual: Pair<Int, ChipSupportVisualType>? = null,
    val onSelectionChanged: (String) -> Unit
)

private class ChipPreviewParameterProvider :
    PreviewParameterProvider<ChipPreviewData> {
    override val values = sequenceOf(
        ChipPreviewData(
            title = "chip item chip item chip item chip item chip item chip item",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ){
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = null,
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = false,
            supportVisual = null,
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = null,
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip item chip item chip item chip item chip item",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = true,
            supportVisual = null,
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = false,
            supportVisual = null,
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = true,
            supportVisual = null,
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralHighlighted,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralHighlighted,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralHighlighted,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Toogle,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralHighlighted,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralMinimal,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralMinimal,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralMinimal,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.NeutralMinimal,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.HighlightedMinimal,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.HighlightedMinimal,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.HighlightedMinimal,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.HighlightedMinimal,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_card_giftcard_24,
                ChipSupportVisualType.Icon
            ),
            selectionType = ChipSelectionType.Dismissible,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = false,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Medium,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = true,
            isEnabled = true,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = false,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        },
        ChipPreviewData(
            title = "chip item chip",
            style = ChipType.Neutral,
            size = ChipSize.Small,
            isSelected = true,
            isEnabled = false,
            supportVisual = Pair(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Image
            ),
            selectionType = ChipSelectionType.Dropdown,
        ) {
            println("click!")
        }
    )
}

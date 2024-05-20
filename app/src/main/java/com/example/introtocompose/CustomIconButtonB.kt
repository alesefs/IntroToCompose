package com.example.introtocompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

enum class CustomIconButtonBStyle {
    Primary,
    Tertiary
}

@Stable
internal interface IconButtonBDefinitions {
    @Composable
    fun backgroundColor(): Color

    @Composable
    fun iconColor(): Color

    @Composable
    fun rippleColor(): Color
}

@Immutable
private class PrimaryIconButtonBDefinitions(val enable: Boolean) : IconButtonBDefinitions {
    @Composable
    override fun backgroundColor(): Color = if (enable) {
        Color.Blue
    } else {
        Color.LightGray
    }

    @Composable
    override fun iconColor(): Color = if (enable) {
        Color.White
    } else {
        Color.DarkGray
    }

    @Composable
    override fun rippleColor(): Color = Color.Cyan
}

@Immutable
private class TertiaryIconButtonBDefinitions(val enable: Boolean) : IconButtonBDefinitions {
    @Composable
    override fun backgroundColor(): Color = Color.Transparent

    @Composable
    override fun iconColor(): Color = if (enable) {
        Color.Blue
    } else {
        Color.Gray
    }

    @Composable
    override fun rippleColor(): Color = Color.Cyan
}

object IconButtonDefaults {
    @Composable
    internal fun iconButtonColorDefinitions(
        style: CustomIconButtonBStyle,
        enable: Boolean
    ): IconButtonBDefinitions {
        return when (style) {
            CustomIconButtonBStyle.Primary -> PrimaryIconButtonBDefinitions(enable)
            CustomIconButtonBStyle.Tertiary -> TertiaryIconButtonBDefinitions(enable)
        }
    }
}

@Composable
fun CustomIconButtonB(
    style: CustomIconButtonBStyle,
    icon: Painter,
    modifier: Modifier = Modifier,
    iconDescription: String? = null,
    notificationValue: Int = 0,
    enable: Boolean = true,
    action: () -> Unit
) {
    val colorDefinitions = IconButtonDefaults.iconButtonColorDefinitions(style, enable)

    Box(
        modifier = modifier
    ) {
        /*Image(
            modifier = Modifier
                .padding(all = 1.dp)
                .clip(shape = RoundedCornerShape(999.dp))
                .size(size = 40.dp)
                .background(color = colorDefinitions.backgroundColor())
                .clickable(
                    enabled = enable,
                    role = Role.Button,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        radius = 999.dp,
                        color = colorDefinitions.rippleColor()
                    ),
                ) {
                    action.invoke()
                }
                .semantics { role = Role.Button }
                .padding(all = 8.dp),
            painter = icon,
            contentDescription = iconDescription,
            colorFilter = ColorFilter.tint(
                color = colorDefinitions.iconColor(),
                blendMode = BlendMode.SrcIn
            )
        )*/

        CustomIcons(
            icon = icon,
            contentDescription = iconDescription,
            size = CustomIconsSize.Large,
            style = CustomIconStyle.Custom(
                shapedColor = colorDefinitions.backgroundColor(),
                iconeColor = colorDefinitions.iconColor()
            ),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(999.dp))
                .clickable(
                    enabled = enable,
                    role = Role.Button,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = true,
                        radius = 999.dp,
                        color = colorDefinitions.rippleColor()
                    ),
                ) {
                    action.invoke()
                }
                .semantics { role = Role.Button }
        )

        if (notificationValue > 0) {
            CustomBadges(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(0.dp),
                type = CustomBadgesType.Counter(notificationValue),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomIconButtonBClickPreview() {
    IntroToComposeTheme {
        val notificationValue = remember {
            mutableIntStateOf(0)
        }

        Column {
            CustomIconButtonB(
                style = CustomIconButtonBStyle.Primary,
                icon = rememberVectorPainter(
                    image = Icons.Default.AccountCircle
                ),
                notificationValue = notificationValue.intValue,
                enable = true
            ) {
                notificationValue.intValue += 1
                println("click! ${notificationValue.intValue}")
            }

            CustomIconButtonB(
                style = CustomIconButtonBStyle.Tertiary,
                icon = rememberVectorPainter(
                    image = Icons.Default.AccountCircle
                ),
                notificationValue = notificationValue.intValue,
                enable = true
            ) {
                notificationValue.intValue -= 1
                println("click! ${notificationValue.intValue}")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun CustomIconButtonBPreview(
    @PreviewParameter(CustomIconButtonBPreviewParameterProvider::class) iconButtonData: CustomIconButtonBPreviewData,
) {
    IntroToComposeTheme {
        CustomIconButtonB(
            style = iconButtonData.style,
            icon = rememberVectorPainter(
                image = Icons.Default.AccountCircle
            ),
            notificationValue = iconButtonData.notificationValue,
            enable = iconButtonData.enable
        ) {
            println("click! ${iconButtonData.style}, ${iconButtonData.enable}, ${iconButtonData.notificationValue}")
        }
    }
}

private data class CustomIconButtonBPreviewData(
    val style: CustomIconButtonBStyle,
    val notificationValue: Int,
    val enable: Boolean,
)

private class CustomIconButtonBPreviewParameterProvider :
    PreviewParameterProvider<CustomIconButtonBPreviewData> {
    override val values = sequenceOf(
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Primary,
            notificationValue = 0,
            enable = true
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Primary,
            notificationValue = 5,
            enable = true
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Primary,
            notificationValue = 10,
            enable = true
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Primary,
            notificationValue = 0,
            enable = false
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Primary,
            notificationValue = 5,
            enable = false
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Primary,
            notificationValue = 10,
            enable = false
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Tertiary,
            notificationValue = 0,
            enable = true
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Tertiary,
            notificationValue = 5,
            enable = true
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Tertiary,
            notificationValue = 10,
            enable = true
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Tertiary,
            notificationValue = 0,
            enable = false
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Tertiary,
            notificationValue = 5,
            enable = false
        ),
        CustomIconButtonBPreviewData(
            style = CustomIconButtonBStyle.Tertiary,
            notificationValue = 10,
            enable = false
        ),
    )
}
package com.example.introtocompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import com.example.introtocompose.utils.thenIf

sealed class CustomFeedbackStyle(
    val iconStyle: CustomIconStyle,
) {
    data object Success : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Success()
    )

    data object Warning : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Warning()
    )

    data object Error : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Error()
    )

    data object Neutral : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Neutral()
    )

    data object Informative : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Info()
    )

    data class Custom(
        val feedbackIconShapeColor: Color,
        val feedbackIconColor: Color,
    ) : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Custom(
            shapedColor = feedbackIconShapeColor,
            iconeColor = feedbackIconColor,
            showCustomShape = true
        )
    )
}

/*sealed class CustomFeedbackSize(
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val iconPadding: Dp,
    val contentAlign: Alignment.Horizontal,
    val iconSize: CustomIconsSize,
    val titleTextStyle: TextStyle,
    val descriptionTextStyle: TextStyle,
    val textAlign: TextAlign
) {
    data class Large(
        val showCard: Boolean = false
    ) : CustomFeedbackSize(
        horizontalPadding = 16.dp,
        verticalPadding = 24.dp,
        iconPadding = 16.dp,
        contentAlign = Alignment.CenterHorizontally,
        iconSize = CustomIconsSize.Large,
        titleTextStyle = TextStyle(
            fontSize = 24.sp,
        ),
        descriptionTextStyle = TextStyle(
            fontSize = 16.sp,
        ),
        textAlign = TextAlign.Center
    )

    data class Medium(
        val showCard: Boolean = false
    ) : CustomFeedbackSize(
        horizontalPadding = 12.dp,
        verticalPadding = 16.dp,
        iconPadding = 16.dp,
        contentAlign = Alignment.CenterHorizontally,
        iconSize = CustomIconsSize.Medium,
        titleTextStyle = TextStyle(
            fontSize = 18.sp,
        ),
        descriptionTextStyle = TextStyle(
            fontSize = 14.sp,
        ),
        textAlign = TextAlign.Center
    )

    data class Small(
        val showCard: Boolean = false
    ) : CustomFeedbackSize(
        horizontalPadding = 16.dp,
        verticalPadding = 16.dp,
        iconPadding = 8.dp,
        contentAlign = Alignment.Start,
        iconSize = CustomIconsSize.Small,
        titleTextStyle = TextStyle(
            fontSize = 16.sp,
        ),
        descriptionTextStyle = TextStyle(
            fontSize = 12.sp,
        ),
        textAlign = TextAlign.Start
    )
}*/

interface CustomFeedbackDefinition {
    val horizontalPadding: Dp
    val verticalPadding: Dp
    val iconPadding: Dp
    val contentAlign: Alignment.Horizontal
    val iconSize: CustomIconsSize
    val titleTextStyle: TextStyle
    val descriptionTextStyle: TextStyle
    val textAlign: TextAlign
}

class CustomFeedbackDefinitionSmall : CustomFeedbackDefinition {
    override val horizontalPadding: Dp = 16.dp
    override val verticalPadding: Dp = 16.dp
    override val iconPadding: Dp = 8.dp
    override val contentAlign: Alignment.Horizontal = Alignment.Start
    override val iconSize: CustomIconsSize = CustomIconsSize.Small
    override val titleTextStyle: TextStyle = TextStyle(fontSize = 16.sp)
    override val descriptionTextStyle: TextStyle = TextStyle(fontSize = 12.sp)
    override val textAlign: TextAlign = TextAlign.Start
}

class CustomFeedbackDefinitionMedium : CustomFeedbackDefinition {
    override val horizontalPadding: Dp = 16.dp
    override val verticalPadding: Dp = 24.dp
    override val iconPadding: Dp = 16.dp
    override val contentAlign: Alignment.Horizontal = Alignment.CenterHorizontally
    override val iconSize: CustomIconsSize = CustomIconsSize.Medium
    override val titleTextStyle: TextStyle = TextStyle(fontSize = 16.sp)
    override val descriptionTextStyle: TextStyle = TextStyle(fontSize = 12.sp)
    override val textAlign: TextAlign = TextAlign.Center
}

class CustomFeedbackDefinitionLarge : CustomFeedbackDefinition {
    override val horizontalPadding: Dp = 16.dp
    override val verticalPadding: Dp = 16.dp
    override val iconPadding: Dp = 16.dp
    override val contentAlign: Alignment.Horizontal = Alignment.CenterHorizontally
    override val iconSize: CustomIconsSize = CustomIconsSize.Medium
    override val titleTextStyle: TextStyle = TextStyle(fontSize = 24.sp)
    override val descriptionTextStyle: TextStyle = TextStyle(fontSize = 16.sp)
    override val textAlign: TextAlign = TextAlign.Center
}

data class CustomFeedbackAction(
    val actionTitle: String,
    val action: (() -> Unit)
)

sealed class CustomFeedbackSize(
    open val showCard: Boolean = false
) {
    data class Large(
        override val showCard: Boolean = false
    ) : CustomFeedbackSize(
        showCard = showCard
    )

    data class Medium(
        override val showCard: Boolean = false
    ) : CustomFeedbackSize(
        showCard = showCard
    )

    data class Small(
        override val showCard: Boolean = false
    ) : CustomFeedbackSize(
        showCard = showCard
    )
}

@Composable
fun CustomFeedback(
    title: String,
    icon: ImageVector,
    size: CustomFeedbackSize,
    style: CustomFeedbackStyle,
    modifier: Modifier = Modifier,
    description: String? = null,
    action: CustomFeedbackAction? = null
) {
    val definition = CustomFeedbackDefaults.customFeedbackDefinition(size = size)

    when (size) {
        is CustomFeedbackSize.Large ->  {
            if (size.showCard) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    FeedbackContentLargeMedium(modifier, size, definition, icon, style, title, description, action)
                }
            } else {
                FeedbackContentLargeMedium(modifier, size, definition, icon, style, title, description, action)
            }
        }
        is CustomFeedbackSize.Medium -> {
            if (size.showCard) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    FeedbackContentLargeMedium(modifier, size, definition, icon, style, title, description, action)
                }
            } else {
                FeedbackContentLargeMedium(modifier, size, definition, icon, style, title, description, action)
            }
        }
        is CustomFeedbackSize.Small -> {
            if (size.showCard) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    FeedbackContentSmall(size, definition, icon, style, title, description, action)
                }
            } else {
                FeedbackContentSmall(size, definition, icon, style, title, description, action)
            }
        }
    }
}

object CustomFeedbackDefaults {
    @Composable
    internal fun customFeedbackDefinition(size: CustomFeedbackSize): CustomFeedbackDefinition = remember {
        when(size) {
            is CustomFeedbackSize.Large -> CustomFeedbackDefinitionLarge()
            is CustomFeedbackSize.Medium -> CustomFeedbackDefinitionMedium()
            is CustomFeedbackSize.Small -> CustomFeedbackDefinitionSmall()
        }
    }
}

@Composable
private fun FeedbackContentSmall(
    size: CustomFeedbackSize,
    definition: CustomFeedbackDefinition,
    icon: ImageVector,
    type: CustomFeedbackStyle,
    title: String,
    description: String?,
    action: CustomFeedbackAction?
) {
    Row(
        modifier = Modifier
            .width(328.dp)
            .padding(
                horizontal = definition.horizontalPadding,
                vertical = definition.verticalPadding
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        FeedbackIcon(icon, size, definition, type)

        FeedbackTextsAndActionContent(definition, title, description, action)
    }
}

@Composable
private fun FeedbackContentLargeMedium(
    modifier: Modifier,
    size: CustomFeedbackSize,
    definition: CustomFeedbackDefinition,
    icon: ImageVector,
    type: CustomFeedbackStyle,
    title: String,
    description: String?,
    action: CustomFeedbackAction?
) {
    Column(
        modifier = modifier
            .thenIf(size is CustomFeedbackSize.Large) {
                fillMaxWidth()
            }
            .thenIf(size is CustomFeedbackSize.Medium) {
                width(328.dp)
            }
            .padding(
                horizontal = definition.horizontalPadding,
                vertical = definition.verticalPadding
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeedbackIcon(icon, size, definition, type)

        FeedbackTextsAndActionContent(definition, title, description, action)
    }
}

@Composable
private fun FeedbackIcon(
    icon: ImageVector,
    size: CustomFeedbackSize,
    definition: CustomFeedbackDefinition,
    type: CustomFeedbackStyle
) {
    CustomIcons(
        icon = rememberVectorPainter(image = icon),
        size = definition.iconSize,
        style = type.iconStyle
    )

    Spacer(modifier = Modifier
        .thenIf(size is CustomFeedbackSize.Small) {
            width(definition.iconPadding)
        }
        .thenIf(size !is CustomFeedbackSize.Small) {
            height(definition.iconPadding)
        }
    )
}

@Composable
private fun FeedbackTextsAndActionContent(
    definition: CustomFeedbackDefinition,
    title: String,
    description: String?,
    action: CustomFeedbackAction?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = definition.contentAlign
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = definition.titleTextStyle,
            textAlign = definition.textAlign
        )

        description?.let {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                color = Color.Gray,
                style = definition.descriptionTextStyle,
                textAlign = definition.textAlign
            )
        }

        action?.let {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .clickable {
                        action.action
                    }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = action.actionTitle,
                color = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomFeedbackListPreview(
    @PreviewParameter(CustomFeedbackParameterProvider::class) customFeedbackData : CustomFeedbackPreviewData
) {
    IntroToComposeTheme {
        Box(modifier = Modifier.padding(2.dp)) {
            CustomFeedback(
                title = customFeedbackData.title,
                icon = Icons.Filled.AccountCircle,
                size = customFeedbackData.size,
                style = customFeedbackData.style,
                description = "He lands head first on a rotting maple log.\n" +
                        "Knocked unconscious, fox sleeps with shallow breath\n" +
                        "until the lazy dog awakes.",
                action = CustomFeedbackAction("Click!") {}
            )
        }
    }
}

private class CustomFeedbackParameterProvider : PreviewParameterProvider<CustomFeedbackPreviewData>  {
    override val values = sequenceOf(
        CustomFeedbackPreviewData(
            title = "Custom Feedback,\n size: Large, style: Success",
            size = CustomFeedbackSize.Large(showCard = true),
            style = CustomFeedbackStyle.Success
        ),
        CustomFeedbackPreviewData(
            title = "Custom Feedback,\n size: Medium, style: Success",
            size = CustomFeedbackSize.Medium(),
            style = CustomFeedbackStyle.Success
        ),
        CustomFeedbackPreviewData(
            title = "Custom Feedback,\n size: Small, style: Success",
            size = CustomFeedbackSize.Small(),
            style = CustomFeedbackStyle.Success
        )
    )
}

private data class CustomFeedbackPreviewData(
    val title: String,
    val size: CustomFeedbackSize,
    val style: CustomFeedbackStyle
)

@Preview(showBackground = true)
@Composable
fun CustomFeedbackPreview() {
    IntroToComposeTheme {
        val allTypes = listOf(
            CustomFeedbackStyle.Success,
            CustomFeedbackStyle.Warning,
            CustomFeedbackStyle.Error,
            CustomFeedbackStyle.Neutral,
            CustomFeedbackStyle.Informative,
            CustomFeedbackStyle.Custom(
                feedbackIconColor = Color.Cyan,
                feedbackIconShapeColor = Color.Black
            )
        )

        val allSizes = listOf(
            CustomFeedbackSize.Large(),
            CustomFeedbackSize.Medium(),
            CustomFeedbackSize.Small(),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = allTypes) { type ->
                LazyRow {
                    items(items = allSizes) { size ->
                        CustomFeedback(
                            title = "The quick brown fox jumps over",
                            icon = Icons.Filled.AccountCircle,
                            size = size,
                            style = type,
                            description = "He lands head first on a rotting maple log.\n" +
                                    "Knocked unconscious, fox sleeps with shallow breath\n" +
                                    "until the lazy dog awakes.",
                            action = CustomFeedbackAction("Click!") {}
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomFeedbackSuccessPreview() {
    IntroToComposeTheme {
        val allSizes = listOf(
            CustomFeedbackSize.Large(),
            CustomFeedbackSize.Large(true),
            CustomFeedbackSize.Medium(),
            CustomFeedbackSize.Medium(true),
            CustomFeedbackSize.Small(),
            CustomFeedbackSize.Small(true),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = allSizes) { size ->
                CustomFeedback(
                    title = "The quick brown fox jumps over",
                    icon = Icons.Filled.AccountCircle,
                    size = size,
                    style = CustomFeedbackStyle.Success,
                    description = "He lands head first on a rotting maple log.\n" +
                            "Knocked unconscious, fox sleeps with shallow breath\n" +
                            "until the lazy dog awakes.",
                    action = CustomFeedbackAction("Click!") {}
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
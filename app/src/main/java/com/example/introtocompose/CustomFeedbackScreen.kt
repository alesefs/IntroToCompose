package com.example.introtocompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import com.example.introtocompose.utils.thenIf

sealed class CustomFeedbackStyle(
    val iconStyle: CustomIconStyle,

    ) {
    class Success : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Success()
    )

    class Warning : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Warning()
    )

    class Error : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Error()
    )

    class Neutral : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Neutral()
    )

    class Informative : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Info()
    )

    class Custom(
        feedbackIconShapeColor: Color,
        feedbackIconColor: Color,
    ) : CustomFeedbackStyle(
        iconStyle = CustomIconStyle.Custom(
            shapedColor = feedbackIconShapeColor,
            iconeColor = feedbackIconColor,
            showCustomShape = true
        )
    )
}

sealed class CustomFeedbackSize(
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val iconPadding: Dp,
    val contentAlign: Alignment.Horizontal,
    val iconSize: CustomIconsSize,
    val titleTextStyle: TextStyle,
    val descriptionTextStyle: TextStyle,
    val textAlign: TextAlign
) {
    class Large : CustomFeedbackSize(
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

    class Medium : CustomFeedbackSize(
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

    class Small : CustomFeedbackSize(
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
}

data class CustomFeedbackAction(
    val actionTitle: String,
    val action: (() -> Unit)
)

@Composable
fun CustomFeedback(
    title: String,
    icon: ImageVector,
    size: CustomFeedbackSize,
    type: CustomFeedbackStyle,
    modifier: Modifier = Modifier,
    description: String? = null,
    action: CustomFeedbackAction? = null
) {
    if (size !is CustomFeedbackSize.Small) {
        Column(
            modifier = modifier
                .thenIf(size is CustomFeedbackSize.Large) {
                    fillMaxWidth()
                }
                .thenIf(size is CustomFeedbackSize.Medium) {
                    width(328.dp)
                }
                .padding(
                    horizontal = size.horizontalPadding,
                    vertical = size.verticalPadding
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FeedbackIcon(icon, size, type)

            FeedbackTextsAndActionContent(size, title, description, action)
        }
    } else {
        Row(
            modifier = Modifier
                .width(328.dp)
                .padding(
                    horizontal = size.horizontalPadding,
                    vertical = size.verticalPadding
                ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FeedbackIcon(icon, size, type)

            FeedbackTextsAndActionContent(size, title, description, action)
        }
    }
}

@Composable
private fun FeedbackIcon(
    icon: ImageVector,
    size: CustomFeedbackSize,
    type: CustomFeedbackStyle
) {
    CustomIcons(
        icon = rememberVectorPainter(image = icon),
        size = size.iconSize,
        style = type.iconStyle
    )

    Spacer(modifier = Modifier
        .thenIf(size is CustomFeedbackSize.Small) {
            width(size.iconPadding)
        }
        .thenIf(size !is CustomFeedbackSize.Small) {
            height(size.iconPadding)
        }
    )
}

@Composable
private fun FeedbackTextsAndActionContent(
    size: CustomFeedbackSize,
    title: String,
    description: String?,
    action: CustomFeedbackAction?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = size.contentAlign
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = size.titleTextStyle,
            textAlign = size.textAlign
        )

        description?.let {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                color = Color.Gray,
                style = size.descriptionTextStyle,
                textAlign = size.textAlign
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
fun CustomFeedbackPreview() {
    IntroToComposeTheme {
        val allTypes = listOf(
            CustomFeedbackStyle.Success(),
            CustomFeedbackStyle.Warning(),
            CustomFeedbackStyle.Error(),
            CustomFeedbackStyle.Neutral(),
            CustomFeedbackStyle.Informative(),
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
                            type = type,
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
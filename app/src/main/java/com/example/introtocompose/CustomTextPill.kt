package com.example.introtocompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.utils.Colors
import com.example.introtocompose.utils.thenIf
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun CustomTextPillPreview() {

    val allCasesStyles = listOf(
        CustomTextPillStyle.Success,
        CustomTextPillStyle.Warning,
        CustomTextPillStyle.Error,
        CustomTextPillStyle.Info,
        CustomTextPillStyle.Neutral,
        CustomTextPillStyle.Custom(Color.Cyan, Color.Black),
    )

    val allCasesIsFilled = listOf(
        true,
        false
    )

    val allCasesIsMarquee = listOf(
        true,
        false
    )

    Column {
        allCasesStyles.forEach { style ->
            Column {
                Row {
                    allCasesIsFilled.forEach { isFilled ->
                        allCasesIsMarquee.forEach { isMarquee ->
                            CustomTextPill(
                                text = if (isMarquee)  "Tech, Video games" else "Text",
                                isFilled = isFilled,
                                style = style,
                                isMarquee = isMarquee
                            )
                        }
                    }
                }
            }
        }
    }

    /*
    //uso unico
    CustomTextPill(
        text = "Text",
        isFilled = true,
        style = CustomTextPillStyle.Success()
    )
    */
}

sealed class CustomTextPillStyle(
    val backgroundColor: Color,
    val textColor: Color,
) {
    data object Success : CustomTextPillStyle(
        backgroundColor = CustomIconsColor.DarkGreen,
        textColor = Color.White,
    )

    data object Warning : CustomTextPillStyle(
        backgroundColor = Color.Yellow,
        textColor = Color.Black,
    )

    data object Error : CustomTextPillStyle(
        backgroundColor = Color.Red,
        textColor = Color.White,
    )

    data object Info : CustomTextPillStyle(
        backgroundColor = Colors.LightBlue,
        textColor = Color.White,
    )

    data object Neutral : CustomTextPillStyle(
        backgroundColor = Color.LightGray,
        textColor = Color.Black,
    )

    data class Custom(
        val backgroundCustomColor: Color,
        val textCustomColor: Color
    ) : CustomTextPillStyle(
        backgroundColor = backgroundCustomColor,
        textColor = textCustomColor,
    )
}

data class CustomTextPillModel(
    val text: String,
    val isFilled: Boolean,
    val style: CustomTextPillStyle
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTextPill(
    text: String,
    isFilled: Boolean,
    style: CustomTextPillStyle,
    isMarquee: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .thenIf(isMarquee) {
                    width(75.dp)
                }
                .padding(1.dp)
                .thenIf(isFilled) {
                    drawBehind {
                        drawRoundRect(
                            color = style.backgroundColor,
                            cornerRadius = CornerRadius(999.dp.toPx())
                        )
                    }
                }
                .thenIf(!isFilled) {
                    drawBehind {
                        drawRoundRect(
                            color = style.backgroundColor,
                            cornerRadius = CornerRadius(999.dp.toPx()),
                            style = Stroke(width = 2f)
                        )
                    }
                }
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .thenIf(isMarquee) {
                        basicMarquee(
                            iterations = Int.MAX_VALUE,
                            spacing = MarqueeSpacing(spacing = 15.dp)
                        )
                    },
                text = text,//.uppercase(Locale.getDefault()),
                maxLines = 1,
                color = if (isFilled) style.textColor else style.backgroundColor,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }

    /* Text(
         modifier = modifier
             .padding(1.dp)
             .thenIf(isMarquee) {
                 basicMarquee()
             }
             .thenIf(isFilled) {
                 drawBehind {
                     drawRoundRect(
                         color = style.backgroundColor,
                         cornerRadius = CornerRadius(999.dp.toPx())
                     )
                 }
             }
             .thenIf(!isFilled) {
                 drawBehind {
                     drawRoundRect(
                         color = style.backgroundColor,
                         cornerRadius = CornerRadius(999.dp.toPx()),
                         style = Stroke(width = 2f)
                     )
                 }
             }
             .padding(horizontal = 8.dp),
         text = text,//.uppercase(Locale.getDefault()),
         maxLines = 1,
         color = if (isFilled) style.textColor else style.backgroundColor,
         style = TextStyle(
             fontSize = 12.sp,
             fontWeight = FontWeight.Medium
         )
     )*/
}
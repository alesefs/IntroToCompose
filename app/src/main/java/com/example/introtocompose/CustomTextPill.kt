package com.example.introtocompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Preview(showBackground = true)
@Composable
fun CustomTextPillPreview() {

    val allCasesStyles = listOf(
        CustomTextPillStyle.Success(),
        CustomTextPillStyle.Warning(),
        CustomTextPillStyle.Error(),
        CustomTextPillStyle.Info(),
        CustomTextPillStyle.Neutral(),
        CustomTextPillStyle.Custom(Color.Cyan, Color.Black),
    )

    val allCasesIsFilled = listOf(
        true,
        false
    )

    Column {
        allCasesStyles.forEach { style ->
            Column {
                Row {
                    allCasesIsFilled.forEach { isFilled ->
                        CustomTextPill(
                            text = "Text",
                            isFilled = isFilled,
                            style = style
                        )
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
    class Success : CustomTextPillStyle(
        backgroundColor = CustomIconsColor.DarkGreen,
        textColor = Color.White,
    )

    class Warning : CustomTextPillStyle(
        backgroundColor = Color.Yellow,
        textColor = Color.Black,
    )

    class Error : CustomTextPillStyle(
        backgroundColor = Color.Red,
        textColor = Color.White,
    )

    class Info : CustomTextPillStyle(
        backgroundColor = Colors.LightBlue,
        textColor = Color.White,
    )

    class Neutral : CustomTextPillStyle(
        backgroundColor = Color.LightGray,
        textColor = Color.Black,
    )

    class Custom(
        backgroundCustomColor: Color,
        textCustomColor: Color
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

@Composable
fun CustomTextPill(
    text: String,
    isFilled: Boolean,
    style: CustomTextPillStyle,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
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
        text = text,
        maxLines = 1,
        color = if (isFilled) style.textColor else style.backgroundColor,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500
        )
    )
}
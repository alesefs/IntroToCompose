package com.example.introtocompose

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.introtocompose.ui.theme.IntroToComposeTheme


@Preview
@Composable
fun CustomButtonConstraintLayoutPreview() {
    CustomButtonConstraintLayout(
        text = "Custom button",
        icon = Icons.Rounded.Notifications,
        enable = false
    )
}

@Composable
fun CustomButtonConstraintLayout(
    text: String,
    icon: ImageVector,
    enable: Boolean = true
) {
    val context = LocalContext.current

    Button(
        modifier = Modifier
            .fillMaxWidth()//var
            .height(48.dp)//var
            .semantics {
                contentDescription = text
            }
            .testTag("TAG_BUTTON_TEST"),
        shape = RoundedCornerShape(0.dp),//var
        enabled = enable,//var
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
        ),//var
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),//var
        onClick = {
            Toast.makeText(context, "clicked!!!", Toast.LENGTH_SHORT).show()
        }
    ) {
        /*
        //direto
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Custom button")
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp),
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications"
            )
        }
        */

        /*
        //Row + ConstraintLayout
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ConstraintLayout(
                modifier = Modifier
            ) {
                val (textRef, iconRef) = createRefs()
                val guidelineFromEnd = createGuidelineFromEnd(24.dp)

                Text(
                    text = "Custom button Custom button Custom button Custom button Custom button ",
                    modifier = Modifier
                        .constrainAs(textRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(guidelineFromEnd)
                            width = Dimension.matchParent
                        }
                        .padding(end = 16.dp),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                )

                Icon(
                    modifier = Modifier
                        .constrainAs(iconRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            linkTo(
                                start = guidelineFromEnd,
                                end = parent.end,
                                startMargin = 0.dp,
                                endMargin = 0.dp,
                                bias = 1F
                            )
                        }
                        .size(16.dp),
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notifications"
                )
            }
        }
        */

        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (textRef, iconRef) = createRefs()
            val guidelineFromEnd = createGuidelineFromEnd(24.dp)

            Text(
                text = text,
                modifier = Modifier
                    .constrainAs(textRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(guidelineFromEnd)
                        width = Dimension.matchParent
                    }
                    .padding(end = 16.dp)
                    .testTag("TAG_TEXT_TEST"),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )

            Icon(
                modifier = Modifier
                    .constrainAs(iconRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        linkTo(
                            start = guidelineFromEnd,
                            end = parent.end,
                            startMargin = 0.dp,
                            endMargin = 0.dp,
                            bias = 1F
                        )
                    }
                    .size(16.dp)
                    .testTag("TAG_ICON_TEST"),
                imageVector = icon,
                contentDescription = "Notifications"
            )
        }

    }
}

@Preview
@Composable
fun CustomButtonDoubleClickPreview() {
    CustomButtonDoubleClick(
        text = "Custom button",
        image = R.drawable.baseline_electric_car_24,
        enable = true
    )
}

@Composable
fun CustomButtonDoubleClick(
    text: String,
    @DrawableRes image: Int,
    enable: Boolean = true
) {
    val context = LocalContext.current
    val rippleColor = if (enable) Color(0xffc8d7eb) else Color(0xffeff0f1)

    CompositionLocalProvider (LocalRippleTheme provides RippleEffect(rippleColor)) {
        Button(
            modifier = Modifier
                .fillMaxWidth()//var
                .height(48.dp)//var
                .semantics {
                    contentDescription = text
                }
                .testTag("TAG_BUTTON_TEST"),
            shape = RoundedCornerShape(24.dp),//var
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enable) Color.Blue else Color.Gray,
                contentColor = if (enable) Color.White else Color.LightGray
            ),//var
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),//var
            onClick = {
                if (enable) {
                    Toast.makeText(context, "clicked enable!!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "clicked disable!!!", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight(0.5f)
                        .testTag("TAG_STRING_TEST"),
                    textAlign = TextAlign.Center
                )
                Image(
                    painterResource(id = image),
                    contentDescription = "eletric car",
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .padding(start = 8.dp)
                        .size(24.dp)
                        .testTag("TAG_IMAGE_TEST"),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(Color.Red, BlendMode.SrcIn)
                )
            }
        }
    }
}

private class RippleEffect(val rippleColor: Color) : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = rippleColor,
        lightTheme = true
    )

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        pressedAlpha = 0.75f,
        focusedAlpha = 1.0f,
        draggedAlpha = 1.0f,
        hoveredAlpha = 1.0f
    )

}

@Preview
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "teste",
    icon: ImageVector? = Icons.Rounded.Notifications,
    enable: Boolean = true,
    isRect: Boolean = false,
    onClick: (() -> Unit)? = null
) {

    val contentPadding =
        PaddingValues(
            horizontal = 24.dp,
            vertical = 8.dp
        )

    IntroToComposeTheme {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .semantics {
                    contentDescription = text
                },
            shape = if (isRect) {
                RoundedCornerShape(0.dp)
            } else {
                RoundedCornerShape(24.dp)
            },
            enabled = enable,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enable) {
                    Color.Blue
                } else {
                    Color.Green
                }
            ),
            contentPadding = contentPadding,
            onClick = { onClick?.invoke() }
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .wrapContentSize(Alignment.Center, true)
                    .background(Color.Red),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if (icon != null) {
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = icon,
                        contentDescription = "$icon"
                    )

                    Spacer(modifier = modifier.size(8.dp))
                }

                Text(
                    text = "Tap $text",
                    fontSize = 15.sp,
                    letterSpacing = 0.0045.sp,
                    modifier = modifier
                )
            }

        }
    }
}
package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.introtocompose.ui.theme.IntroToComposeTheme

private const val topBarLeftBox = "topBarLeftBox"
private const val topBarCenterBox = "topBarCenterBox"
private const val topBarRightBox = "topBarRightBox"
private const val topBarTitle = "topBarTitle"
private const val topBarImage = "topBarImage"
private const val topBarComposition = "topBarComposition"

sealed class TopBarType (
    var leftBox: Boxes? = null,
    var centerBox: Boxes? = null,
    var rightBox: Boxes? = null
) {
    data class Text(val title: String) : TopBarType()
    data object Image : TopBarType()
    data object Composition : TopBarType()
}

data class Boxes(val id: Int, val isShow: Boolean)
data object Boxers

@Composable
fun TopBarConstraintLayout(
    type: TopBarType
) {
    val constraintSet = when(type) {
        is TopBarType.Composition -> {
//            val boxList = setOf(type.leftBox, type.centerBox, type.rightBox)
            val boxList: MutableList<Boxes?> = mutableListOf(type.leftBox, type.centerBox, type.rightBox)
            val countValue = boxList.filterNotNull().size

            Log.d("ALELOG", "Composition: $countValue")

            compositionConstraintSet(countValue)
        }

        is TopBarType.Image -> imageConstraintSet()

        is TopBarType.Text -> {
            val isActionLeft = type.leftBox != null

            val boxList: MutableList<Boxes?> = mutableListOf(type.centerBox, type.rightBox)
            val countValue = boxList.filterNotNull().size

            Log.d("ALELOG", "Text: $countValue")
            textConstraintSet(countValue, isActionLeft)
        }
    }

    /*ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Blue)
    ) {
        val (backAct, titleRef, leftAct, centerAct, rightAct, imageRef, userRef) = createRefs()

        val rightTwoAction = showCenter && showRight
        val guidelineValue = if (rightTwoAction) 96.dp else 48.dp

        val guidelineFromEndRule = createGuidelineFromEnd(guidelineValue)
        val guidelineFromStartRule = createGuidelineFromStart(guidelineValue)


        Box(
            modifier = Modifier
                .width(164.dp)
                .height(48.dp)
                .background(Color.Yellow)
                .constrainAs(userRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Box(
            modifier = Modifier
                .width(128.dp)
                .height(48.dp)
                .background(Color.Magenta)
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        if (showBack) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Green)
                    .constrainAs(backAct) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
        }

        if (showRight) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Red)
                    .constrainAs(rightAct) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }

        if (showCenter) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Gray)
                    .constrainAs(centerAct) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(rightAct.start)
                    }
            )
        }

        if (showLeft) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Cyan)
                    .constrainAs(leftAct) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(centerAct.start)
                    }
            )
        }

        Text(
            text = "I'm a TopBar I'm a TopBar I'm a TopBar",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(guidelineFromStartRule)
                    end.linkTo(guidelineFromEndRule)
                    width = Dimension.fillToConstraints
                },
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
    }*/

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color.Blue)
    ) {

        type.leftBox?.let { TopBarBoxes(topBarLeftBox) }
        type.centerBox?.let { TopBarBoxes(topBarCenterBox) }
        type.rightBox?.let { TopBarBoxes(topBarRightBox) }

        when (type) {
            is TopBarType.Composition -> {
                Box(
                    modifier = Modifier
                        .width(164.dp)
                        .height(48.dp)
                        .background(Color.Yellow)
                        .layoutId(topBarComposition)
                )
            }
            is TopBarType.Image -> {
                Box(
                    modifier = Modifier
                        .width(128.dp)
                        .height(48.dp)
                        .background(Color.Magenta)
                        .layoutId(topBarImage)
                )
            }
            is TopBarType.Text -> {
                Text(
                    text = type.title,
                    modifier = Modifier.layoutId(topBarTitle),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                )
            }
        }

    }
}

@Composable
fun TopBarBoxes(layoutId: String) {
    Box(
        modifier = Modifier
            .layoutId(layoutId = layoutId)
            .size(48.dp)
            .border(1.dp, Color.Red, RectangleShape)
    )
}

fun compositionConstraintSet(count: Int) = ConstraintSet {
    val boxLeftRef = createRefFor(topBarLeftBox)
    val boxCenterRef = createRefFor(topBarCenterBox)
    val boxRightRef = createRefFor(topBarRightBox)
    val compositionRef = createRefFor(topBarComposition)

    val guidelineValue = (48 * count + 4).dp
    val guidelineFromEnd = createGuidelineFromEnd(guidelineValue)

    constrain(boxRightRef) {
        end.linkTo(parent.end, margin = 4.dp)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(boxCenterRef) {
        end.linkTo(boxRightRef.start)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(boxLeftRef) {
        end.linkTo(boxCenterRef.start)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(compositionRef) {
        linkTo(top = parent.top, bottom = parent.bottom)
        linkTo(
            start = parent.start, startMargin = 16.dp,
            end = guidelineFromEnd, endMargin = 16.dp,
            bias = 0f
        )
        width = Dimension.fillToConstraints
    }
}

fun imageConstraintSet() = ConstraintSet {
    val boxLeftRef = createRefFor(topBarLeftBox)
    val boxCenterRef = createRefFor(topBarCenterBox)
    val boxRightRef = createRefFor(topBarRightBox)
    val imageRef = createRefFor(topBarImage)

    constrain(boxRightRef) {
        end.linkTo(parent.end, margin = 4.dp)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(boxCenterRef) {
        end.linkTo(boxRightRef.start)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(boxLeftRef) {
        end.linkTo(boxCenterRef.start)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(imageRef) {
        start.linkTo(parent.start, margin = 16.dp)
        linkTo(top = parent.top, bottom = parent.bottom)
    }
}

fun textConstraintSet(countValue: Int, isActionLeft: Boolean) = ConstraintSet {
    val boxLeftRef = createRefFor(topBarLeftBox)
    val boxCenterRef = createRefFor(topBarCenterBox)
    val boxRightRef = createRefFor(topBarRightBox)
    val titleRef = createRefFor(topBarTitle)

    val guidelineValue = if (isActionLeft && countValue == 0) {
        (48 + 4).dp
    } else {
        (48 * countValue + 4).dp
    }
    val guidelineFromStart = createGuidelineFromStart(guidelineValue)
    val guidelineFromEnd = createGuidelineFromEnd(guidelineValue)

    constrain(boxLeftRef) {
        start.linkTo(parent.start, margin = 4.dp)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(boxRightRef) {
        end.linkTo(parent.end, margin = 4.dp)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(boxCenterRef) {
        end.linkTo(boxRightRef.start)
        linkTo(top = parent.top, bottom = parent.bottom)
    }

    constrain(titleRef) {
        linkTo(top = parent.top, bottom = parent.bottom)
        linkTo(
            start = guidelineFromStart, startMargin = 16.dp,
            end = guidelineFromEnd, endMargin = 16.dp,
            bias = 0.5f
        )
        width = Dimension.fillToConstraints
    }
}

@Preview
@Composable
fun TopBarConstraintLayoutTextPreview() {
    IntroToComposeTheme {
        TopBarConstraintLayout(
            type = TopBarType.Text("I'm a TopBar I'm a TopBar I'm a TopBar").apply {
                leftBox = Boxes(0, true)
                centerBox = Boxes(0, true)
                rightBox = Boxes(0, true)
            }
        )
    }
}

@Preview
@Composable
fun TopBarConstraintLayoutImagePreview() {
    IntroToComposeTheme {
        TopBarConstraintLayout(
            type = TopBarType.Image.apply {
                leftBox = Boxes(0, true)
                centerBox = Boxes(0, true)
                rightBox = Boxes(0, true)
            }
        )
    }
}

@Preview
@Composable
fun TopBarConstraintLayoutCompositionPreview() {
    IntroToComposeTheme {
        TopBarConstraintLayout(
            type = TopBarType.Composition.apply {
                leftBox = Boxes(0, true)
                centerBox = Boxes(0, true)
                rightBox = Boxes(0, true)
            }
        )
    }
}
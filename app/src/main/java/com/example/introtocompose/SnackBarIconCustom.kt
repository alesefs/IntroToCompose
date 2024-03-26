package com.example.introtocompose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.introtocompose.icons.BaselineCardGiftcard_24
import com.example.introtocompose.icons.BaselineElectricCar_24
import com.example.introtocompose.icons.IcLauncherForeground
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import kotlinx.coroutines.launch

sealed class SnackBarType(
    val backgroundColor: Color,
    val contentColor: Color,
    val actionColor: Color,
    open val supportVisual: ImageVector
) {
    data object Success : SnackBarType(
        backgroundColor = Color.Green,
        contentColor = Color.White,
        actionColor = Color.White,
        supportVisual = Images.Icons.BaselineCardGiftcard_24
    )

    data object Alert : SnackBarType(
        backgroundColor = Color.Yellow,
        contentColor = Color.Black,
        actionColor = Color.Black,
        supportVisual = Images.Icons.BaselineElectricCar_24
    )

    data object Error : SnackBarType(
        backgroundColor = Color.Red,
        contentColor = Color.White,
        actionColor = Color.White,
        supportVisual = Images.Icons.IcLauncherForeground
    )

    data class System(
        override val supportVisual: ImageVector = Icons.Default.AccountBox
    ) : SnackBarType(
        backgroundColor = Color.DarkGray,
        contentColor = Color.White,
        actionColor = Color.White,
        supportVisual = supportVisual
    )

    data class Informative(
        override val supportVisual: ImageVector = Icons.Default.AccountBox
    ) : SnackBarType(
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        actionColor = Color.White,
        supportVisual = supportVisual
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CustomSnackBarIcon(
    type: SnackBarType,
    message: String,
    duration: SnackbarDuration,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.BottomCenter,
    isStacked: Boolean = false,
    actionLabel: String? = null,
    action: (() -> Unit)? = null
) {
    val snackBarState = remember { SnackbarHostState() }
    val coroutinesScope = rememberCoroutineScope()

    coroutinesScope.launch {
        snackBarState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = duration
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Transparent),
        contentAlignment = contentAlignment
    ) {
        SnackbarHost(
            hostState = snackBarState,
        ) {
            if (isStacked) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    StackedSnackBar(message, type, actionLabel, action)
                }
            } else {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    InLineSnackBar(message, type, actionLabel, action)
                }
            }
        }
    }
}

@Composable
fun StackedSnackBar(
    message: String,
    type: SnackBarType,
    actionLabel: String?,
    action: (() -> Unit)?
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = type.backgroundColor,
            contentColor = type.contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically, true)
                .padding(16.dp)
        ) {
            Row {
                Icon(
                    type.supportVisual,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = type.contentColor
                )

                Text(
                    text = message,
                    color = type.contentColor,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }

            actionLabel?.let {
                action?.let {
                    Text(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 16.dp)
                            .clickable {
                                action.invoke()
                            },
                        text = actionLabel,
                        color = type.actionColor,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            fontSize = 15.sp,
                            letterSpacing = 0.9.sp,
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Bottom,
                                trim = LineHeightStyle.Trim.LastLineBottom
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun InLineSnackBar(
    message: String,
    type: SnackBarType,
    actionLabel: String?,
    action: (() -> Unit)?
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = type.backgroundColor,
            contentColor = type.contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically, true)
                .padding(16.dp),
        ) {
            val (iconRefs, textRefs, btnRefs) = createRefs()

            Icon(
                type.supportVisual,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(iconRefs) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(24.dp),
                tint = type.contentColor
            )

            Text(
                text = message,
                color = type.contentColor,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(textRefs) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(textRefs.end)
                        end.linkTo(btnRefs.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 16.dp)

            )

            actionLabel?.let {
                action?.let {
                    Text(
                        modifier = Modifier
                            .constrainAs(btnRefs) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                            .clickable {
                                action.invoke()
                            },
                        text = actionLabel,
                        color = type.actionColor,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            fontSize = 15.sp,
                            letterSpacing = 0.8.sp,
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Center,
                                trim = LineHeightStyle.Trim.FirstLineTop
                            )
                        )
                    )
                }
            }

            createHorizontalChain(
                iconRefs,
                textRefs,
                btnRefs,
                chainStyle = ChainStyle.SpreadInside
            )
        }
    }
}

@Preview
@Composable
fun PBSnackBarPreview() {
    IntroToComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            val isClicked = remember { mutableStateOf(false) }

            Box(contentAlignment = Alignment.Center) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                    onClick = { isClicked.value = !isClicked.value }
                ) {
                    Text(text = "show SnackBar")
                }
            }

            if (isClicked.value) {
                CustomSnackBarIcon(
                    type = SnackBarType.Error,
                    message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    actionLabel = "Click",
                    duration = SnackbarDuration.Short,
                    contentAlignment = Alignment.BottomCenter,
                    isStacked = true,
                ) {
                    isClicked.value = !isClicked.value
                }
            }
        }
    }
}

@Preview
@Composable
fun PBSnackBar2Preview() {
    IntroToComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            val isClicked = remember { mutableStateOf(false) }

            Box(contentAlignment = Alignment.Center) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                    onClick = { isClicked.value = !isClicked.value }
                ) {
                    Text(text = "show SnackBar")
                }
            }

            if (isClicked.value) {
                CustomSnackBarIcon(
                    type = SnackBarType.Informative(Icons.Default.AccountCircle),
                    message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    actionLabel = "Click",
                    duration = SnackbarDuration.Indefinite,
                    contentAlignment = Alignment.BottomCenter,
                    isStacked = true,
                ) {
                    isClicked.value = !isClicked.value
                }
            }
        }
    }
}

@Preview
@Composable
fun PBSnackBar3Preview() {
    IntroToComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            val isClicked = remember { mutableStateOf(false) }

            Box(contentAlignment = Alignment.Center) {
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp),
                        onClick = { isClicked.value = !isClicked.value }
                    ) {
                        Text(text = "show SnackBar")
                    }
                }
            }

            if (isClicked.value) {
                CustomSnackBarIcon(
                    type = SnackBarType.Success,
                    message = "Lorem Ipsum is simply.",
                    actionLabel = "Click",
                    duration = SnackbarDuration.Short,
                    contentAlignment = Alignment.BottomCenter,
                    isStacked = false,
                ) {
                    isClicked.value = !isClicked.value
                }
            }
        }
    }
}


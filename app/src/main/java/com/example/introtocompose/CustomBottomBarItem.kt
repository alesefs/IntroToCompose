package com.example.introtocompose

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.introtocompose.ui.theme.IntroToComposeTheme

/*@Preview
@Composable
fun CustomBottomBarItemPreview() {
    IntroToComposeTheme {
        Column {
            CustomBottomBarItem(
                activeIcon = Icons.Filled.AccountCircle,
                idleIcon = Icons.Outlined.AccountCircle,
                isSelect = true,
                label = "Settigns",
                onClickSelection = { },
                onAction = { },
            )

            Spacer(modifier = Modifier.size(8.dp))

            CustomBottomBarItem(
                activeIcon = Icons.Filled.AccountCircle,
                idleIcon = Icons.Outlined.AccountCircle,
                isSelect = false,
                label = "Settigns",
                onClickSelection = { },
                onAction = { },
            )

            Spacer(modifier = Modifier.size(8.dp))

            CustomBottomBarItemBrand(
                activeIcon = Icons.Filled.AccountCircle,
                idleIcon = Icons.Outlined.AccountCircle,
                isSelect = true,
                label = "Settigns",
                onClickSelection = { },
                onAction = { },
            )

            Spacer(modifier = Modifier.size(8.dp))

            CustomBottomBarItemBrand(
                activeIcon = Icons.Filled.AccountCircle,
                idleIcon = Icons.Outlined.AccountCircle,
                isSelect = false,
                label = "Settigns",
                onClickSelection = { },
                onAction = { },
            )
        }
    }
}*/

data class CustomBottomBarItemModel(
    val idleIcon: ImageVector,
    val activeIcon: ImageVector,
    val label: String,
    val onClick: () -> Unit
)

@Composable
fun CustomBottomBarItem(
    idleIcon: ImageVector,
    activeIcon: ImageVector,
    label: String,
    isSelect: Boolean,
    onIndicator: (BarIndicator?) -> Unit,
    modifier: Modifier = Modifier,
    onClickSelection: () -> Unit,
    onAction: () -> Unit
) {
    val localDensity = LocalDensity.current

    Box(
        modifier = modifier
            .width(70.dp)
            .height(48.dp)
            .clip(shape = RectangleShape)
            .background(
                if (isSelect) {
                    Color.Magenta
                } else {
                    Color.Blue
                }
            )
            .clickable(role = Role.Tab) {
                onClickSelection()
                onAction()
            }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxSize()
                /*.onGloballyPositioned {
                    if (isSelect) {
                        val boundsRect = it.boundsInWindow()

                        with(localDensity) {
                            onIndicator(
                                BarIndicator(
                                    posX = boundsRect.left.toDp(),
                                    width = boundsRect.width.toDp()
                                )
                            )
                        }
                    }
                }*/,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = if (isSelect) {
                    activeIcon
                } else {
                    idleIcon
                },
                contentDescription = null,
                tint = if (isSelect) {
                    Color.White
                } else {
                    Color.LightGray
                }
            )

            Text(
                modifier = Modifier
                    .onGloballyPositioned {
                    if (isSelect) {
                        val boundsRect = it.boundsInWindow()

                        with(localDensity) {
                            onIndicator(
                                BarIndicator(
                                    posX = boundsRect.left.toDp(),
                                    width = boundsRect.width.toDp()
                                )
                            )
                        }
                    }
                },
                text = label,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = if (isSelect) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
            )
        }
    }
}

@Composable
fun CustomBottomBarItemBrand(
    modifier: Modifier = Modifier,
    idleIcon: ImageVector,
    activeIcon: ImageVector,
    label: String,
    isSelect: Boolean,
    onIndicator: (BarIndicator?) -> Unit,
    onClickSelection: () -> Unit,
    onAction: () -> Unit
) {
    val localDensity = LocalDensity.current

    Box(
        modifier = modifier
            .width(80.dp)
            .height(64.dp)
            .clip(shape = RectangleShape)
            .clickable(role = Role.Tab) {
                onClickSelection()
                onAction()
            }
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(48.dp)
                .clip(shape = RectangleShape)
                .zIndex(1f)
                .background(
                    if (isSelect) {
                        Color.Magenta
                    } else {
                        Color.Blue
                    }
                )
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxSize()
                .zIndex(2f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(shape = CircleShape)
                    .background(
                        if (isSelect) {
                            Color.Magenta
                        } else {
                            Color.Blue
                        }
                    )
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    imageVector = if (isSelect) {
                        activeIcon
                    } else {
                        idleIcon
                    },
                    contentDescription = null,
                    tint = if (isSelect) {
                        Color.Blue
                    } else {
                        Color.LightGray
                    }
                )
            }

            Text(
                modifier = Modifier
                    .onGloballyPositioned {
                        if (isSelect) {
                            val boundsRect = it.boundsInWindow()

                            with(localDensity) {
                                onIndicator(
                                    BarIndicator(
                                        posX = boundsRect.left.toDp(),
                                        width = boundsRect.width.toDp()
                                    )
                                )
                            }
                        }
                    },
                text = label,
                color = Color.Yellow,
                fontSize = 12.sp,
                fontWeight = if (isSelect) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
            )
        }
    }
}

@Composable
fun CustomBottomBar(
    items: List<CustomBottomBarItemModel>,
    selectItem: Int,
    showBrand: Boolean = false,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (showBrand) 64.dp else 48.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            )
    ) {
        val placeOfBrand = items.lastIndex / 2

        var indicator: BarIndicator? by remember {
            mutableStateOf(null)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
                .background(Color.Blue)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            itemsIndexed(items, itemContent = { index, item ->
                var itemShape: Shape = RectangleShape

                if (item == items.first()) {
                    itemShape = RoundedCornerShape(topStart = 16.dp)
                } else if (index == items.lastIndex) {
                    itemShape = RoundedCornerShape(topEnd = 16.dp)
                } else if (showBrand && index == placeOfBrand) {
                    itemShape = GenericShape { size, layoutDirection ->
                        size.width
                        addRect(
                            Rect(
                                topLeft = Offset(0f, 42f),
                                bottomRight = Offset(size.width, size.height)
                            )
                        )
                        addOval(
                            Rect(center = Offset(size.width / 2, 48f), radius = 48f)
                        )
                    }

                } else {
                    RectangleShape
                }

                if (showBrand && index == placeOfBrand) {
                    CustomBottomBarItemBrand(
                        modifier = Modifier.clip(itemShape),
                        activeIcon = item.activeIcon,
                        idleIcon = item.idleIcon,
                        label = item.label,
                        isSelect = index == selectItem,
                        onIndicator = { indicator = it },
                        onClickSelection = { onClick(index) },
                        onAction = item.onClick
                    )
                } else {
                    CustomBottomBarItem(
                        modifier = Modifier.clip(itemShape),
                        activeIcon = item.activeIcon,
                        idleIcon = item.idleIcon,
                        label = item.label,
                        isSelect = index == selectItem,
                        onIndicator = { indicator = it },
                        onClickSelection = { onClick(index) },
                        onAction = item.onClick
                    )
                }
            })
        }

        CustomBottomBarIndicator(indicator = indicator)
    }
}

data class BarIndicator(
    val posX: Dp,
    val width: Dp
)

@Composable
fun CustomBottomBarIndicator(
    indicator: BarIndicator?,
    modifier: Modifier = Modifier,
    posXTransitionSpec: @Composable Transition.Segment<BarIndicator>.() -> FiniteAnimationSpec<Dp> = {
        SpringSpec(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = Dp.VisibilityThreshold
        )
    },
    widthTransitionSpec: @Composable Transition.Segment<BarIndicator>.() -> FiniteAnimationSpec<Dp> = {
        SpringSpec(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = Dp.VisibilityThreshold
        )
    },
    content: @Composable () -> Unit = {
        Box (
            modifier = modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.Yellow)
        )
    }
) {
    indicator?.let {
        val transition = updateTransition(targetState = it, label = "animation transition")

        val posX: Dp by transition.animateDp(
            label = "posX Animation",
            transitionSpec = posXTransitionSpec
        ) { posX ->
            posX.posX
        }

        val width: Dp by transition.animateDp(
            label = "Width Animation",
            transitionSpec = widthTransitionSpec
        ) { width ->
            width.width
        }

        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Spacer(modifier = Modifier.width(posX))
            Box(modifier = Modifier.width(width).align(Alignment.Bottom)) {
                content()
            }
        }
    }
}

val listOfItems = listOf(
    CustomBottomBarItemModel(
        activeIcon = Icons.Filled.AccountCircle,
        idleIcon = Icons.Outlined.AccountCircle,
        label = "Set",
        onClick = { }
    ),
    CustomBottomBarItemModel(
        activeIcon = Icons.Filled.AccountCircle,
        idleIcon = Icons.Outlined.AccountCircle,
        label = "Settings",
        onClick = { }
    ),
    CustomBottomBarItemModel(
        activeIcon = Icons.Filled.AccountCircle,
        idleIcon = Icons.Outlined.AccountCircle,
        label = "set",
        onClick = { }
    ),
    CustomBottomBarItemModel(
        activeIcon = Icons.Filled.AccountCircle,
        idleIcon = Icons.Outlined.AccountCircle,
        label = "Config",
        onClick = { }
    ),
    CustomBottomBarItemModel(
        activeIcon = Icons.Filled.AccountCircle,
        idleIcon = Icons.Outlined.AccountCircle,
        label = "Threshold",
        onClick = { }
    )
)

@Preview
@Composable
fun CustomBottomBar1Preview() {
    IntroToComposeTheme {
        var selectItem by remember {
            mutableIntStateOf(0)
        }

        CustomBottomBar(
            items = listOfItems,
            selectItem = selectItem,
            showBrand = true,
            onClick = { selectItem = it }
        )
    }
}

@Preview
@Composable
fun CustomBottomBar2Preview() {
    IntroToComposeTheme {
        var selectItem by remember {
            mutableIntStateOf(0)
        }

        CustomBottomBar(
            items = listOfItems,
            selectItem = selectItem,
            showBrand = false,
            onClick = { selectItem = it }
        )
    }
}
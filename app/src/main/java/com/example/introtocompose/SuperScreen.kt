package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SuperScreen() {
    Scaffold(
        topBar = {
            /*TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = { Text(text = "I'm a topBar") },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { *//**//* }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    Row {
                        IconButton(
                            modifier = Modifier.wrapContentSize(),
                            onClick = { *//**//* }
                        ) {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                        }

                        IconButton(
                            modifier = Modifier.wrapContentSize(),
                            onClick = { *//**//* }
                        ) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                        }
                    }
                }
            )*/
            TopBarConstraintLayout(
                type = TopBarType.Text("I'm a TopBar I'm a TopBar I'm a TopBar").apply {
                    leftBox = Boxes(0, true)
                    centerBox = Boxes(0, true)
                    rightBox = Boxes(0, true)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color.Blue,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue,
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    }

                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }

                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    }

                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }
                }
            }
        }
    ) {
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(it)
            ) {
//                Text(text = "I'm a topBar")

                /*IconButton(
                    modifier = Modifier
                        .size(56.0.dp)
                        .clip(CircleShape)
                        .shadow(
                            elevation = 1.dp,
                            shape = CircleShape,
                            ambientColor = Color.Red,
                            spotColor = Color.Red,
                        )

//                    .graphicsLayer(
//                        shadowElevation = 4f,
//                        ambientShadowColor = Color.Red,
//                        spotShadowColor = Color.Gray,
//                    )
//                        .offset(10.dp, 10.dp)
//                        .blur(radius = 2.dp)
                    ,
                    colors = IconButtonColors(
                        containerColor = Color.Blue,
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContentColor = Color.DarkGray
                    ),
                    onClick = { *//**//* }
                ) {
                    Icon(Icons.Filled.Add, null, Modifier.size(24.0.dp))
                }*/

                Button(
                    modifier = Modifier
                        .size(56.0.dp)
                        .shadow(
                            elevation = 16.dp,
                            shape = CircleShape,
                            ambientColor = Color.Red,
                            spotColor = Color.Red,
                        ),
                    shape = CircleShape,
                    colors =  ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContentColor = Color.DarkGray
                    ),
                    contentPadding = PaddingValues(8.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 16.dp,
                        pressedElevation = 16.dp,
                        focusedElevation = 16.dp,
                        hoveredElevation = 16.dp,
                        disabledElevation = 16.dp
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(Icons.Filled.Add, null, Modifier.size(24.dp))
                }

                /*Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(56.dp)
                        .clip(CircleShape)
                        .clickable {
                            Log.d("ALELOG", "moneyCounter")
                        }
                    ,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Blue,
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContentColor = Color.DarkGray
                    ),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp,
                        pressedElevation = 16.dp,
                        focusedElevation = 16.dp,
                        hoveredElevation = 16.dp,
                        draggedElevation = 16.dp,
                        disabledElevation = 16.dp
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Add, null, Modifier.size(24.0.dp))
                    }
                }*/
            }
        }
    }
}

@Preview
@Composable
fun CustomBottomBar() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Blue)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = Color.White)
                    }
                    Text(text = "setting", color = Color.White)
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = Color.White)
                    }
                    Text(text = "setting", color = Color.White)
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = Color.White)
                    }
                    Text(text = "setting", color = Color.White)
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = Color.White)
                    }
                    Text(text = "setting", color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomBottomBarUpper(
    indicator: Int = 2
) {
    /*ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Blue)
    ) {
        val (centerRef) = createRefs()


        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .shadow(
                    elevation = 1.dp,
                    shape = CircleShape,
                    ambientColor = Color.Red,
                    spotColor = Color.Red,
                )
                .size(56.0.dp)
            ,
            colors = IconButtonColors(
                containerColor = Color.Blue,
                disabledContainerColor = Color.LightGray,
                contentColor = Color.White,
                disabledContentColor = Color.DarkGray
            ),
            onClick = { *//**//* }
        ) {
            Icon(Icons.Filled.Add, null, Modifier.size(24.0.dp))
        }
    }*/

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        val (boxRef, centerBtnRef, leftRowBtnRef, rightRowBtnRef, boxIndicatorRef, ponterRef) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(Color.Green)
                .constrainAs(boxIndicatorRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Box(
            modifier = Modifier
                .width(42.dp)
                .height(6.dp)
                .background(Color.Red)
                .constrainAs(ponterRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Blue)
                .constrainAs(boxRef) {
                    bottom.linkTo(boxIndicatorRef.top)
                    start.linkTo(parent.start)
                }
        )

        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .border(3.dp, Color.Blue, CircleShape)
                .size(56.0.dp)
                .constrainAs(centerBtnRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = IconButtonColors(
                containerColor = Color.Green,
                disabledContainerColor = Color.LightGray,
                contentColor = Color.DarkGray,
                disabledContentColor = Color.DarkGray
            ),
            onClick = { }
        ) {
            Icon(Icons.Filled.Add, null, Modifier.size(24.0.dp))
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(leftRowBtnRef) {
                    top.linkTo(boxRef.top)
                    bottom.linkTo(boxRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(centerBtnRef.start)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = Color.White)
                }
                Text(text = "setting", color = Color.White)
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = Color.White)
                }
                Text(text = "setting", color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(rightRowBtnRef) {
                    top.linkTo(boxRef.top)
                    bottom.linkTo(boxRef.bottom)
                    start.linkTo(centerBtnRef.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = Color.White)
                }
                Text(text = "setting", color = Color.White)
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = Color.White)
                }
                Text(text = "setting", color = Color.White)
            }
        }
    }
}

/*
fun Modifier.vectorShadow(
    path: Path,
    x: Dp,
    y: Dp,
    radius: Dp
) = composed(
    inspectorInfo = {
        name = "vectorShadow"
        value = path
        value = x
        value = y
        value = radius
    },
    factory = {
        val paint = remember {
            Paint()
        }

        val frameworkPaint = remember {
            paint.asFrameworkPaint()
        }

        val color = Color.DarkGray
        val dx: Float
        val dy: Float
        val radiusInPx: Float

        with(LocalDensity.current) {
            dx = x.toPx()
            dy = y.toPx()
            radiusInPx = radius.toPx()
        }

        drawBehind {
            this.drawIntoCanvas {
                val transparent = color
                    .copy(alpha = 0f)
                    .toArgb()
                frameworkPaint.color = transparent

                frameworkPaint.setShadowLayer(
                    radiusInPx,
                    dx,
                    dy,
                    color
                        .copy(alpha = .7f)
                        .toArgb()
                )
                it.drawPath(path, paint)
            }
        }
    }
)*/

@Composable
fun SuperTopBar(
    leftContent: @Composable () -> Unit? = {},
    centerContent: @Composable () -> Unit? = {},
    rightContent: @Composable () -> Unit? = {},
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Magenta)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftContent()
            centerContent()
            rightContent()
        }
    }
}


@Preview
@Composable
fun SuperUse() {
    SuperTopBar(
        leftContent = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            }
        },

        centerContent = {
            Text(text = "I'm a TopBar")
        },

        rightContent = {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                }
            }
        }
    )
}
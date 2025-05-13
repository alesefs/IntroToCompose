package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.introtocompose.utils.thenIf

@Composable
fun CustomCardItemList(
    modifier: Modifier = Modifier,
    alignSupportVisual: AlignSupportVisual = AlignSupportVisual.Top,
    contentSupportVisual: @Composable (() -> CustomCardItemList.SupportVisual)? = null,
    contentCentral: @Composable (() -> CustomCardItemList.Central),
    contentAction: @Composable (() -> CustomCardItemList.Action)? = null,
    subContent: @Composable (() -> Unit)? = null,
    action: (() -> Unit)? = null
) {
    /*Box(modifier = modifier) {
        Row (
            modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            if (contentSupportVisual != null) {

                Box (
                    modifier
                        .background(Color.Green)
                ) {
                    contentSupportVisual.invoke()
                }
                Spacer(modifier.width(16.dp))
            }

            Box (
                modifier
                    .weight(1f)
                    .background(Color.Green)
            ) {
                contentCentral.invoke()
            }

            if (contentAction != null) {
                Spacer(modifier.width(16.dp))
                Box(
                    modifier
                        .background(Color.Green)
                ) {
                    contentAction.invoke()
                }
            }
        }
    }*/

    Box(modifier = modifier) {
        /*ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.Blue)
                .thenIf(action != null) {
                    clickable {
                        action?.invoke()
                    }
                }
        ) {
            val (contentSupportVisualRef, contentCentralRef, contentActionRef, contetnSubRef) = createRefs()

            if (contentSupportVisual != null) {
                Box(
                    Modifier
                        .background(Color.Green)
                        .constrainAs(contentSupportVisualRef) {
                            top.linkTo(parent.top)
                            if (alignSupportVisual == AlignSupportVisual.Center) bottom.linkTo(
                                parent.bottom
                            )
                            start.linkTo(parent.start)
                        }
                        .padding(end = 16.dp)
                ) {
                    contentSupportVisual.invoke()
                }
            }

            Box(
                Modifier
                    .background(Color.Green)
                    .constrainAs(contentCentralRef) {
                        top.linkTo(parent.top)
                        start.linkTo(contentSupportVisualRef.end)
                        end.linkTo(contentActionRef.start)
                        width = Dimension.fillToConstraints
                    }
            ) {
                contentCentral.invoke()
            }

            if (contentAction != null) {
                Box(
                    Modifier
                        .background(Color.Green)
                        .constrainAs(contentActionRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(contentCentralRef.end)
                            end.linkTo(parent.end)
                        }
                        .padding(start = 16.dp)
                ) {
                    contentAction.invoke()
                }
            }

            Box(
                Modifier
                    .background(Color.Red)
                    .constrainAs(contetnSubRef) {
                        top.linkTo(contentCentralRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ) {
                contentCentral.invoke()
            }

        }*/

        Column {
            CustomListMainContent(alignSupportVisual, contentSupportVisual, contentCentral, contentAction)
        }
    }
}

@Composable
fun CustomListMainContent(
    alignSupportVisual: AlignSupportVisual = AlignSupportVisual.Top,
    contentSupportVisual: @Composable (() -> CustomCardItemList.SupportVisual)? = null,
    contentCentral: @Composable (() -> CustomCardItemList.Central),
    contentAction: @Composable (() -> CustomCardItemList.Action)? = null,
    action: (() -> Unit)? = null
) {
    ConstraintLayout(
        modifier = Modifier
//            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.Blue)
            .thenIf(action != null) {
                clickable {
                    action?.invoke()
                }
            }
    ) {
        val (contentSupportVisualRef, contentCentralRef, contentActionRef, contentSubRef) = createRefs()

        if (contentSupportVisual != null) {
            Box(
                Modifier
                    .background(Color.Green)
                    .constrainAs(contentSupportVisualRef) {
                        top.linkTo(parent.top)
                        if (alignSupportVisual == AlignSupportVisual.Center) bottom.linkTo(
                            parent.bottom
                        )
                        start.linkTo(parent.start)
                    }
                    .padding(end = 16.dp)
            ) {
                contentSupportVisual.invoke()
            }
        }

        Box(
            Modifier
                .background(Color.Green)
                .constrainAs(contentCentralRef) {
                    top.linkTo(parent.top)
                    start.linkTo(contentSupportVisualRef.end)
                    end.linkTo(contentActionRef.start)
                    width = Dimension.fillToConstraints
                }
        ) {
            contentCentral.invoke()
        }

        if (contentAction != null) {
            Box(
                Modifier
                    .background(Color.Green)
                    .constrainAs(contentActionRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(contentCentralRef.end)
                        end.linkTo(parent.end)
                    }
                    .padding(start = 16.dp)
            ) {
                contentAction.invoke()
            }
        }

        /*Box(
            Modifier
                .background(Color.Red)
                .constrainAs(contentSubRef) {
                    top.linkTo(contentCentralRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        ) {
            contentCentral.invoke()
        }*/
    }
}

sealed class CustomCardItemList {
    data object SupportVisual : CustomCardItemList()
    data object Central : CustomCardItemList()
    data object Action : CustomCardItemList()
}

sealed class AlignSupportVisual {
    data object Top : AlignSupportVisual()
    data object Center : AlignSupportVisual()
}

@Composable
fun CustomCardItemList.SupportVisual.imageSmall(
    modifier: Modifier = Modifier,
) : CustomCardItemList.SupportVisual {

    Box(modifier = modifier) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = rememberVectorPainter(image = Icons.AutoMirrored.Rounded.Send),
            contentDescription = null,
        )
    }

    return this
}

@Composable
fun CustomCardItemList.SupportVisual.imageLarge(
    modifier: Modifier = Modifier,
) : CustomCardItemList.SupportVisual {

    Box(modifier = modifier) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = rememberVectorPainter(image = Icons.AutoMirrored.Rounded.Send),
            contentDescription = null
        )
    }

    return this
}

@Composable
fun CustomCardItemList.Central.texts(
    modifier: Modifier = Modifier,
) : CustomCardItemList.Central {

    Column(modifier = modifier) {
        Text(text = "Text 1")
        Text(text = "Text 2")
        Text(text = "Text 3")
        Text(text = "Text 4")
    }

    return this
}

@Composable
fun CustomCardItemList.Action.navigation(
    modifier: Modifier = Modifier,
) : CustomCardItemList.Action {

    Box(modifier = modifier) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = rememberVectorPainter(image = Icons.AutoMirrored.Outlined.ArrowForward),
            contentDescription = null
        )
    }

    return this
}

@Preview(showBackground = true)
@Composable
fun CustomCardItemListSptVisTopSmallPreview() {
    CustomCardItemList(
        alignSupportVisual = AlignSupportVisual.Top,
        contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
        contentCentral = { CustomCardItemList.Central.texts() },
        contentAction = { CustomCardItemList.Action.navigation() }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomCardItemListSptVisCenterSmallPreview() {
    CustomCardItemList(
        alignSupportVisual = AlignSupportVisual.Center,
        contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
        contentCentral = { CustomCardItemList.Central.texts() },
        contentAction = { CustomCardItemList.Action.navigation() }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomCardItemListSptVisTopLargePreview() {
    CustomCardItemList(
        alignSupportVisual = AlignSupportVisual.Top,
        contentSupportVisual = { CustomCardItemList.SupportVisual.imageLarge() },
        contentCentral = { CustomCardItemList.Central.texts() },
        contentAction = { CustomCardItemList.Action.navigation() }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomCardItemListSptVisCenterLargePreview() {
    CustomCardItemList(
        alignSupportVisual = AlignSupportVisual.Center,
        contentSupportVisual = { CustomCardItemList.SupportVisual.imageLarge() },
        contentCentral = { CustomCardItemList.Central.texts() },
        contentAction = { CustomCardItemList.Action.navigation() },
        action = {
            Log.d("ALELOG", "CustomCardItemListSptVisCenterLargePreview: Click")
        }
    )
}
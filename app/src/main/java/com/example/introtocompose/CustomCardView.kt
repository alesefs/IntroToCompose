package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import java.util.Locale

/*enum class CustomCardViewStyle(val showDecorate: Boolean) {
    Decorate(true),
    VerticalImage(false),
    HorizontalImage(false)
}*/

sealed class CustomCardViewStyle {

    class Decorate(
        val decoration: CustomCardDecoration = CustomCardDecoration(12.dp, Color.Blue)
    ) : CustomCardViewStyle()

    class VerticalImage(
        val image: Int = R.drawable.ic_launcher_background
    ) : CustomCardViewStyle()

    class HorizontalImage(
        val image: Int = R.drawable.ic_launcher_background
    ) : CustomCardViewStyle()
}


object Colors {
    val Teal = Color(red = 0, green = 220, blue = 220)
    val TealUlong = Color(0xFF00DCDC)
    val Petroleum = Color(red = 0, green = 80, blue = 80)
    val PetroleumUlong = Color(0xFF005050)
    val LightBlue = Color(0xFF005599)
}

object CustomCardView {
    object Header
    object Center
    object Footer
}

@Composable
fun CustomCardView(
    style: CustomCardViewStyle,
    customCardStyle: CustomCardStyle,
    modifier: Modifier = Modifier,
    contentHeader: @Composable (() -> CustomCardView.Header)? = null,
    contentCenter: @Composable (() -> CustomCardView.Center)? = null,
    contentFooter: @Composable (() -> CustomCardView.Footer)? = null,
//    contentHeader: @Composable() (() -> Unit)? = { CustomCardViewHeader() },
//    contentCenter: @Composable (() -> Unit)? = { CustomCardViewContent() },
//    contentFooter: @Composable (() -> Unit)? = { CustomCardViewFooter() },
) {
    Box(modifier = modifier) {
        CustomCard(
            modifier = Modifier.wrapContentSize(),
            style = customCardStyle,
            decorated = if (style is CustomCardViewStyle.Decorate) style.decoration else null,
        ) {
            when (style) {
                is CustomCardViewStyle.Decorate -> {
                    Column {
                        contentHeader?.invoke()
                        contentCenter?.invoke()
                        contentFooter?.invoke()
                    }
                }

                is CustomCardViewStyle.VerticalImage -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        contentHeader?.invoke()
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        contentCenter?.invoke()
                        contentFooter?.invoke()
                    }
                }

                is CustomCardViewStyle.HorizontalImage -> {
                    /*
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            modifier = Modifier.size(120.dp),
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )

                        Column {
                            contentHeader?.invoke()
                            contentCenter?.invoke()
                            contentFooter?.invoke()
                        }
                    }
                    */

                    ConstraintLayout(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val (headerRef, centerRef, footerRef, imageRef) = createRefs()
                        val guidelineStartContent = createGuidelineFromStart(120.dp)
                        Image(
                            modifier = Modifier.constrainAs(imageRef) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(guidelineStartContent)
                                width = Dimension.fillToConstraints
                                height = Dimension.fillToConstraints
                            },
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )

                        contentHeader?.let {
                            Box(
                                modifier = Modifier.constrainAs(headerRef) {
                                    top.linkTo(parent.top)
                                    start.linkTo(imageRef.end)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                            ) {
                                contentHeader.invoke()
                            }
                        }

                        contentCenter?.let {
                            Box(
                                modifier = Modifier.constrainAs(centerRef) {
                                    top.linkTo(headerRef.bottom)
                                    start.linkTo(imageRef.end)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                            ) {
                                contentCenter.invoke()
                            }
                        }

                        contentFooter?.let {
                            Box(
                                modifier = Modifier.constrainAs(footerRef) {
                                    top.linkTo(centerRef.bottom)
                                    start.linkTo(imageRef.end)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                            ) {
                                contentFooter.invoke()
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun CustomCardViewPreview1() {
    val allCasesStyles = listOf(
        CustomCardViewStyle.Decorate(),
        CustomCardViewStyle.VerticalImage(),
        CustomCardViewStyle.HorizontalImage(),
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = allCasesStyles) { style ->
            CustomCardView(
                style = style,
                customCardStyle = CustomCardStyle.Base(
                    cardRadius = CustomCardCornerRadius(
                        radius = 16.dp,
                        topStart = true,
                        topEnd = true,
                        bottomStart = true,
                        bottomEnd = true
                    )
                ),
                contentHeader = {
                    CustomCardView.Header.description(
                        title = "Lorem ipsum dolor sit amet",
                        icon = rememberVectorPainter(image = Icons.Filled.CheckCircle),
                        action = CustomActionStyle.Icon(
                            actionIcon = rememberVectorPainter(image = Icons.Filled.MoreVert),
                            action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
                        ),
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
                                "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula. "
                    )
                },
                contentCenter = {
                    CustomCardView.Center.simple(
                        title = "Lorem ipsum dolor sit amet, Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo.  In consectetur nec nunc nec tristique",
                        pillList = textPillList,
                        overLine = "Lorem ipsum dolor sit amet",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
                                "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula.",
                        monetaryValue = "R$ 100,70",
                        action = CustomActionStyle.Icon(
                            actionIcon = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowForward),
                            action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
                        ),
                        actionAlignment = Alignment.CenterVertically
                    )
                },
                contentFooter = {
                    CustomCardView.Footer.simple(
                        caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
                                "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula.",
                        action = CustomActionStyle.Text(
                            actionText = "text",
                            style = TextStyle(
                                color = Color.Red,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            ),
                            action = {}
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }

}

@Composable
fun CustomCardView.Header.title(
    title: String,
    icon: Painter? = null,
    action: CustomActionStyle? = null,
): CustomCardView.Header {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        icon?.let {
            CustomIcons(
                size = CustomIconsSize.Large,
                style = CustomIconStyle.Custom(
                    shapedColor = Color.Transparent,
                    iconeColor = Color.DarkGray,
                    showCustomShape = false
                ),
                icon = icon
            )

            Spacer(modifier = Modifier.size(8.dp))
        }

        Text(
            modifier = Modifier.weight(1f, fill = true),
            text = title,
            color = Color.DarkGray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 18.sp
            )
        )

        action?.let {
            Spacer(modifier = Modifier.size(8.dp))

            when (action) {
                is CustomActionStyle.Icon -> {
                    CustomAction(
                        style = CustomActionStyle.Icon(
                            actionIcon = action.actionIcon
                        ) {
                            action.action.invoke()
                        }
                    )
                }

                is CustomActionStyle.Text -> {
                    CustomAction(
                        style = CustomActionStyle.Text(
                            actionText = action.actionText,
                            style = action.style
                        ) {
                            action.action.invoke()
                        }
                    )
                }
            }
        }
    }
    return this
}

@Preview(showBackground = true)
@Composable
fun CustomCardViewHeaderSimplePreview() {
    CustomCardView.Header.title(
        title = "Lorem ipsum dolor sit amet",
        icon = rememberVectorPainter(image = Icons.Filled.CheckCircle),
        action = CustomActionStyle.Icon(
            actionIcon = rememberVectorPainter(image = Icons.Filled.MoreVert),
            action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
        )
    )
}

@Composable
fun CustomCardView.Header.description(
    title: String,
    description: String,
    icon: Painter? = null,
    action: CustomActionStyle? = null
): CustomCardView.Header {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            icon?.let {
                CustomIcons(
                    size = CustomIconsSize.Large,
                    style = CustomIconStyle.Custom(
                        shapedColor = Color.Transparent,
                        iconeColor = Color.DarkGray,
                        showCustomShape = false
                    ),
                    icon = icon
                )

                Spacer(modifier = Modifier.size(8.dp))
            }

            Text(
                modifier = Modifier.weight(1f, fill = true),
                text = title,
                color = Color.DarkGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )

            action?.let {
                Spacer(modifier = Modifier.size(8.dp))

                when (action) {
                    is CustomActionStyle.Icon -> {
                        CustomAction(
                            style = CustomActionStyle.Icon(
                                actionIcon = action.actionIcon
                            ) {
                                action.action.invoke()
                            }
                        )
                    }

                    is CustomActionStyle.Text -> {
                        CustomAction(
                            style = CustomActionStyle.Text(
                                actionText = action.actionText,
                                style = action.style
                            ) {
                                action.action.invoke()
                            }
                        )
                    }
                }
            }

        }

        description.let {
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
                text = description,
                color = Color.Gray,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }

    }

    return this
}

@Preview(showBackground = true)
@Composable
fun CustomCardViewHeaderDescriptionPreview() {
    CustomCardView.Header.description(
        title = "Lorem ipsum dolor sit amet, Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo.  In consectetur nec nunc nec tristique",
        icon = rememberVectorPainter(image = Icons.Filled.CheckCircle),
        action = CustomActionStyle.Icon(
            actionIcon = rememberVectorPainter(image = Icons.Filled.MoreVert),
            action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
        ),
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
                "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula. " +
                "Nunc pretium sollicitudin consequat. Duis in ante mattis, rutrum enim ac, " +
                "commodo sem. Nunc et justo a lacus posuere posuere vitae in enim. Proin condimentum fermentum augue," +
                " et pretium ante dictum et. Morbi tincidunt nibh quis nisi feugiat, a consectetur dolor venenatis. " +
                "Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo. " +
                "In consectetur nec nunc nec tristique."
    )
}

val textPillList = listOf(
    CustomTextPillModel(
        text = "Texto 1",
        isFilled = true,
        style = CustomTextPillStyle.Neutral()
    ),
    CustomTextPillModel(
        text = "Texto 2",
        isFilled = true,
        style = CustomTextPillStyle.Neutral()
    ),
    CustomTextPillModel(
        text = "Texto 3",
        isFilled = true,
        style = CustomTextPillStyle.Neutral()
    ),
    CustomTextPillModel(
        text = "Texto 4",
        isFilled = true,
        style = CustomTextPillStyle.Neutral()
    ),
    CustomTextPillModel(
        text = "Texto5 ",
        isFilled = true,
        style = CustomTextPillStyle.Neutral()
    )
)

@Composable
fun CustomCardView.Center.simple(
    title: String,
    pillList: List<CustomTextPillModel>? = emptyList(),
    overLine: String? = null,
    description: String? = null,
    monetaryValue: String? = null,
    action: CustomActionStyle? = null,
    actionAlignment: Alignment.Vertical? = null
): CustomCardView.Center {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            pillList?.let {
                LazyRow(
                    modifier = Modifier
                ) {
                    items(pillList) { pill ->
                        CustomTextPill(
                            text = pill.text,
                            isFilled = pill.isFilled,
                            style = pill.style
                        )
                    }
                }
            }

            overLine?.let {
                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = overLine.uppercase(Locale.getDefault()),
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.size(8.dp))
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                color = Color.DarkGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            description?.let {
                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = description,
                    color = Color.Gray,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }

            monetaryValue?.let {
                Spacer(modifier = Modifier.size(8.dp))

                CustomTextValues(
                    value = monetaryValue,
                    style = CustomTextValuesStyle.Dark,
                    showValue = true
                )
            }
        }

        action?.let {
            Spacer(modifier = Modifier.size(8.dp))

            Box(
                modifier = Modifier
                    .align(actionAlignment ?: Alignment.CenterVertically)
            ) {
                when (action) {
                    is CustomActionStyle.Icon -> {
                        CustomAction(
                            style = CustomActionStyle.Icon(
                                actionIcon = action.actionIcon
                            ) {
                                action.action.invoke()
                            }
                        )
                    }

                    is CustomActionStyle.Text -> {
                        CustomAction(
                            style = CustomActionStyle.Text(
                                actionText = action.actionText,
                                style = action.style
                            ) {
                                action.action.invoke()
                            }
                        )
                    }
                }
            }
        }
    }

    return this
}

@Preview(showBackground = true)
@Composable
fun CustomCardViewContentPreview() {
    CustomCardView.Center.simple(
        title = "Lorem ipsum dolor sit amet, Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo.  In consectetur nec nunc nec tristique",
        pillList = textPillList,
        overLine = "Lorem ipsum dolor sit amet",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
                "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula.",
        monetaryValue = "R$ 100,70",
        action = CustomActionStyle.Icon(
            actionIcon = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowForward),
            action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
        ),
        actionAlignment = Alignment.CenterVertically
    )
}

@Composable
fun CustomCardView.Footer.simple(
    caption: String,
    action: CustomActionStyle.Text? = null
): CustomCardView.Footer {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = caption,
            color = Color.Gray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp
            )
        )

        action?.let {
            Spacer(modifier = Modifier.size(8.dp))

            CustomAction(
                style = CustomActionStyle.Text(
                    actionText = action.actionText,
                    style = action.style
                ) {
                    action.action.invoke()
                }
            )
        }

    }

    return this
}

@Preview(showBackground = true)
@Composable
fun CustomCardViewFooterPreview() {
    CustomCardView.Footer.simple(
        caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
                "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula.",
        action = CustomActionStyle.Text(
            actionText = "text",
            style = TextStyle(
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ),
            action = {}
        )
    )
}
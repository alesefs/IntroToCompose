package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

enum class CustomCardViewStyle(val showDecorate: Boolean) {
    Decorate(true),
    VerticalImage(false),
    HorizontalImage(false)
}

object Colors {
    val Teal = Color(red = 0, green = 220, blue = 220)
    val TealUlong = Color(0xFF00DCDC)
    val Petroleum = Color(red = 0, green = 80, blue = 80)
    val PetroleumUlong = Color(0xFF005050)
}

@Composable
fun CustomCardView(
    style: CustomCardViewStyle = CustomCardViewStyle.Decorate,
    customCardStyle: CustomCardStyle = CustomCardStyle.Base(
        cardRadius = CustomCardCornerRadius(
            radius = 16.dp,
            topStart = true,
            topEnd = true,
            bottomStart = true,
            bottomEnd = true
        )
    ),
    decoration: CustomCardDecoration? = CustomCardDecoration(12.dp, Color.Blue)
) {
    Box {
        CustomCard(
            modifier = Modifier.wrapContentSize(),
            style = customCardStyle,
            decorated = if (style.showDecorate) decoration else null,
        ) {
            when(style) {
                CustomCardViewStyle.Decorate -> {
                    Column {
                        CustomCardViewHeader()
                        CustomCardViewContent()
                        CustomCardViewFooter()
                    }
                }
                CustomCardViewStyle.VerticalImage -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        CustomCardViewHeader()
                        Image(
                            modifier = Modifier.size(200.dp),
                            imageVector = Icons.Filled.AccountCircle, contentDescription = null
                        )
                        CustomCardViewContent()
                        CustomCardViewFooter()
                    }
                }
                CustomCardViewStyle.HorizontalImage -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(120.dp),
                            imageVector = Icons.Filled.AccountCircle, contentDescription = null
                        )
                        Column {
                            CustomCardViewHeader()
                            CustomCardViewContent()
                            CustomCardViewFooter()
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
        CustomCardViewStyle.Decorate,
        CustomCardViewStyle.VerticalImage,
        CustomCardViewStyle.HorizontalImage,
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = allCasesStyles) { style ->
            CustomCardView(
                style = style,
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }

}


@Composable
fun CustomCardViewHeader(
    icon: Painter? = rememberVectorPainter(image = Icons.Filled.CheckCircle),
    title: String = "Lorem ipsum dolor sit amet, Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo.  In consectetur nec nunc nec tristique",
    action: CustomActionStyle? = CustomActionStyle.Icon(
        actionIcon = rememberVectorPainter(image = Icons.Filled.MoreVert),
        action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
    ),
    description: String? = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
            "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula. " +
            "Nunc pretium sollicitudin consequat. Duis in ante mattis, rutrum enim ac, " +
            "commodo sem. Nunc et justo a lacus posuere posuere vitae in enim. Proin condimentum fermentum augue," +
            " et pretium ante dictum et. Morbi tincidunt nibh quis nisi feugiat, a consectetur dolor venenatis. " +
            "Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo. " +
            "In consectetur nec nunc nec tristique."
) {
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
            horizontalArrangement = Arrangement.Start
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
                modifier = Modifier.weight(1f, fill = false),
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

        description?.let {
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
}

@Preview(showBackground = true)
@Composable
fun CustomCardViewHeaderPreview() {
    CustomCardViewHeader()
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
fun CustomCardViewContent(
    pillList: List<CustomTextPillModel>? = textPillList,
    overLine: String? = "Lorem ipsum dolor sit amet",
    title: String = "Lorem ipsum dolor sit amet, Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo.  In consectetur nec nunc nec tristique",
    description: String? = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
            "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula. " +
            "Nunc pretium sollicitudin consequat. Duis in ante mattis, rutrum enim ac, " +
            "commodo sem. Nunc et justo a lacus posuere posuere vitae in enim. Proin condimentum fermentum augue," +
            " et pretium ante dictum et. Morbi tincidunt nibh quis nisi feugiat, a consectetur dolor venenatis. " +
            "Nullam lorem eros, pretium eu dui sit amet, elementum sagittis justo. " +
            "In consectetur nec nunc nec tristique.",
    monetaryValue: String? = "R$ 100,70",
    action: CustomActionStyle? = CustomActionStyle.Icon(
        actionIcon = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowForward),
        action = { Log.d("ALELOG", "CustomCardViewHeader: click!") }
    ),
    actionAlignment: Alignment.Vertical = Alignment.CenterVertically
) {
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
            //Pill
            pillList?.let {
                LazyRow(
                    modifier = Modifier
                ) {
                    /*items(5) {
                        CustomTextPill(
                            text = "texto $it",
                            isFilled = true,
                            style = CustomTextPillStyle.Neutral()
                        )
                    }*/

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

                Text(//TODO: REFAZER MONETARY PARA STRING
                    modifier = Modifier.fillMaxWidth(),
                    text = monetaryValue,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        action?.let {
            Spacer(modifier = Modifier.size(8.dp))
            
            Box(modifier = Modifier.align(actionAlignment)) {
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
}

@Preview(showBackground = true)
@Composable
fun CustomCardViewContentPreview() {
    CustomCardViewContent()
}

@Composable
fun CustomCardViewFooter(
    caption: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Suspendisse in neque maximus, tempus lectus in, maximus odio. " +
            "Etiam blandit diam at metus semper rutrum. Integer cursus mauris vel neque elementum vehicula.",
    action: CustomActionStyle.Text? = CustomActionStyle.Text(
        actionText = "text",
        style = TextStyle(color = Color.Red, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
        action = {}
    )
) {
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

@Preview(showBackground = true)
@Composable
fun CustomCardViewFooterPreview() {
    CustomCardViewFooter()
}
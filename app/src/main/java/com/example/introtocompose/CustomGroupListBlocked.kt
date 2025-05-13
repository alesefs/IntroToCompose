package com.example.introtocompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

data class CustomGroupListModel(
    val alignSupportVisual: AlignSupportVisual = AlignSupportVisual.Top,
    val contentSupportVisual: @Composable (() -> CustomCardItemList.SupportVisual)? = null,
    val contentCentral: @Composable (() -> CustomCardItemList.Central),
    val contentAction: @Composable (() -> CustomCardItemList.Action)? = null,
    val subContent: @Composable (() -> Unit)? = null,
    val action: (() -> Unit)? = null
)

sealed class CustomGroupListStyled {
    data object Standard : CustomGroupListStyled()

    data object Gap : CustomGroupListStyled()

    data object Blocked : CustomGroupListStyled()
}

@Composable
fun CustomGroupList(
    modifier: Modifier = Modifier,
    listStyle: CustomGroupListStyled = CustomGroupListStyled.Standard,
    listGroup: List<CustomGroupListModel>
) {
    Box(modifier = modifier) {
        when(listStyle) {
            CustomGroupListStyled.Blocked -> CustomGroupListBlocked(listGroup)
            CustomGroupListStyled.Gap -> CustomGroupListGap(listGroup)
            CustomGroupListStyled.Standard -> CustomGroupListStandard(listGroup)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomGroupListPreview() {
    IntroToComposeTheme {
        CustomGroupList(
            listStyle = CustomGroupListStyled.Gap,
            listGroup = listOf(
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                )
            )
        )
    }
}

@Composable
fun CustomGroupListBlocked(
    listGroup: List<CustomGroupListModel>
) {
    CustomCard(
        style = CustomCardStyle.Stroke(
            backgroundColor = Color.Yellow,
            cardRadius = CustomCardCornerRadius(
                radius = 16.dp,
                topStart = true,
                topEnd = true,
                bottomStart = true,
                bottomEnd = true
            ),
            strokeStyle = CustomCardStroke(2.dp, Color.Blue)
        ),
        modifier = Modifier.fillMaxWidth().wrapContentHeight(unbounded = true),
    ) {
        Column (modifier = Modifier.padding(horizontal = 16.dp)) {
            for (item in listGroup) {
                CustomCardItemList(
                    alignSupportVisual = item.alignSupportVisual,
                    contentSupportVisual = item.contentSupportVisual,
                    contentCentral = item.contentCentral,
                    contentAction = item.contentAction,
                    subContent = item.subContent,
                    action = item.action
                )

                if (item != listGroup.lastOrNull()) {
                    HorizontalDivider(
                        modifier = Modifier.height(2.dp),
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomGroupListBlockedPreview() {
    IntroToComposeTheme {
        CustomGroupListBlocked(
            listGroup = listOf(
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                )
            )
        )
    }
}

@Composable
fun CustomGroupListGap(
    listGroup: List<CustomGroupListModel>
) {
    Column (modifier = Modifier) {
        for (item in listGroup) {
            CustomCard(
                style = CustomCardStyle.Stroke(
                    backgroundColor = Color.Yellow,
                    cardRadius = CustomCardCornerRadius(
                        radius = 16.dp,
                        topStart = true,
                        topEnd = true,
                        bottomStart = true,
                        bottomEnd = true
                    ),
                    strokeStyle = CustomCardStroke(2.dp, Color.Blue)
                ),
                modifier = Modifier.fillMaxWidth().wrapContentHeight(unbounded = true),
            ) {
                CustomCardItemList(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    alignSupportVisual = item.alignSupportVisual,
                    contentSupportVisual = item.contentSupportVisual,
                    contentCentral = item.contentCentral,
                    contentAction = item.contentAction,
                    subContent = item.subContent,
                    action = item.action
                )
            }

            if (item != listGroup.lastOrNull()) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomGroupListGapPreview() {
    IntroToComposeTheme {
        CustomGroupListGap(
            listGroup = listOf(
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                )
            )
        )
    }
}

@Composable
fun CustomGroupListStandard(
    listGroup: List<CustomGroupListModel>
) {
    CustomCard(
        style = CustomCardStyle.Base(
            cardRadius = CustomCardCornerRadius(
                radius = 0.dp,
                topStart = true,
                topEnd = true,
                bottomStart = true,
                bottomEnd = true
            )
        ),
        modifier = Modifier.fillMaxWidth().wrapContentHeight(unbounded = true),
    ) {
        Column (modifier = Modifier) {
            for (item in listGroup) {
                CustomCardItemList(
                    alignSupportVisual = item.alignSupportVisual,
                    contentSupportVisual = item.contentSupportVisual,
                    contentCentral = item.contentCentral,
                    contentAction = item.contentAction,
                    subContent = item.subContent,
                    action = item.action
                )

                if (item != listGroup.lastOrNull()) {
                    HorizontalDivider(
                        modifier = Modifier.height(2.dp),
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomGroupListStandardPreview() {
    IntroToComposeTheme {
        CustomGroupListStandard(
            listGroup = listOf(
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                ),
                CustomGroupListModel(
                    alignSupportVisual = AlignSupportVisual.Top,
                    contentSupportVisual = { CustomCardItemList.SupportVisual.imageSmall() },
                    contentCentral = { CustomCardItemList.Central.texts() },
                    contentAction = { CustomCardItemList.Action.navigation() }
                )
            )
        )
    }
}
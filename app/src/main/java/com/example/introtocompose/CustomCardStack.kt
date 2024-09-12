package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import com.example.introtocompose.ui.theme.IntroToComposeTheme

data class CustomCardMicroModel(
    val index: Int,
    val color: Color,
    val text: String
)

val cardList2: MutableList<CustomCardMicroModel> = mockCustomCardMicroModel()

@Composable
fun CustomCardStack(
    cardList: MutableList<CustomCardMicroModel> = mockCustomCardMicroModel()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val cardListRevert: MutableList<CustomCardMicroModel> =
            cardList.sortedByDescending { it.index }.toMutableList()
//        Log.d("ALELOG", "cardListRevert: $cardListRevert")
//        Log.d("ALELOG", "cardList: $cardList")

//        for (item in cardListRevert) {
//            CustomMicroCard(
//                modifier = Modifier.clickable {
//                    removeItem(cardListRevert, item.index)
//                },
//                index = item.index,
//                color = item.color,
//                text = item.text
//            )
//        }
        var enabled by remember { mutableStateOf(false) }

        cardListRevert.forEachIndexed { index, item ->
            CustomMicroCard(
                index = item.index,
                color = item.color,
                text = item.text,
                cardList = cardListRevert,
                modifier = Modifier.clickable {
                    enabled = true
                    removeItem(cardList, index)
                }
            )

            if (enabled) {
                ForEached(cardList = cardList)
                enabled = false
            }
        }
    }
}

fun mockCustomCardMicroModel() = mutableListOf(
    CustomCardMicroModel(
        index = 0,
        color = Color.Green,
        text = "item 0"
    ),
    CustomCardMicroModel(
        index = 1,
        color = Color.Yellow,
        text = "item 1"
    ),
    CustomCardMicroModel(
        index = 2,
        color = Color.Magenta,
        text = "item 1"
    ),
    CustomCardMicroModel(
        index = 3,
        color = Color.Blue,
        text = "item 3"
    ),
    CustomCardMicroModel(
        index = 4,
        color = Color.Red,
        text = "item 4"
    ),
)

@Composable
fun CustomMicroCard(
    index: Int,
    color: Color,
    text: String,
    cardList: MutableList<CustomCardMicroModel>,
    modifier: Modifier = Modifier
) {
//    val numOfItems = 3

//    if (index < numOfItems) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp + (index.times(8.dp))),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(index.toFloat())
                    .padding(horizontal = 8.dp * index)
                    .height(64.dp)
                    .offset(y = 8.dp * index)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = color)

            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = text
                )
            }
//        }
    }
}

fun removeItem(cardList: MutableList<CustomCardMicroModel>, index: Int) {
    cardList.removeAt(index)
    Log.d("ALELOG", "index: $index")
    Log.d("ALELOG", "cardList: $cardList")
}

@Composable
fun ForEached(cardList: MutableList<CustomCardMicroModel>) {
    cardList.forEachIndexed { index, item ->
        CustomMicroCard(
            index = item.index,
            color = item.color,
            text = item.text,
            cardList = cardList
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CustomCardStackPreview() {
    IntroToComposeTheme {
        CustomCardStack()
    }
}
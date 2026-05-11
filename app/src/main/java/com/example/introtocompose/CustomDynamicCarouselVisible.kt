package com.example.introtocompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*@Composable
fun DynamicCarousel(
    items: List<String>,
    onVisibleItemsChanged: (List<Int>) -> Unit
) {

    val scrollState = rememberScrollState()

    var parentWidth by remember {
        mutableStateOf(0)
    }

    var parentBounds by remember {
        mutableStateOf<Rect?>(null)
    }

    // guarda posição dos itens
    val itemPositions = remember {
        mutableStateMapOf<Int, Pair<Float, Float>>()
    }

    val visibleItems = remember {
        mutableStateMapOf<Int, Boolean>()
    }

    fun notifyVisibility() {
        val visibles = visibleItems
            .filterValues { it }
            .keys
            .sorted()

        onVisibleItemsChanged(visibles)
    }

    fun dispatchVisibleItems() {
        onVisibleItemsChanged(
            visibleItems
                .filterValues { it }
                .keys
                .sorted()
        )
    }

    val itemBounds = remember {
        mutableStateMapOf<Int, Rect>()
    }

    var lastVisible by remember {
        mutableStateOf<List<Int>>(emptyList())
    }

    // recalcula sempre que scroll mudar
    LaunchedEffect(scrollState.value, parentWidth, itemBounds.size) {

//        val visibleLeft = scrollState.value.toFloat()
//        val visibleRight = visibleLeft + parentWidth
//
//        val visibleItems = itemPositions
//            .filter { (_, bounds) ->
//
//                val itemLeft = bounds.first
//                val itemRight = bounds.second
//
//                itemRight >= visibleLeft &&
//                        itemLeft <= visibleRight
//            }
//            .keys
//            .sorted()
//
//        if (visibleItems != lastVisible) {
//            lastVisible = visibleItems
//            onVisibleItemsChanged(visibleItems)
//        }

        val current = itemBounds
            .filter { (_, rect) ->

                val isVisible =
                    rect.right >= 0f &&
                            rect.left <= parentWidth

                isVisible
            }
            .keys
            .sorted()

        if (current != lastVisible) {
            lastVisible = current
            onVisibleItemsChanged(current)
        }
    }

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .onSizeChanged {
                parentWidth = it.width
            }
//            .onGloballyPositioned { coordinates ->
//                parentBounds = coordinates.boundsInRoot()
//            }
    ) {

        items.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)
                    .background(Color.Gray, RoundedCornerShape(16.dp))
                    .onGloballyPositioned { coordinates ->

//                        val itemLeft = coordinates.positionInParent().x
//                        val itemRight = itemLeft + coordinates.size.width
//
//                        val visibleLeft = scrollState.value.toFloat()
//                        val visibleRight = visibleLeft + parentWidth
//
//                        val isVisible =
//                            itemRight > visibleLeft &&
//                                    itemLeft < visibleRight
//
//                        if (visibleItems[index] != isVisible) {
//                            visibleItems[index] = isVisible
//                            dispatchVisibleItems()
//                        }

//                        val parent = parentBounds ?: return@onGloballyPositioned
//
//                        val itemBounds = coordinates.boundsInRoot()
//
//                        val isVisible =
//                            itemBounds.right >= parent.left &&
//                                    itemBounds.left <= parent.right
//
//                        val oldValue = visibleItems[index]
//
//                        if (oldValue != isVisible) {
//                            visibleItems[index] = isVisible
//                            notifyVisibility()
//                        }

//                        val left = coordinates.positionInParent().x
//                        val right = left + coordinates.size.width
//
//                        itemPositions[index] = left to right

                        itemBounds[index] = coordinates.boundsInParent()

                    },
                contentAlignment = Alignment.Center
            ) {
                Text(item)
            }
        }
    }
}*/

/*@Composable
fun DynamicCarousel(
    items: List<String>,
    onVisibleItemsChanged: (List<Int>) -> Unit
) {

    val scrollState = rememberScrollState()

    var viewportWidth by remember {
        mutableStateOf(0)
    }

    // posição absoluta dos itens dentro da Row
    val itemPositions = remember {
        mutableStateMapOf<Int, IntRange>()
    }

    var lastVisible by remember {
        mutableStateOf<List<Int>>(emptyList())
    }

    // recalcula no scroll
    LaunchedEffect(scrollState.value, viewportWidth, itemPositions.size) {

        val visibleStart = scrollState.value
        val visibleEnd = visibleStart + viewportWidth

        val current = itemPositions
            .filter { (_, range) ->

                range.last > visibleStart &&
                        range.first < visibleEnd
            }
            .keys
            .sorted()

        if (current != lastVisible) {
            lastVisible = current

            println("VISIVEIS -> $current")

            onVisibleItemsChanged(current)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .onSizeChanged {
                viewportWidth = it.width
            }
    ) {

        items.forEachIndexed { index, item ->

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)
                    .background(Color.Gray, RoundedCornerShape(16.dp))
                    .onGloballyPositioned { coordinates ->

                        val start = coordinates.positionInParent().x.toInt()
                        val end = start + coordinates.size.width

                        itemPositions[index] = start..end
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(item)
            }
        }
    }
}*/

@Composable
fun DynamicCarousel(
    items: List<String>,
    onVisibleItemsChanged: (List<Int>) -> Unit
) {

    val state = rememberLazyListState()

    var maxHeight by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(state) {

        snapshotFlow {
            state.layoutInfo.visibleItemsInfo
                .map { it.index }
        }.collect { visibleItems ->

            println("VISIVEIS -> $visibleItems")

            onVisibleItemsChanged(visibleItems)
        }
    }

    LazyRow(
        state = state,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        itemsIndexed(items) { index, item ->

            Box(
                modifier = Modifier
                    .width((80..180).random().dp) // largura dinâmica
                    .then(
                        if (maxHeight > 0)
                            Modifier.height(with(LocalDensity.current) {
                                maxHeight.toDp()
                            })
                        else Modifier
                    )
                    .background(Color.Gray)
                    .onSizeChanged {

                        if (it.height > maxHeight) {
                            maxHeight = it.height
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    if (index == 0) {
                        Text(item)
                    }
                    if (index % 2 == 0) {
                        Text(item)
                    }
                    Text(item)
                    Text(item)
                    Text(item)
                }
            }
        }
    }
}

@Preview
@Composable
fun DynamicCarouselPreview() {
    DynamicCarousel(
        items = List(20) { "Item $it" },
        onVisibleItemsChanged = { visibleItems ->
            println("Visible items: $visibleItems")

            val fourthVisible = 3 in visibleItems

            Log.d("VISIBLE", "4º item visível? $fourthVisible")
        }
    )
}
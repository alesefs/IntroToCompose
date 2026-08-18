package com.example.introtocompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BarChartItem(
    val label: String,
    val value: Float
)

enum class YAxisPosition {
    Start,
    End
}

private fun calculateBarWidth(
    itemCount: Int,
    availableWidth: Dp,
    barSpacing: Dp
): Dp {

    if (itemCount <= 0) {
        return 32.dp
    }

    // 1 barra = máximo 96.dp
    if (itemCount == 1) {
        return minOf(
            96.dp,
            availableWidth
        )
    }

    // 7 ou mais = mínimo 32.dp
    if (itemCount >= 7) {
        return 32.dp
    }

    /*
     * Interpolação:
     *
     * 1 -> 96
     * 2 -> 85.33
     * 3 -> 74.66
     * 4 -> 64
     * 5 -> 53.33
     * 6 -> 42.66
     * 7 -> 32
     */

    val maxWidth = 96f
    val minWidth = 32f

    val fraction = (itemCount - 1) / 6f

    val calculated =
        maxWidth + (minWidth - maxWidth) * fraction

    /*
     * Também limita pela largura disponível.
     */
    val maxAvailableWidth =
        (availableWidth - barSpacing * (itemCount - 1)) / itemCount

    return minOf(
        calculated.dp,
        maxAvailableWidth
    )
}

@Composable
fun BarChart(
    data: List<BarChartItem>,
    modifier: Modifier = Modifier,
    scrollable: Boolean = false,
    barWidth: Dp = 32.dp,
    barSpacing: Dp = 16.dp,
    chartHeight: Dp = 300.dp,
    showGrid: Boolean = true,
    showValues: Boolean = true,
    showXAxis: Boolean = true,
    showYAxis: Boolean = true,
    yAxisPosition: YAxisPosition = YAxisPosition.Start,
    yAxisSteps: Int = 5,
    valueFormatter: (Float) -> String = {
        if (it % 1f == 0f) {
            it.toInt().toString()
        } else {
            "%.1f".format(it)
        }
    }
) {
    if (data.isEmpty()) {
        return
    }

    val density = LocalDensity.current

    val maxWidth = 96.dp
    val minWidth = 32.dp
    val fraction = (data.size - 1) / 6f

    val calculatedBarWidth =
        if (scrollable) {
            barWidth
        } else {
//            calculateBarWidth(
//                itemCount = data.size,
//                availableWidth = maxWidth,
//                barSpacing = barSpacing
//            )

            maxWidth +
                    (minWidth - maxWidth) *
                    fraction
//            96.dp * (data.size + 1 * 7)

        }

    //https://www.instagram.com/familia_han?igsi=ZGZwzxu3OHRicDEw

    val barWidthPx = with(density) {
        calculatedBarWidth.toPx()
    }

    val barSpacingPx = with(density) {
        barSpacing.toPx()
    }

    val yAxisWidth = with(density) {
        52.dp.toPx()
    }

    val xAxisHeight = with(density) {
        42.dp.toPx()
    }

    val topPadding = with(density) {
        28.dp.toPx()
    }

    val bottomPadding = with(density) {
        8.dp.toPx()
    }

    val chartHeightPx = with(density) {
        chartHeight.toPx()
    }

    val minDataValue = data.minOf { it.value }
    val maxDataValue = data.maxOf { it.value }

    // Sempre inclui o zero no gráfico.
    var minValue = minOf(0f, minDataValue)
    var maxValue = maxOf(0f, maxDataValue)

    // Caso todos os valores sejam zero.
    if (minValue == maxValue) {
        minValue = -1f
        maxValue = 1f
    }

    val valueRange = maxValue - minValue

    val scrollState = rememberScrollState()

    var selectedIndex by remember {
        mutableStateOf<Int?>(null)
    }

    Column(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(chartHeight)
        ) {

            // ---------------------------------------------------------
            // EIXO Y Start
            // ---------------------------------------------------------

            if (showYAxis && yAxisPosition == YAxisPosition.Start) {

                YAxis(
                    density,
                    yAxisWidth,
                    topPadding,
                    xAxisHeight,
                    bottomPadding,
                    valueRange,
                    yAxisSteps,
                    maxValue,
                    valueFormatter
                )
            }

            // ---------------------------------------------------------
            // GRÁFICO
            // ---------------------------------------------------------

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .then(
                        if (scrollable) {
                            Modifier.horizontalScroll(scrollState)
                        } else {
                            Modifier
                        }
                    )
            ) {

                val minChartWidth = with(density) {
                    1.dp.toPx()
                }

                val calculatedWidth =
                    maxOf(
                        minChartWidth,
                        data.size * barWidthPx +
                                (data.size - 1) * barSpacingPx
                    )

                Canvas(
                    modifier = Modifier
                        .then(
                            if (scrollable) {
                                Modifier.width(
                                    with(density) {
                                        calculatedWidth.toDp()
                                    }
                                )
                            } else {
                                Modifier.fillMaxWidth()
                            }
                        )
                        .fillMaxHeight()
                        .pointerInput(data) {

                            detectTapGestures { offset ->

                                val graphLeft = 0f

                                val indexFloat =
                                    (offset.x - graphLeft) /
                                            (barWidthPx + barSpacingPx)

                                val index =
                                    indexFloat.toInt()

                                if (
                                    index in data.indices &&
                                    offset.x >=
                                    index *
                                    (barWidthPx + barSpacingPx) &&
                                    offset.x <=
                                    index *
                                    (barWidthPx + barSpacingPx) +
                                    barWidthPx
                                ) {
                                    selectedIndex = index
                                } else {
                                    selectedIndex = null
                                }
                            }
                        }
                ) {

                    val graphTop = topPadding

                    val graphBottom =
                        size.height - xAxisHeight - bottomPadding

                    val graphHeight =
                        graphBottom - graphTop

                    // -------------------------------------------------
                    // GRID
                    // -------------------------------------------------

                    if (showGrid) {

                        val gridPaint =
                            Paint().apply {
                                color =
                                    Color.LightGray.copy(
                                        alpha = 0.35f
                                    )
                                strokeWidth = 1.dp.toPx()
                            }

                        repeat(yAxisSteps + 1) { index ->

                            val value =
                                maxValue -
                                        (valueRange / yAxisSteps) *
                                        index

                            val y =
                                graphTop +
                                        ((maxValue - value) /
                                                valueRange) *
                                        graphHeight

                            drawLine(
                                color = gridPaint.color,
                                start = Offset(
                                    0f,
                                    y
                                ),
                                end = Offset(
                                    size.width,
                                    y
                                ),
                                strokeWidth =
                                    gridPaint.strokeWidth
                            )
                        }
                    }

                    // -------------------------------------------------
                    // EIXO X = ZERO
                    // -------------------------------------------------

                    val totalBarsWidth =
                        data.size * barWidthPx +
                                (data.size - 1) * barSpacingPx

                    val startX =
                        if (scrollable) {
                            0f
                        } else {
                            maxOf(
                                0f,
                                (size.width - totalBarsWidth) / 2f
                            )
                        }

                    val zeroY =
                        graphTop +
                                ((maxValue - 0f) /
                                        valueRange) *
                                graphHeight

                    drawLine(
                        color = Color.Gray,
                        start = Offset(
                            0f,
                            zeroY
                        ),
                        end = Offset(
                            size.width,
                            zeroY
                        ),
                        strokeWidth = 1.5.dp.toPx()
                    )

                    // -------------------------------------------------
                    // BARRAS
                    // -------------------------------------------------

                    data.forEachIndexed { index, item ->

//                        val x =
//                            index *
//                                    (barWidthPx + barSpacingPx)

                        val x =
                            startX +
                                    index * (barWidthPx + barSpacingPx)

                        val valueY =
                            graphTop +
                                    ((maxValue - item.value) /
                                            valueRange) *
                                    graphHeight

                        val barTop =
                            minOf(
                                zeroY,
                                valueY
                            )

                        val barBottom =
                            maxOf(
                                zeroY,
                                valueY
                            )

                        val barHeight =
                            maxOf(
                                1.dp.toPx(),
                                barBottom - barTop
                            )

                        val isSelected =
                            selectedIndex == index

                        drawRoundRect(
                            color =
                                if (isSelected) {
                                    Color.Cyan
                                } else {
                                    Color.Cyan.copy(
                                        alpha = 0.75f
                                    )
                                },

                            topLeft = Offset(
                                x,
                                barTop
                            ),

                            size = Size(
                                barWidthPx,
                                barHeight
                            ),

                            cornerRadius =
                                CornerRadius(
                                    4.dp.toPx(),
                                    4.dp.toPx()
                                )
                        )

                        // -------------------------------------------------
                        // VALOR DA BARRA
                        // -------------------------------------------------

                        if (showValues) {

                            val textPaint =
                                android.graphics.Paint().apply {
                                    isAntiAlias = true
                                    textSize =
                                        11.sp.toPx()

                                    color =
                                        android.graphics.Color.DKGRAY

                                    textAlign =
                                        android.graphics.Paint.Align.CENTER

                                    typeface =
                                        android.graphics.Typeface.DEFAULT_BOLD
                                }

                            val textY =
                                if (item.value >= 0f) {
                                    barTop -
                                            6.dp.toPx()
                                } else {
                                    barBottom +
                                            14.dp.toPx()
                                }

                            drawContext
                                .canvas
                                .nativeCanvas
                                .drawText(
                                    valueFormatter(item.value),
                                    x +
                                            barWidthPx / 2,
                                    textY,
                                    textPaint
                                )
                        }

                        // -------------------------------------------------
                        // TOOLTIP
                        // -------------------------------------------------

                        if (isSelected) {

                            val tooltipText =
                                "${item.label}: ${
                                    valueFormatter(item.value)
                                }"

                            val tooltipPaint =
                                android.graphics.Paint().apply {
                                    isAntiAlias = true
                                    textSize =
                                        12.sp.toPx()
                                    color =
                                        android.graphics.Color.WHITE
                                }

                            val padding =
                                8.dp.toPx()

                            val textWidth =
                                tooltipPaint.measureText(
                                    tooltipText
                                )

                            val tooltipWidth =
                                textWidth +
                                        padding * 2

                            val tooltipHeight =
                                30.dp.toPx()

                            var tooltipX =
                                x +
                                        barWidthPx / 2 -
                                        tooltipWidth / 2

                            tooltipX =
                                tooltipX.coerceIn(
                                    0f,
                                    size.width -
                                            tooltipWidth
                                )

                            val tooltipY =
                                if (item.value >= 0f) {

                                    maxOf(
                                        0f,
                                        barTop -
                                                tooltipHeight -
                                                8.dp.toPx()
                                    )

                                } else {

                                    minOf(
                                        size.height -
                                                tooltipHeight,
                                        barBottom +
                                                8.dp.toPx()
                                    )
                                }

                            drawRoundRect(
                                color =
                                    Color.Green,

                                topLeft =
                                    Offset(
                                        tooltipX,
                                        tooltipY
                                    ),

                                size =
                                    Size(
                                        tooltipWidth,
                                        tooltipHeight
                                    ),

                                cornerRadius =
                                    CornerRadius(
                                        6.dp.toPx(),
                                        6.dp.toPx()
                                    )
                            )

                            drawContext
                                .canvas
                                .nativeCanvas
                                .drawText(
                                    tooltipText,

                                    tooltipX +
                                            tooltipWidth / 2,

                                    tooltipY +
                                            tooltipHeight / 2 +
                                            4.dp.toPx(),

                                    tooltipPaint.apply {
                                        textAlign =
                                            android.graphics.Paint.Align.CENTER
                                    }
                                )
                        }
                    }

                    // -------------------------------------------------
                    // LABELS X
                    // -------------------------------------------------

                    if (showXAxis) {

                        val labelPaint =
                            android.graphics.Paint().apply {
                                isAntiAlias = true

                                textSize =
                                    11.sp.toPx()

                                color =
                                    android.graphics.Color.GRAY

                                textAlign =
                                    android.graphics.Paint.Align.CENTER
                            }

                        data.forEachIndexed { index, item ->

                            /*val x =
                                index *
                                        (barWidthPx + barSpacingPx) +
                                        barWidthPx / 2*/

                            val x =
                                startX +
                                        index * (barWidthPx + barSpacingPx) +
                                        barWidthPx / 2


                            drawContext
                                .canvas
                                .nativeCanvas
                                .drawText(
                                    item.label,

                                    x,

                                    size.height -
                                            8.dp.toPx(),

                                    labelPaint
                                )
                        }
                    }
                }
            }

            if (showYAxis && yAxisPosition == YAxisPosition.End) {

                YAxis(
                    density,
                    yAxisWidth,
                    topPadding,
                    xAxisHeight,
                    bottomPadding,
                    valueRange,
                    yAxisSteps,
                    maxValue,
                    valueFormatter
                )
            }
        }
    }
}

@Composable
private fun YAxis(
    density: Density,
    yAxisWidth: Float,
    topPadding: Float,
    xAxisHeight: Float,
    bottomPadding: Float,
    valueRange: Float,
    yAxisSteps: Int,
    maxValue: Float,
    valueFormatter: (Float) -> String
) {
    Canvas(
        modifier = Modifier
            .width(with(density) { yAxisWidth.toDp() })
            .fillMaxHeight()
    ) {

        val graphTop = topPadding

        val graphBottom =
            size.height - xAxisHeight - bottomPadding

        val graphHeight =
            graphBottom - graphTop

        val step =
            valueRange / yAxisSteps

        val textPaint = android.graphics.Paint().apply {
            isAntiAlias = true
            textSize = with(density) {
                11.sp.toPx()
            }
            color = android.graphics.Color.GRAY
            textAlign =
                android.graphics.Paint.Align.RIGHT
        }

        repeat(yAxisSteps + 1) { index ->

            val value =
                maxValue - step * index

            val y =
                graphTop +
                        ((maxValue - value) / valueRange) *
                        graphHeight

            drawContext.canvas.nativeCanvas.drawText(
                valueFormatter(value),
                size.width - 6.dp.toPx(),
                y + 4.dp.toPx(),
                textPaint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BarChartPreview() {
    val data = listOf(
        BarChartItem("Jan", 120f),
        BarChartItem("Fev", 80f),
        BarChartItem("Mar", -50f),
//        BarChartItem("Abr", 150f),
//        BarChartItem("Mai", -90f),
//        BarChartItem("Jun", 200f)
    )

    BarChart(
        data = data,
        scrollable = false
    )
}

@Preview(showBackground = true)
@Composable
private fun BarChartScrollablePreview() {
    val data = listOf(
        BarChartItem("Jan", 120f),
        BarChartItem("Fev", 80f),
        BarChartItem("Mar", -50f),
        BarChartItem("Abr", 150f),
        BarChartItem("Mai", -90f),
        BarChartItem("Jun", 200f),
        BarChartItem("Jun", -200f),
        BarChartItem("Jun", 200f),
        BarChartItem("Jun", 200f),
        BarChartItem("Jun", 200f),
    )

    BarChart(
        data = data,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        scrollable = true,
        yAxisPosition = YAxisPosition.End
    )
}
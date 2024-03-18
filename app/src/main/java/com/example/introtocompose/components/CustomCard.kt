package com.example.introtocompose.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.introtocompose.R
import com.example.introtocompose.utils.BorderInt
import com.example.introtocompose.utils.BorderRadiusInt
import com.example.introtocompose.utils.BorderRadiusStyle
import com.example.introtocompose.utils.CardCustomTypes
import com.example.introtocompose.utils.ElevationInt

class CustomCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : CardView(context, attrs, defStyleAttr) {

    var style: CardCustomTypes = CardCustomTypes.Active
        set(value) {
            field = value
            refreshView(value)
        }

    var bgColor: Int = R.color.white
        set(value) {
            field = value
            refreshView(style)
        }

    var deactivateColor: Int = R.color.light_gray
        set(value) {
            field = value
            refreshView(style)
        }

    var borderSize: BorderInt = BorderInt.Medium
        set(value) {
            field = value
            refreshView(style)
        }

    var borderColor: Int = R.color.black
        set(value) {
            field = value
            refreshView(style)
        }

    var radiusStyle: BorderRadiusStyle = BorderRadiusStyle.All
        set(value) {
            field = value
            refreshView(style)
        }

    var radiusValue: BorderRadiusInt = BorderRadiusInt.Default
        set(value) {
            field = value
            refreshView(style)
        }

    var elevationValue: ElevationInt = ElevationInt.Medium
        set(value) {
            field = value
            refreshView(style)
        }

    fun customCardStyles(cardStyle: CustomCardStyles) {
        when(cardStyle) {
            is CustomCardStyles.Active -> {
                customView(
                    background = cardStyle.color,
                    cornerRadiiValues = cardStyle.shape,
                )
            }

            is CustomCardStyles.Border -> {
                customView(
                    background = cardStyle.color,
                    cornerRadiiValues = cardStyle.shape,
                    borderSize = cardStyle.borderStroke.first,
                    borderColor = cardStyle.borderStroke.second,
                )
            }

            is CustomCardStyles.Deactivate -> {
                customView(
                    background = cardStyle.color,
                    cornerRadiiValues = cardStyle.shape,
                )
            }

            is CustomCardStyles.Elevation -> {
                customView(
                    background = cardStyle.color,
                    cornerRadiiValues = cardStyle.shape,
                    elevationValue = cardStyle.elevationSize,
                )
            }
        }
    }

    init {
        if (attrs != null) {
            val attr = context.obtainStyledAttributes(
                attrs, R.styleable.CustomCard, defStyleAttr, 0
            )
            setupCustomCard(attr)
        }
    }

    private fun setupCustomCard(attr: TypedArray) {
        style = attr.getInt(R.styleable.CustomCard_style, 0).let {
            when (it) {
                0 -> CardCustomTypes.Active
                1 -> CardCustomTypes.Deactivate
                2 -> CardCustomTypes.Border
                3 -> CardCustomTypes.Elevation
                else -> CardCustomTypes.Active
            }
        }

        borderSize = attr.getInt(R.styleable.CustomCard_strokeSize, 1).let {
            when (it) {
                0 -> BorderInt.Small
                1 -> BorderInt.Medium
                2 -> BorderInt.Large
                3 -> BorderInt.XLarge
                else -> BorderInt.Medium
            }
        }

        elevationValue = attr.getInt(R.styleable.CustomCard_elevationValue, 1).let {
            when (it) {
                0 -> ElevationInt.Small
                1 -> ElevationInt.Medium
                2 -> ElevationInt.Large
                3 -> ElevationInt.XLarge
                else -> ElevationInt.Medium
            }
        }

        radiusValue = attr.getInt(R.styleable.CustomCard_cardRadius, 3).let {
            when (it) {
                0 -> BorderRadiusInt.XSmall
                1 -> BorderRadiusInt.Small
                2 -> BorderRadiusInt.Medium
                3 -> BorderRadiusInt.Default
                4 -> BorderRadiusInt.Large
                5 -> BorderRadiusInt.XLarge
                6 -> BorderRadiusInt.Round
                7 -> BorderRadiusInt.Sharp
                else -> BorderRadiusInt.Default
            }
        }

        radiusStyle = attr.getInt(R.styleable.CustomCard_borderStyle, 0).let {
            when(it) {
                0 -> BorderRadiusStyle.All
                1 -> BorderRadiusStyle.None
                2 -> BorderRadiusStyle.TopStart
                3 -> BorderRadiusStyle.TopEnd
                4 -> BorderRadiusStyle.BottomStart
                5 -> BorderRadiusStyle.BottomEnd
                6 -> BorderRadiusStyle.Top
                7 -> BorderRadiusStyle.Bottom
                8 -> BorderRadiusStyle.Start
                9 -> BorderRadiusStyle.End
                10 -> BorderRadiusStyle.TopStartBottomEnd
                11 -> BorderRadiusStyle.TopEndBottomStart
                12 -> BorderRadiusStyle.SharpTopStart
                13 -> BorderRadiusStyle.SharpTopEnd
                14 -> BorderRadiusStyle.SharpBottomStart
                15 -> BorderRadiusStyle.SharpBottomEnd
                else -> BorderRadiusStyle.All
            }
        }

        bgColor = attr.getInt(
            R.styleable.CustomCard_bgColor,
            R.color.white
        )
        deactivateColor =
            attr.getInt(
                R.styleable.CustomCard_highlightColor,
                R.color.light_gray
            )
        borderColor =
            attr.getInt(
                R.styleable.CustomCard_borderColor,
                R.color.black
            )

        attr.recycle()
    }

    private fun refreshView(type: CardCustomTypes) {
        when (type) {
            CardCustomTypes.Active -> {
                customView(
                    background = CardCustomDefaults(context).backgroundColor(
                        type = type,
                        backgroundColor = context.getColor(bgColor)//ContextCompat.getColor(context, bgColor)
                    ),
                    cornerRadiiValues = CardCustomDefaults(context).roundCornerShape(
                        cornerRadiusValue = radiusValue,
                        cornerRadiusStyle = radiusStyle
                    ),
                )
            }

            CardCustomTypes.Border -> {
                customView(
                    background = CardCustomDefaults(context).backgroundColor(
                        type = type,
                        backgroundColor = context.getColor(bgColor)//ContextCompat.getColor(context, bgColor)
                    ),
                    cornerRadiiValues = CardCustomDefaults(context).roundCornerShape(
                        cornerRadiusValue = radiusValue,
                        cornerRadiusStyle = radiusStyle
                    ),
                    borderSize = CardCustomDefaults(context).borderSize(
                        type = type,
                        size = borderSize
                    ),
                    borderColor = CardCustomDefaults(context).borderColor(
                        type = type,
                        color = context.getColor(borderColor)//ContextCompat.getColor(context, borderColor)
                    ),
                )
            }

            CardCustomTypes.Elevation -> {
                customView(
                    background = CardCustomDefaults(context).backgroundColor(
                        type = type,
                        backgroundColor = context.getColor(bgColor)//ContextCompat.getColor(context, bgColor)
                    ),
                    cornerRadiiValues = CardCustomDefaults(context).roundCornerShape(
                        cornerRadiusValue = radiusValue,
                        cornerRadiusStyle = radiusStyle
                    ),
                    elevationValue = CardCustomDefaults(context).elevationSize(
                        type = type,
                        size = elevationValue
                    )
                )
            }

            CardCustomTypes.Deactivate -> {
                customView(
                    background = CardCustomDefaults(context).backgroundColor(
                        type = type,
                        deactivateColor = context.getColor(deactivateColor)//ContextCompat.getColor(context, deactivateColor)
                    ),
                    cornerRadiiValues = CardCustomDefaults(context).roundCornerShape(
                        cornerRadiusValue = radiusValue,
                        cornerRadiusStyle = radiusStyle
                    ),
                )
            }
        }
    }

    private fun customView(
        background: Int,
        borderColor: Int? = null,
        borderSize: Int? = null,
        elevationValue: Int? = null,
        cornerRadiiValues: FloatArray? = null,
    ) {
        val card = GradientDrawable()
        card.shape = GradientDrawable.RECTANGLE
        if (cornerRadiiValues != null) card.cornerRadii = cornerRadiiValues
        card.setStroke(borderSize ?: 0, borderColor ?: background)
        card.setColor(background)
        this.background = card
        this.cardElevation = elevationValue?.toFloat() ?: 0f
        this.elevation = elevationValue?.toFloat() ?: 0f
    }

}

sealed class CustomCardStyles {
    class Active(
        bgColor: Int = R.color.white,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusInt> = Pair(
            BorderRadiusStyle.All,
            BorderRadiusInt.Default
        ),
        context: Context
    ) : CustomCardStyles() {
        val color = CardCustomDefaults(context).backgroundColor(
            type = CardCustomTypes.Active,
            backgroundColor = context.getColor(bgColor)//ContextCompat.getColor(context,  bgColor)
        )

        val shape = CardCustomDefaults(context).roundCornerShape(
            shapeStyle.first,
            shapeStyle.second
        )
    }

    class Deactivate(
        deactivateColor: Int = R.color.light_gray,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusInt> = Pair(
            BorderRadiusStyle.All,
            BorderRadiusInt.Default
        ),
        context: Context
    ) : CustomCardStyles() {
        val color = CardCustomDefaults(context).backgroundColor(
            type = CardCustomTypes.Deactivate,
            deactivateColor = context.getColor(deactivateColor)//ContextCompat.getColor(context,  deactivateColor)
        )

        val shape = CardCustomDefaults(context).roundCornerShape(
            shapeStyle.first,
            shapeStyle.second
        )
    }

    class Border(
        bgColor: Int = R.color.white,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusInt> = Pair(
            BorderRadiusStyle.All,
            BorderRadiusInt.Default
        ),
        borderStyle: Pair<BorderInt, Int> = Pair(BorderInt.Medium, R.color.black),
        context: Context
    ) : CustomCardStyles() {
        val color = CardCustomDefaults(context).backgroundColor(
            type = CardCustomTypes.Active,
            backgroundColor = context.getColor(bgColor)//ContextCompat.getColor(context,  bgColor)
        )

        val shape = CardCustomDefaults(context).roundCornerShape(
            shapeStyle.first,
            shapeStyle.second
        )

        val borderStroke = Pair(
            CardCustomDefaults(context).borderSize(
                CardCustomTypes.Border, borderStyle.first
            ),
            CardCustomDefaults(context).borderColor(
                CardCustomTypes.Border, context.getColor(borderStyle.second)//ContextCompat.getColor(context,  borderStyle.second)
            )
        )
    }

    class Elevation(
        bgColor: Int = R.color.white,
        shapeStyle: Pair<BorderRadiusStyle, BorderRadiusInt> = Pair(
            BorderRadiusStyle.All,
            BorderRadiusInt.Default
        ),
        elevationInt: ElevationInt = ElevationInt.Medium,
        context: Context
    ) : CustomCardStyles() {
        val color = CardCustomDefaults(context).backgroundColor(
            type = CardCustomTypes.Elevation,
            backgroundColor = context.getColor(bgColor)//ContextCompat.getColor(context,  bgColor)
        )

        val shape = CardCustomDefaults(context).roundCornerShape(
            shapeStyle.first,
            shapeStyle.second
        )

        val elevationSize =
            CardCustomDefaults(context).elevationSize(CardCustomTypes.Elevation, elevationInt)
    }

}

class CardCustomDefaults(private val context: Context) {
    fun backgroundColor(
        type: CardCustomTypes,
        backgroundColor: Int = context.getColor(R.color.white),//ContextCompat.getColor(context, R.color.white),
        deactivateColor: Int = context.getColor(R.color.light_gray)//ContextCompat.getColor(context, R.color.light_gray)
    ): Int {
        return when (type) {
            CardCustomTypes.Deactivate -> deactivateColor
            else -> backgroundColor
        }
    }

    fun roundCornerShape(
        cornerRadiusStyle: BorderRadiusStyle,
        cornerRadiusValue: BorderRadiusInt
    ): FloatArray {
        val valueInPixels = context.resources.getDimension(cornerRadiusValue.value)

        return when (cornerRadiusStyle) {
            BorderRadiusStyle.All -> floatArrayOf(
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels
            )

            BorderRadiusStyle.None -> floatArrayOf(
                0f, 0f,
                0f, 0f,
                0f, 0f,
                0f, 0f
            )

            BorderRadiusStyle.TopStart -> floatArrayOf(
                valueInPixels, valueInPixels,
                0f, 0f,
                0f, 0f,
                0f, 0f
            )

            BorderRadiusStyle.TopEnd -> floatArrayOf(
                0f, 0f,
                valueInPixels, valueInPixels,
                0f, 0f,
                0f, 0f
            )

            BorderRadiusStyle.BottomStart -> floatArrayOf(
                0f, 0f,
                0f, 0f,
                valueInPixels, valueInPixels,
                0f, 0f
            )

            BorderRadiusStyle.BottomEnd -> floatArrayOf(
                0f, 0f,
                0f, 0f,
                0f, 0f,
                valueInPixels, valueInPixels
            )

            BorderRadiusStyle.Top -> floatArrayOf(
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                0f, 0f,
                0f, 0f
            )

            BorderRadiusStyle.Bottom -> floatArrayOf(
                0f, 0f,
                0f, 0f,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
            )

            BorderRadiusStyle.Start -> floatArrayOf(
                valueInPixels, valueInPixels,
                0f, 0f,
                valueInPixels, valueInPixels,
                0f, 0f,
            )

            BorderRadiusStyle.End -> floatArrayOf(
                0f, 0f,
                valueInPixels, valueInPixels,
                0f, 0f,
                valueInPixels, valueInPixels,
            )

            BorderRadiusStyle.TopStartBottomEnd -> floatArrayOf(
                valueInPixels, valueInPixels,
                0f, 0f,
                0f, 0f,
                valueInPixels, valueInPixels,
            )

            BorderRadiusStyle.TopEndBottomStart -> floatArrayOf(
                0f, 0f,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                0f, 0f,
            )

            BorderRadiusStyle.SharpTopStart -> floatArrayOf(
                0f, 0f,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
            )

            BorderRadiusStyle.SharpTopEnd -> floatArrayOf(
                valueInPixels, valueInPixels,
                0f, 0f,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
            )

            BorderRadiusStyle.SharpBottomStart -> floatArrayOf(
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                0f, 0f,
                valueInPixels, valueInPixels,
            )

            BorderRadiusStyle.SharpBottomEnd -> floatArrayOf(
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                valueInPixels, valueInPixels,
                0f, 0f,
            )
        }
    }

    fun borderSize(
        type: CardCustomTypes,
        size: BorderInt
    ): Int {
        val valueInPixels = context.resources.getDimension(size.value)
        val value0 = context.resources.getDimension(R.dimen.value0)

        return when (type) {
            CardCustomTypes.Border -> valueInPixels.toInt()
            else -> value0.toInt()
        }
    }

    fun borderColor(
        type: CardCustomTypes,
        color: Int = context.getColor(R.color.black)//ContextCompat.getColor(context, R.color.black)
    ): Int {
        return when (type) {
            CardCustomTypes.Border -> color
            else -> backgroundColor(type)
        }
    }

    fun elevationSize(
        type: CardCustomTypes,
        size: ElevationInt
    ): Int {
        val valueInPixels = context.resources.getDimension(size.value)
        val value0 = context.resources.getDimension(R.dimen.value0)

        return when (type) {
            CardCustomTypes.Elevation -> valueInPixels.toInt()
            else -> value0.toInt()
        }
    }
}
package com.example.introtocompose.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.introtocompose.R

enum class CardCustomTypes {
    Active,
    Border,
    Elevation,
    Deactivate
}

enum class BorderRadiusStyle {
    All,
    None,
    TopStart,
    TopEnd,
    BottomStart,
    BottomEnd,
    Top,
    Bottom,
    Start,
    End,
    TopStartBottomEnd,
    TopEndBottomStart,
    SharpTopStart,
    SharpTopEnd,
    SharpBottomStart,
    SharpBottomEnd
}

enum class BorderRadiusValues(val value: Dp) {
    XSmall(2.dp),
    Small(4.dp),
    Medium(8.dp),
    Default(12.dp),
    Large(16.dp),
    XLarge(32.dp),
    Round(999.dp),
    Sharp(0.dp)
}

enum class BorderRadiusInt(val value: Int) {
    XSmall(R.dimen.value2),
    Small(R.dimen.value4),
    Medium(R.dimen.value8),
    Default(R.dimen.value12),
    Large(R.dimen.value16),
    XLarge(R.dimen.value32),
    Round(R.dimen.value999),
    Sharp(R.dimen.value0)
}

enum class BorderValues(val value: Dp) {
    Small(1.dp),
    Medium(2.dp),
    Large(4.dp),
    XLarge(8.dp)
}

enum class BorderInt(val value: Int) {
    Small(R.dimen.value2),
    Medium(R.dimen.value4),
    Large(R.dimen.value8),
    XLarge(R.dimen.value12)
}

enum class ElevationValues(val value: Dp) {
    Small(1.dp),
    Medium(2.dp),
    Large(4.dp),
    XLarge(8.dp)
}

enum class ElevationInt(val value: Int) {
    Small(R.dimen.value1),
    Medium(R.dimen.value2),
    Large(R.dimen.value4),
    XLarge(R.dimen.value8)
}
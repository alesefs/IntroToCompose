package com.example.introtocompose.components

import android.content.res.Resources
import kotlin.math.roundToInt

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

val Float.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
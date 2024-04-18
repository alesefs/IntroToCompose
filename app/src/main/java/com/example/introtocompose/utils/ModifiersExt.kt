package com.example.introtocompose.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.thenIf(
    given: Boolean,
    modifierBlock: @Composable() Modifier.() -> Modifier
): Modifier = if (given) modifierBlock(this) else this
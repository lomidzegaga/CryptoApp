package com.example.listio.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class Padding {
    data class All(val padding: Dp = 0.dp) : Padding()
    data class Each(
        val start: Dp = 0.dp, val top: Dp = 0.dp, val end: Dp = 0.dp, val bottom: Dp = 0.dp
    ) : Padding()

    data class Vertical(val vertical: Dp = 0.dp, val horizontal: Dp = 0.dp) : Padding()
}
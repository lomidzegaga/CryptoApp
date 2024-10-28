package com.example.core.presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import java.text.NumberFormat
import java.util.Locale

@Stable
fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() }, indication = null
    ) { onClick.invoke() }
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayableNumber(value = this, formatted = formatter.format(this))
}

fun Boolean.isActiveText(): String {
    return if (this) "Active" else "Inactive"
}

fun String.solidDescription(): String {
    return this.ifEmpty { "Information not provided" }
}

fun <T> List<T>.buttonState(): OnBoardingButtonState {
    return if (this.isEmpty()) OnBoardingButtonState.Retry else OnBoardingButtonState.Navigation
}
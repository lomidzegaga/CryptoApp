package com.example.listio.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.example.listio.presenter.model.DisplayableNumber
import java.text.NumberFormat
import java.util.Locale

@Stable
fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() }, indication = null
    ) { onClick.invoke() }
}

fun Modifier.customPadding(padding: Padding): Modifier {
    return when (padding) {
        is Padding.All -> this.padding(all = padding.padding)
        is Padding.Each -> this.padding(
            start = padding.start, top = padding.top, end = padding.end, bottom = padding.bottom
        )

        is Padding.Vertical -> this.padding(
            vertical = padding.vertical, horizontal = padding.horizontal
        )
    }
}

fun Double.formatPrice(): String {
    val format = NumberFormat.getInstance(Locale.US).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return format.format(this)
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = android.icu.text.NumberFormat.getNumberInstance(Locale.getDefault()).apply {
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

fun String.percentTextColor(): Color = when {
    this.endsWith("%") && this.dropLast(2).toDouble() >= 0 -> Color.Green
    this.endsWith("%") && this.dropLast(2).toDouble() < 0 -> Color.Red
    else -> Color(0xFF3C3C3C)
}
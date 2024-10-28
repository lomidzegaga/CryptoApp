package com.example.listio.presenter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.util.DisplayableNumber
import com.example.core.presentation.util.mediumBlack
import com.example.core.presentation.util.mediumGreen
import com.example.core.presentation.util.red

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {

    val icon = if (change.value < 0.0) {
        Icons.Default.KeyboardArrowDown
    } else {
        Icons.Default.KeyboardArrowUp
    }

    val containerColor = if (change.value < 0.0) {
        red
    } else {
        mediumGreen
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20f))
            .background(containerColor)
            .padding(horizontal = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = mediumBlack
        )

        Text(
            text = "${change.formatted} %",
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun PriceChangePreview() {
    PriceChange(
        change = DisplayableNumber(
            value = 1.32,
            formatted = "1.32"
        )
    )
}
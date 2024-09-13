package com.example.listio.utils.params

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.listio.utils.Padding
import com.example.listio.utils.percentTextColor

data class ParamsIconItem(
    val coinText: String,
    val moneyText: String,
    val percentText: String,
    val coinChangeText: String,
    val coinChangeTextColor: Color = coinChangeText.percentTextColor(),
    val mainRowPadding: Padding = Padding.Vertical(),
    val nestedRowPadding: Padding = Padding.Each(start = 20.dp, top = 4.dp, bottom = 4.dp),
    val imageSize: Dp = 60.dp,
    val imageSource: Any? = null,
    val contentDescription: String? = null,
    val clip: Shape = RoundedCornerShape(10.dp),
    val roundedCornerSize: Dp = 10.dp,
    val spaceBy: Dp = 8.dp,
)
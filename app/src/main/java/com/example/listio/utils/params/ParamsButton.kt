package com.example.listio.utils.params

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listio.utils.CustomColors
import com.example.listio.utils.Padding

data class ParamsButton(
    val text: String,
    val textPadding: Padding = Padding.All(),
    val buttonPadding: Padding = Padding.Vertical(horizontal = 20.dp),
    val textSize: TextUnit = 17.sp,
    val roundedCornerSize: Dp = 20.dp,
    val textColor: Color = Color.Black,
    val buttonColor: Color = CustomColors.buttonGreenColor,
    val fontWeight: FontWeight = FontWeight.Bold
)
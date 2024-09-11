package com.example.listio.utils.params

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.listio.utils.CustomColors
import com.example.listio.utils.Padding

data class ParamsText(
    val text: String,
    val color: Color = CustomColors.white,
    val fontWeight: FontWeight = FontWeight.Bold,
    val fontSize: TextUnit = 18.sp,
    val padding: Padding = Padding.All(),
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val textAlign: TextAlign? = null
)
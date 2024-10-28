package com.example.core.presentation.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core.presentation.util.solidDescription
import com.example.core.presentation.util.white

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = white,
    fontWeight: FontWeight? = FontWeight.Bold,
    fontSize: TextUnit = 18.sp,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified,
) {
    Text(
        text = text.solidDescription(),
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize,
        lineHeight = lineHeight,
        textAlign = textAlign,
        modifier = modifier
    )
}


@Preview
@Composable
fun CustomTextPreview() {
    CustomText(
        text = "this is custom Text",
    )
}
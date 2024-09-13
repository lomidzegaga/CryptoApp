package com.example.listio.presenter.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.listio.utils.customPadding
import com.example.listio.utils.params.ParamsText
import com.example.listio.utils.solidDescription

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    params: ParamsText
) {
    Text(
        text = params.text.solidDescription(),
        color = params.color,
        fontWeight = params.fontWeight,
        fontSize = params.fontSize,
        lineHeight = params.lineHeight,
        textAlign = params.textAlign,
        modifier = modifier.customPadding(params.padding)
    )
}
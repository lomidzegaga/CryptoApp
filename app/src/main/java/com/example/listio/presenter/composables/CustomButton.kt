package com.example.listio.presenter.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.listio.utils.customPadding
import com.example.listio.utils.params.ParamsButton
import com.example.listio.utils.params.ParamsText

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    params: ParamsButton,
    onNavigationClick: () -> Unit
) {
    Button(
        onClick = onNavigationClick, modifier = modifier
            .fillMaxWidth()
            .customPadding(params.buttonPadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = params.buttonColor
        ), shape = RoundedCornerShape(params.roundedCornerSize)
    ) {
        CustomText(
            params = ParamsText(
                text = params.text,
                color = params.textColor,
                fontSize = params.textSize,
                padding = params.textPadding,
                fontWeight = params.fontWeight
            )
        )
    }
}
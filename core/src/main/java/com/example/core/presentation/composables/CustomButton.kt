package com.example.core.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.util.boldBlack
import com.example.core.presentation.util.lightGreen

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    modifierForText: Modifier = Modifier,
    buttonPadding: Dp = 20.dp,
    fontSize: TextUnit = 17.sp,
    roundedCornerSize: Dp = 20.dp,
    onNavigationClick: () -> Unit
) {
    Button(
        onClick = onNavigationClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = buttonPadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = lightGreen
        ),
        shape = RoundedCornerShape(roundedCornerSize)
    ) {
        Text(
            text = text,
            color = boldBlack,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            modifier = modifierForText
        )
    }
}


@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(
        "Clickable button",
        onNavigationClick = { }
    )
}
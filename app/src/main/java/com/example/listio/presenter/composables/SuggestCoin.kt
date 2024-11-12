package com.example.listio.presenter.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.composables.CustomText
import com.example.core.presentation.util.lightGreen
import com.example.core.presentation.util.mediumBlack
import com.example.core.presentation.util.mediumGreen
import com.example.core.presentation.util.white
import com.example.listio.presenter.model.CoinUI

@Composable
fun SuggestCoin(
    modifier: Modifier = Modifier,
    coin: CoinUI
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                ambientColor = mediumGreen,
                spotColor = mediumGreen
            ),
        colors = CardDefaults.cardColors(
            containerColor = mediumBlack
        ),
        border = BorderStroke(width = 0.1.dp, color = white),
        shape = RoundedCornerShape(40f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 40.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                CustomText(
                    text = "$ ${coin.price.formatted}",
                    fontSize = 25.sp,
                    color = lightGreen,
                )

                PriceChange(change = coin.percentChange24h)
            }

            CustomText(
                text = coin.symbol,
                fontSize = 23.sp,
                color = lightGreen
            )
        }
    }
}


@Preview
@Composable
fun SuggestCoinPreview() {
    SuggestCoin(coin = previewCoin)
}
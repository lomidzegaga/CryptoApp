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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listio.presenter.model.CoinUI
import com.example.listio.utils.Padding
import com.example.listio.utils.params.ParamsButton

@Composable
fun SuggestCoin(
    modifier: Modifier = Modifier,
    coin: CoinUI,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161616)
        ),
        border = BorderStroke(width = 0.1.dp, color = Color.White),
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
                    color = Color(0xFFACD577),
                )

                PriceChange(change = coin.percentChange24h)
            }

            CustomText(
                text = coin.symbol,
                fontSize = 23.sp,
                color = Color(0xFFACD577)
            )
        }

        CustomButton(
            params = ParamsButton(
                text = "Buy",
                buttonPadding = Padding.Vertical(vertical = 25.dp, horizontal = 20.dp),
                textPadding = Padding.All(12.dp)
            )
        ) {
//            onClick(MainScreenActions.OnBuyClick)
        }
    }
}


@Preview
@Composable
fun SuggestCoinPreview() {
    SuggestCoin(coin = previewCoin, onClick = { })
}
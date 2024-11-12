package com.example.listio.presenter.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.composables.CustomText
import com.example.core.presentation.util.boldBlack
import com.example.core.presentation.util.clickableWithoutRipple
import com.example.core.presentation.util.mediumGreen
import com.example.listio.R
import com.example.listio.domain.model.Coin
import com.example.listio.presenter.model.CoinUI
import com.example.listio.presenter.model.toCoinUi

@Composable
fun CoinListItem(
    coin: CoinUI,
    onClick: (CoinUI) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(13.dp)
            .clickableWithoutRipple { onClick(coin) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.bitcoin),
            contentDescription = coin.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, top = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomText(text = coin.symbol)

                CustomText(
                    text = coin.changeFromAth.formatted,
                    fontSize = TextUnit.Unspecified,
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                CustomText(
                    text = "$ ${coin.price.formatted}",
                    fontSize = 19.sp,
                    color = mediumGreen
                )
                PriceChange(
                    change = coin.percentChange24h
                )
            }
        }
    }
}


@Preview
@Composable
fun CoinListItemPreview() {
    CoinListItem(
        coin = previewCoin,
        onClick = {  },
        modifier = Modifier.background(boldBlack)
    )
}

internal val previewCoin = Coin(
    rank = 1,
    id = "bitcoin",
    name = "Bitcoin",
    price = 38000.23,
    percentChange24h = 0.1,
    changeFromAth = 45.23,
    symbol = "BTC"
).toCoinUi()
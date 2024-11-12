package com.example.listio.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.core.presentation.composables.CustomText
import com.example.core.presentation.composables.ParentColumn
import com.example.core.presentation.util.DisplayableNumber
import com.example.core.presentation.util.mediumBlack
import com.example.core.presentation.util.solidDescription
import com.example.core.presentation.util.white
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.composables.Chart
import com.example.listio.presenter.composables.SuggestCoin
import com.example.listio.presenter.model.CoinUI

@Composable
fun CoinDetailsScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    ParentColumn(
        modifier = modifier
            .background(mediumBlack),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        state.selectedCoin?.let { coin ->
            SuggestCoin(coin = coin)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Chart()
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .verticalScroll(scrollState)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = white,
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(7.dp))
            CustomText(
                text = state.selectedCoin?.name?.solidDescription() ?: "error",
                fontSize = TextUnit.Unspecified,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Preview
@Composable
private fun CoinDetailsScreenPreview() {
    CoinDetailsScreen(
        state = selectedCoinState
    )
}


internal val selectedCoinState = CoinListState(
    selectedCoin = CoinUI(
        rank = 1,
        id = "",
        name = "Bitcoin",
        symbol = "BTC",
        price = DisplayableNumber(80000.12, "80,000.12"),
        percentChange24h = DisplayableNumber(1.12, "1.12"),
        changeFromAth = DisplayableNumber(3.12, "3.12")
    ),
)
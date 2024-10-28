package com.example.listio.presenter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.util.mediumBlack
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.model.CoinUI

@Composable
fun CoinsList(
    state: CoinListState,
    onClick: (CoinUI) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp)
            .background(mediumBlack, shape = RoundedCornerShape(20.dp))
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(state.coins) { coinUI ->
                CoinListItem(
                    coin = coinUI,
                    onClick = onClick
                )

                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
fun CoinListPreview() {
    CoinsList(
        state = state,
        onClick = { }
    )
}

internal val state = CoinListState(
    coins = listOf(previewCoin, previewCoin, previewCoin),
    isLoading = false
)
package com.example.listio.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.composables.CoinsList
import com.example.listio.presenter.composables.CustomText
import com.example.listio.presenter.composables.SearchFieldComposable
import com.example.listio.presenter.composables.SuggestCoin
import com.example.listio.presenter.composables.state


@Composable
fun CoinsListScreen(
    state: CoinListState,
//    onClick: (MainScreenActions) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
    ) {
        SearchFieldComposable(
            searchText = "",
            onClick = { searchText ->
//                viewModel.onClick(MainScreenActions.EnteredSearchText(searchText))
            }
        )

        state.suggestCoin?.let { coin ->
            SuggestCoin(
                coin = coin,
                onClick = { }
            )
        }

        CustomText(
            text = "Explore new",
            fontSize = TextUnit.Unspecified,
            modifier = Modifier.padding(start = 20.dp, top = 17.dp, bottom = 8.dp),
        )

        CoinsList(
            state = state,
            onClick = {
//                id -> viewModel.onClick(MainScreenActions.OnCoinDetailsClick(coinId = id))
            }

        )
    }
}


@Preview
@Composable
fun CoinsListScreenPreview() {
    CoinsListScreen(
        state = state
    )
}
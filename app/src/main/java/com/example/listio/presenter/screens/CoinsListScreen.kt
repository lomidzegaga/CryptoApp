package com.example.listio.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.core.presentation.composables.CustomText
import com.example.core.presentation.composables.ParentColumn
import com.example.core.presentation.util.mediumBlack
import com.example.listio.presenter.CoinListAction
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.composables.CoinsList
import com.example.listio.presenter.composables.SearchFieldComposable
import com.example.listio.presenter.composables.SuggestCoin
import com.example.listio.presenter.composables.state

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = pullToRefreshState,
        onRefresh = { onAction(CoinListAction.OnSwipeAction) },
        isRefreshing = state.isRefreshing
    ) {
        ParentColumn(
            modifier = modifier
                .background(mediumBlack)
        ) {

            SearchFieldComposable(
                searchText = state.searchText,
                onClick = { onAction.invoke(CoinListAction.OnSearch(it.uppercase())) },
                isListLoading = state.isSearchLoading
            )

            state.suggestCoin?.let { coin ->
                SuggestCoin(coin = coin)
            }

            CustomText(
                text = "Explore new",
                fontSize = TextUnit.Unspecified,
                modifier = Modifier.padding(start = 20.dp, top = 17.dp, bottom = 8.dp),
            )

            CoinsList(
                state = state,
                onClick = { coin -> onAction(CoinListAction.OnCoinClick(coin)) }
            )
        }
    }
}


@Preview
@Composable
fun CoinsListScreenPreview() {
    CoinsListScreen(
        state = state,
        onAction = { }
    )
}
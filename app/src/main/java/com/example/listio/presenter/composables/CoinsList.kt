package com.example.listio.presenter.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.util.lightGreen
import com.example.core.presentation.util.mediumBlack
import com.example.core.presentation.util.white
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.model.CoinUI
import kotlinx.coroutines.launch

@Composable
fun CoinsList(
    state: CoinListState,
    onClick: (CoinUI) -> Unit
) {

    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp)
            .background(mediumBlack, shape = RoundedCornerShape(20.dp))
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(
                items = state.coins,
                key = { coin -> coin.symbol }
            ) { coinUI ->
                CoinListItem(
                    coin = coinUI,
                    onClick = onClick
                )
            }
            if (state.coins.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No coins found",
                            color = white,
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }

        val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 5 } }
        val coroutine = rememberCoroutineScope()

        AnimatedVisibility(
            visible = showButton,
            enter = slideInVertically(),
            exit = slideOutVertically(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ) {
            Button(
                onClick = {
                    coroutine.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGreen
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Scroll To Top",
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 10.dp)
                )
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
    coins = listOf(previewCoin, previewCoin, previewCoin, previewCoin),
    isLoading = false
)
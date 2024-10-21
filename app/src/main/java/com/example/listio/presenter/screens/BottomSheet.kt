package com.example.listio.presenter.screens

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listio.presenter.view_models.MainScreenVM
import com.example.listio.utils.MainScreenActions
import com.example.listio.utils.params.ParamsCoinDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    screenHeight: Dp,
    viewModel: MainScreenVM = hiltViewModel(),
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = { viewModel.onClick(MainScreenActions.ShowBottomSheet(false)) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.Black
    ) {
//        viewModel.apply {
//            CoinDetails(
//                params = ParamsCoinDetails(
//                    maxSheetHeight = screenHeight,
//                    price = coins.value.find { it.rank == selectedCoin.value.rank }?.price
//                        ?: "error",
//                    percentChangeLast24h = coins.value.find { it.rank == selectedCoin.value.rank }?.percentChange24h.toString() + " %",
//                    coinDetails = selectedCoin.value
//                )
//            )
//        }
    }
}
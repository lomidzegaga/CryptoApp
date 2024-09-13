package com.example.listio.presenter.screens

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.listio.utils.params.ParamsCoinDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    params: ParamsCoinDetails,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.Black
    ) {
        CoinDetails(
            params = ParamsCoinDetails(
                maxSheetHeight = params.maxSheetHeight,
                price = params.price,
                percentChangeLast24h = params.percentChangeLast24h,
                coinDetails = params.coinDetails
            )
        )
    }
}
package com.example.listio.utils.params

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.listio.presenter.model.SelectedCoinUI

data class ParamsCoinDetails(
    val maxSheetHeight: Dp,
    val price: String,
    val percentChangeLast24h: String,
    val percentChangeColor: Color = Color.Unspecified,
    val coinDetails: SelectedCoinUI
)

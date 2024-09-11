package com.example.listio.data.model

import com.example.listio.presenter.model.SelectedCoinUIModel
import com.squareup.moshi.Json

data class GetCoinByIdDto(
    val id: String,
    val name: String,
    val symbol: String,
    @Json(name = "is_active") val isActive: Boolean,
    val logo: String,
    val description: String,
) {
    fun toSelectedCoinUIModel(): SelectedCoinUIModel = SelectedCoinUIModel(
        name = name,
        symbol = symbol,
        isActive = isActive,
        logo = logo,
        description = description
    )
}
package com.example.listio.presenter.model

data class SelectedCoinUIModel(
    val rank: Int = -1,
    val name: String = "",
    val symbol: String = "",
    val isActive: Boolean = false,
    val logo: String = "",
    val description: String = ""
)

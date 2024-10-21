package com.example.listio.domain.model

data class SelectedCoin(
    val rank: Int = -1,
    val name: String = "",
    val symbol: String = "",
    val isActive: Boolean = false,
    val logo: String = "",
    val description: String = ""
)

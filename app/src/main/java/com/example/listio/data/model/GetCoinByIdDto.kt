package com.example.listio.data.model

import com.squareup.moshi.Json

data class GetCoinByIdDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @Json(name = "is_active") val isActive: Boolean,
    val logo: String,
    val description: String,
)
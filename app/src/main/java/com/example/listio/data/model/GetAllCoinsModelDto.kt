package com.example.listio.data.model

import com.squareup.moshi.Json

data class GetAllCoinsModelDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    val type: String
)
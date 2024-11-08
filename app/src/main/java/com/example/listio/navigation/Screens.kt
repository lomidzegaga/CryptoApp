package com.example.listio.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    object OnBoarding

    @Serializable
    object CoinList

    @Serializable
    object CoinDetails

}
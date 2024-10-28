package com.example.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class RootNavTarget : Parcelable {

    @Parcelize
    data object OnBoarding : RootNavTarget()

    @Parcelize
    data object CoinList : RootNavTarget()

    @Parcelize
    data object CoinDetails : RootNavTarget()

    @Parcelize
    data object BuyCoin : RootNavTarget()

}
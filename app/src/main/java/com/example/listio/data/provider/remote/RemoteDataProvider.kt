package com.example.listio.data.provider.remote

import com.example.listio.data.model.CoinDto
import com.example.core.data.util.VERSION
import retrofit2.Response
import retrofit2.http.GET

fun interface RemoteDataProvider {

    @GET("/v$VERSION/tickers")
    suspend fun loadCoins(): Response<List<CoinDto>>

}
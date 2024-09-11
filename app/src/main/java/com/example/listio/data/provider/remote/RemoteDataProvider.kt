package com.example.listio.data.provider.remote

import com.example.listio.data.model.GetAllCoinsModelDto
import com.example.listio.data.model.GetAllTickersDto
import com.example.listio.data.model.GetCoinByIdDto
import com.example.listio.data.model.SearchCoinDto
import com.example.listio.utils.VERSION
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataProvider {

    @GET("/v$VERSION/coins")
    suspend fun getAllCoins(): Response<List<GetAllCoinsModelDto>>

    @GET("/v$VERSION/tickers")
    suspend fun getAllTickers(): Response<List<GetAllTickersDto>>

    @GET("/v$VERSION/coins/{coinId}")
    suspend fun getCoinById(
        @Path("coinId") coinId: String
    ): Response<GetCoinByIdDto>

    @GET("/v$VERSION/search")
    suspend fun searchCoin(
        @Query("q") searchCoin: String?
    ): Response<SearchCoinDto>

}
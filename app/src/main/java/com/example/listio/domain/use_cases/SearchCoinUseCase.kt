package com.example.listio.domain.use_cases

import com.example.listio.data.model.SearchCoinDto
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class SearchCoinUseCase @Inject constructor(
    private val coinRepositories: CoinRepositories
) {
    suspend fun execute(searchCoin: String?): Response<SearchCoinDto> =
        coinRepositories.searchCoin(searchCoin = searchCoin)
}
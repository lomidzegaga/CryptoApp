package com.example.listio.domain.use_cases

import com.example.listio.data.model.GetAllTickersDto
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class GetAllTickersUseCases @Inject constructor(
    private val coinRepositories: CoinRepositories
) {
    suspend fun execute(): Response<List<GetAllTickersDto>> = coinRepositories.getAllTickers()
}
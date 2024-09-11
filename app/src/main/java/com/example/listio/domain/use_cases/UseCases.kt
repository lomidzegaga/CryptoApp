package com.example.listio.domain.use_cases

data class UseCases(
    val getAllCoinsUseCase: GetAllCoinsUseCase,
    val getAllTickersUseCases: GetAllTickersUseCases,
    val getCoinByIdUseCase: GetCoinByIdUseCase,
    val searchCoinUseCase: SearchCoinUseCase
)

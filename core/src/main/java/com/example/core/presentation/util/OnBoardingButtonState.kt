package com.example.core.presentation.util

sealed interface OnBoardingButtonState {
    data object Navigation : OnBoardingButtonState
    data object Retry : OnBoardingButtonState
}
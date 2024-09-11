package com.example.listio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listio.domain.use_cases.UseCases
import com.example.listio.presenter.model.CoinUIModel
import com.example.listio.presenter.model.SelectedCoinUIModel
import com.example.listio.utils.Screens
import com.example.listio.utils.formatPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var currentScreen by mutableStateOf<Screens>(Screens.OnBoarding)
        private set

    var isButtonVisible by mutableStateOf(false)
        private set

    var showSheet by mutableStateOf(false)
        private set

    var isCoinDetailsLoaded by mutableStateOf(false)

    private val _coins = MutableStateFlow(listOf<CoinUIModel>())
    val coins = _coins.asStateFlow()

    private val _selectedCoins = MutableStateFlow(SelectedCoinUIModel())
    val selectedCoin = _selectedCoins.asStateFlow()


    init {
        getAllTickers()
    }

    private fun getAllTickers() {
        viewModelScope.launch {
            useCases.getAllTickersUseCases.execute().apply {
                if (isSuccessful) {
                    isButtonVisible = true
                    _coins.value = body()?.map { dto ->
                        CoinUIModel(
                            id = dto.id,
                            name = dto.name.take(4),
                            price = "$" + dto.quotes?.usd?.price?.formatPrice(),
                            percentChange24h = dto.quotes?.usd?.percentChange24h ?: 0.0,
                            changeFromAth = dto.quotes?.usd?.percentFromPriceAth.toString() + " " + dto.symbol.take(
                                4
                            ),
                            symbol = dto.symbol
                        )
                    } ?: emptyList()
                }
            }
        }
    }

    fun getCoinById(coinId: String) {
        viewModelScope.launch {
            useCases.getCoinByIdUseCase.execute(coinId).apply {
                body()?.let { dto ->
                    isCoinDetailsLoaded = true
                    _selectedCoins.value = dto.toSelectedCoinUIModel()
                }
            }
        }
    }

    fun showSheet(show: Boolean) {
        showSheet = show
    }

    fun navigateTo(destination: Screens) {
        currentScreen = destination
    }
}
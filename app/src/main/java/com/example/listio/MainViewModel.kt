package com.example.listio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listio.domain.use_cases.UseCases
import com.example.listio.presenter.model.CoinUIModel
import com.example.listio.presenter.model.SelectedCoinUIModel
import com.example.listio.utils.IoDispatcher
import com.example.listio.utils.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var currentScreen by mutableStateOf<Screens>(Screens.OnBoarding)
        private set

    var isButtonVisible by mutableStateOf(false)
        private set

    var showSheet by mutableStateOf(false)
        private set

    var isCoinDetailsLoaded by mutableStateOf(false)
    var showProgressIndicator by mutableStateOf(false)

    private val _coins = MutableStateFlow(listOf<CoinUIModel>())
    val coins = _coins.asStateFlow()

    private val _selectedCoins = MutableStateFlow(SelectedCoinUIModel())
    val selectedCoin = _selectedCoins.asStateFlow()

    init {
        getAllTickers()
    }

    /**
    By default, viewModelScope uses the `Dispatchers.Main` dispatcher
     **/
    private fun getAllTickers() = viewModelScope.launch {
        withContext(dispatcher) {
            useCases.getAllTickersUseCases.execute().apply {
                if (isSuccessful) {
                    isButtonVisible = true
                    _coins.value = body()?.take(50).let { dto -> dto?.map { it.toCoinUIModel() } }
                        ?: emptyList()
                }
            }
        }
    }

    fun getCoinById(coinId: String) = viewModelScope.launch {
        withContext(dispatcher) {
            showProgressIndicator = true

            useCases.getCoinByIdUseCase.execute(coinId).apply {
                body()?.let { dto ->
                    isCoinDetailsLoaded = true
                    showProgressIndicator = false
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


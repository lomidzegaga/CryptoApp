package com.example.listio.presenter.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listio.data.model.toCoin
import com.example.listio.domain.use_cases.UseCases
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.model.toCoinUi
import com.example.listio.utils.IoDispatcher
import com.example.listio.utils.MainScreenActions
import com.example.listio.utils.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor(
    private val useCases: UseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { getAllTickers() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            CoinListState()
        )

    var currentScreen by mutableStateOf<Screens>(Screens.OnBoarding)
        private set

    var showSheet by mutableStateOf(false)
        private set

    var showProgressIndicator by mutableStateOf(false)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    fun onClick(action: MainScreenActions) {
        when (action) {
            MainScreenActions.OnBuyClick -> {
                navigateTo(Screens.BuyCoins)
            }

            is MainScreenActions.OnCoinDetailsClick -> {
                getCoinById(coinId = action.coinId)
            }

            is MainScreenActions.EnteredSearchText -> {
                updateSearchText(text = action.text)
            }

            is MainScreenActions.ShowBottomSheet -> {
                if (!action.show) {
                    showSheet = false
                }
            }
        }
    }

    /**
    By default, viewModelScope uses the `Dispatchers.Main.immediate` dispatcher
     **/
    private fun getAllTickers() = viewModelScope.launch {
        withContext(dispatcher) {
            _state.update { it.copy(isLoading = true) }
            useCases.getAllTickersUseCases.execute().apply {
                if (isSuccessful) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = body()?.take(50)?.map { dto -> dto.toCoin().toCoinUi() }
                                ?: emptyList(),
                            suggestCoin = body()?.random()?.toCoin()?.toCoinUi()
                        )
                    }
                } else {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private fun getCoinById(coinId: String) = viewModelScope.launch {
        withContext(dispatcher) {
            showProgressIndicator = true

            useCases.getCoinByIdUseCase.execute(coinId).apply {
                body()?.let { dto ->
                    showSheet = true
                    showProgressIndicator = false
                }
            }
        }
    }

    private fun updateSearchText(text: String) {
        _searchText.value = text
    }

    fun navigateTo(destination: Screens) {
        currentScreen = destination
    }
}


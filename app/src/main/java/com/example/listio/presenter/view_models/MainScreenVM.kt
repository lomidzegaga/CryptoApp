package com.example.listio.presenter.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.util.Result
import com.example.listio.data.util.IoDispatcher
import com.example.listio.domain.use_cases.CoinListUseCase
import com.example.listio.presenter.CoinListAction
import com.example.listio.presenter.CoinListEvent
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.model.toCoinUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenVM @Inject constructor(
    private val coinListUseCase: CoinListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnBuyClick -> Unit

            is CoinListAction.OnCoinClick -> {
                _state.update { it.copy(selectedCoin = action.coinUi) }
            }

            CoinListAction.OnSwipeAction -> {
                _state.update { it.copy(isRefreshing = true) }
                loadCoins()
            }

            is CoinListAction.OnSearch -> {
                _state.update { it.copy(searchText = action.searchText) }
                when {
                    action.searchText.isEmpty() -> {
                        _state.update { it.copy(isSearchLoading = true) }
                        loadCoins()
                    }

                    action.searchText.length > 2 -> {
                        _state.update {
                            it.copy(
                                coins = _state.value.coins.filter { searchedText ->
                                    searchedText.symbol.contains(
                                        action.searchText
                                    )
                                },
                                isSearchLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    /**
    By default, viewModelScope uses the `Dispatchers.Main.immediate` dispatcher
     **/
    fun loadCoins() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        when (val response = coinListUseCase.execute()) {
            is Result.Error -> {
                _state.update { it.copy(isLoading = false, isRefreshing = false) }
                _events.send(CoinListEvent.Error(response.error))
            }

            is Result.Success -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        isSearchLoading = false,
                        coins = response.data.take(50).map { coin -> coin.toCoinUi() },
                        suggestCoin = response.data.random().toCoinUi()
                    )
                }
            }
        }
    }
}
package com.example.listio.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.util.ObserveAsEvents
import com.example.core.presentation.util.buttonState
import com.example.core.presentation.util.toString
import com.example.listio.presenter.CoinListAction
import com.example.listio.presenter.CoinListEvent
import com.example.listio.presenter.screens.CoinDetailsScreen
import com.example.listio.presenter.screens.CoinsListScreen
import com.example.listio.presenter.screens.OnBoardingScreen
import com.example.listio.MainScreenVM

@Composable
fun Navigation(
    modifier: Modifier = Modifier, viewModel: MainScreenVM = hiltViewModel()
) {

    val navController = rememberNavController()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val coinListAction = remember<(CoinListAction) -> Unit> {
        {
            viewModel.onAction(it)

            if (it is CoinListAction.OnCoinClick) {
                navController.navigate(Screens.CoinDetails)
            }
        }
    }

    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { events ->
        when (events) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context, events.error.toString(context), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.OnBoarding,
        modifier = modifier
    ) {

        composable<Screens.OnBoarding> {
            OnBoardingScreen(
                state = state,
                buttonState = state.coins.buttonState(),
                onNavigationClick = {
                    navController.navigate(route = Screens.CoinList) {
                        popUpTo<Screens.OnBoarding> { inclusive = true }
                    }
                },
                onRetryClick = viewModel::loadCoins
            )
        }

        composable<Screens.CoinList> {
            CoinsListScreen(
                state = state, onAction = coinListAction,
            )
        }

        composable<Screens.CoinDetails> {
            CoinDetailsScreen(
                state = state
            )
        }
    }
}
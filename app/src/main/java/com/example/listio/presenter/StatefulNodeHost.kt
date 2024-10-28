package com.example.listio.presenter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.IntegrationPoint
import com.bumble.appyx.core.node.node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import com.example.listio.presenter.nodes.coinDetailsListNode
import com.example.listio.presenter.nodes.coinListNode
import com.example.listio.presenter.nodes.onBoardingNode
import com.example.listio.presenter.view_models.MainScreenVM
import com.example.navigation.RootNavTarget
import com.example.navigation.RootNode
import com.example.navigation.TransitionType

@Composable
fun StatefulNodeHost(
    integrationPoint: IntegrationPoint,
    modifier: Modifier = Modifier,
    viewModel: MainScreenVM = hiltViewModel()
) {

    NodeHost(integrationPoint = integrationPoint, modifier = modifier) { buildContext ->
        val backStack: BackStack<RootNavTarget> = BackStack(
            initialElement = RootNavTarget.OnBoarding,
            savedStateMap = buildContext.savedStateMap
        )
        RootNode(
            buildContext = buildContext,
            transitionType = TransitionType.Swipe,
            backStack = backStack,
            onBoarding = {
                onBoardingNode(
                    buildContext = buildContext,
                    viewModel = viewModel,
                    onNavigationClick = { backStack.push(RootNavTarget.CoinList) },
                    onRetryClick = { viewModel.loadCoins() }
                )
            },
            coinList = {
                coinListNode(
                    buildContext = buildContext,
                    viewModel = viewModel,
                    onAction = { action ->
                        viewModel.onAction(action)
                        backStack.push(RootNavTarget.CoinDetails)
                    }
                )
            },
            coinDetails = {
                coinDetailsListNode(
                    buildContext = buildContext,
                    viewModel = viewModel
                )
            },
            buyNewCoin = {
                node(buildContext) {
                    // buy new coin UI here
                }
            }
        )
    }
}
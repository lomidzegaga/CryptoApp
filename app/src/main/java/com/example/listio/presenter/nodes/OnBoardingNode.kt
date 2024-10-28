package com.example.listio.presenter.nodes

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.node
import com.example.core.presentation.util.buttonState
import com.example.listio.presenter.screens.OnBoardingScreen
import com.example.listio.presenter.view_models.MainScreenVM

fun onBoardingNode(
    buildContext: BuildContext,
    viewModel: MainScreenVM,
    onNavigationClick: () -> Unit,
    onRetryClick: () -> Unit
) = node(buildContext) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    OnBoardingScreen(
        state = state,
        buttonState = state.coins.buttonState(),
        onNavigationClick = onNavigationClick,
        onRetryClick = onRetryClick
    )
}
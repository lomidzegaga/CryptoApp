package com.example.listio.presenter.nodes

import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.node
import com.example.listio.presenter.CoinListAction
import com.example.listio.presenter.screens.CoinsListScreen
import com.example.listio.presenter.view_models.MainScreenVM

fun coinListNode(
    buildContext: BuildContext,
    viewModel: MainScreenVM,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) = node(buildContext) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    CoinsListScreen(
        state = state,
        onAction = onAction,
        modifier = modifier
    )
}
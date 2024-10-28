package com.example.listio.presenter.nodes

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.node
import com.example.listio.presenter.screens.CoinDetailsScreen
import com.example.listio.presenter.view_models.MainScreenVM

fun coinDetailsListNode(
    buildContext: BuildContext,
    viewModel: MainScreenVM,
) = node(buildContext) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    CoinDetailsScreen(
        state = state
    )
}
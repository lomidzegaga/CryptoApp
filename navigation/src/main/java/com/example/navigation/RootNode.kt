package com.example.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack

class RootNode(
    buildContext: BuildContext,
    private val transitionType: TransitionType,
    private val backStack: BackStack<RootNavTarget>,
    private val onBoarding: () -> Node,
    private val buyNewCoin: () -> Node,
    private val coinDetails: () -> Node,
    private val coinList: () -> Node
) : ParentNode<RootNavTarget>(
    navModel = backStack,
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberSwipeTransitionHandler(transitionType),
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        )
    }

    override fun resolve(navTarget: RootNavTarget, buildContext: BuildContext): Node {
        return when (navTarget) {
            RootNavTarget.OnBoarding -> onBoarding()
            RootNavTarget.CoinList -> coinList()
            RootNavTarget.CoinDetails -> coinDetails()
            RootNavTarget.BuyCoin -> buyNewCoin()
        }
    }
}
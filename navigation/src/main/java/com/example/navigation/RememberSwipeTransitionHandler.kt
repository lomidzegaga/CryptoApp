package com.example.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.IntOffset
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.core.navigation.transition.TransitionHandler
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import kotlin.math.roundToInt

@Composable
fun rememberSwipeTransitionHandler(transitionType: TransitionType): TransitionHandler<RootNavTarget, BackStack.State> {
    return when (transitionType) {
        TransitionType.Custom -> remember { SwipeTransitionHandler() }
        TransitionType.Swipe -> rememberBackstackSlider(
            transitionSpec = {
                spring(
                    stiffness = 100f,
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            }
        )
    }
}


class SwipeTransitionHandler : ModifierTransitionHandler<RootNavTarget, BackStack.State>() {

    @SuppressLint("ModifierFactoryExtensionFunction")
    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<RootNavTarget, BackStack.State>
    ): Modifier = modifier.composed {
        val height = descriptor.params.bounds.height.value

        val alpha by transition.animateFloat(
            transitionSpec = {
                spring(
                    stiffness = 100f,
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            },
            targetValueByState = { it.toProps(height).alpha },
            label = "alpha"
        )

        val offset by transition.animateOffset(
            transitionSpec = {
                spring(
                    stiffness = 80f,
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            },
            targetValueByState = { it.toProps(height).offset },
            label = "offset"
        )

        val scale by transition.animateFloat(
            transitionSpec = {
                spring(
                    stiffness = 70f,
                    dampingRatio = Spring.DampingRatioNoBouncy,
                )
            },
            targetValueByState = { it.toProps(height).scale },
            label = "scale"
        )

        this
            .offset {
                IntOffset(
                    x = (offset.x * density).roundToInt(),
                    y = (offset.y * density).roundToInt(),
                )
            }
            .scale(scale)
            .alpha(alpha)
    }

}
package com.example.navigation

import androidx.compose.ui.geometry.Offset
import com.bumble.appyx.navmodel.backstack.BackStack

data class Props(
    val scale: Float = 1f,
    val offset: Offset = Offset.Zero,
    val alpha: Float = 1f
)

private val created = Props(alpha = 1f)
private val active = created.copy(alpha = 1f, scale = 1f)
private val stashed = active.copy(alpha = 1f, scale = 0.6f)
private val destroyed = stashed.copy(alpha = 0f, scale = 1.25f)

fun BackStack.State.toProps(height: Float): Props = when (this) {
    BackStack.State.CREATED -> created.copy(offset = Offset(0f, 2f * height))
    BackStack.State.ACTIVE -> active
    BackStack.State.STASHED -> stashed
    BackStack.State.DESTROYED -> destroyed
}

sealed interface TransitionType {
    data object Swipe : TransitionType
    data object Custom : TransitionType
}
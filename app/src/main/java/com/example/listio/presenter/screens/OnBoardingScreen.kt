package com.example.listio.presenter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core.presentation.composables.CustomButton
import com.example.core.presentation.composables.ParentBox
import com.example.core.presentation.util.OnBoardingButtonState
import com.example.core.presentation.util.boldBlack
import com.example.core.presentation.util.lightGreen
import com.example.listio.R
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.composables.state

@Composable
fun OnBoardingScreen(
    state: CoinListState,
    buttonState: OnBoardingButtonState,
    onNavigationClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val brush = remember { Brush.verticalGradient(listOf(lightGreen, boldBlack)) }

    ParentBox(
        modifier = modifier.background(brush)
    ) {

        OnBoardingAnimation()

        AnimatedVisibility(
            visible = state.isLoading.not(),
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {

            when (buttonState) {
                OnBoardingButtonState.Navigation -> {
                    CustomButton(
                        text = "Let's get started!",
                        modifierForText = Modifier.padding(vertical = 13.dp),
                        onNavigationClick = onNavigationClick
                    )
                }

                OnBoardingButtonState.Retry -> {
                    CustomButton(
                        text = "Retry!",
                        modifierForText = Modifier.padding(vertical = 13.dp),
                        onNavigationClick = onRetryClick
                    )
                }
            }
        }
    }
}

@Composable
fun OnBoardingAnimation(
    modifier: Modifier = Modifier
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.onboarding_animation)
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        modifier = modifier
    )
}

@Preview
@Composable
fun OnBoardingPreview() {
    OnBoardingScreen(
        state = state,
        buttonState = OnBoardingButtonState.Navigation,
        onNavigationClick = { },
        onRetryClick = { }
    )
}
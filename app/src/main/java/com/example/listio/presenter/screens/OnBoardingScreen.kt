package com.example.listio.presenter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core.presentation.composables.CustomButton
import com.example.core.presentation.composables.CustomText
import com.example.core.presentation.util.boldBlack
import com.example.core.presentation.util.lightGreen
import com.example.listio.R
import com.example.listio.presenter.CoinListState
import com.example.core.presentation.util.OnBoardingButtonState
import com.example.listio.presenter.composables.state

@Composable
fun OnBoardingScreen(
    state: CoinListState,
    buttonState: OnBoardingButtonState,
    onNavigationClick: () -> Unit,
    onRetryClick: () -> Unit
) {

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.onboarding_animation
        )
    )
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition, iterations = LottieConstants.IterateForever, isPlaying = true
    )
    val brush = Brush.verticalGradient(listOf(lightGreen, boldBlack))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = { preloaderProgress },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier.padding(bottom = 18.dp)
        ) {
            CustomText(
                text = "Ready to exchange cryptocurrency \n in just two clicks?",
                textAlign = TextAlign.Center,
                lineHeight = 26.sp
            )

            AnimatedVisibility(
                visible = state.isLoading.not(),
                enter = scaleIn(),
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
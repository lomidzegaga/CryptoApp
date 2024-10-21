package com.example.listio.presenter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.listio.R
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.composables.CustomButton
import com.example.listio.presenter.composables.CustomText
import com.example.listio.utils.Padding
import com.example.listio.utils.params.ParamsButton

@Composable
fun OnBoarding(
    state: CoinListState,
    onNavigationClick: () -> Unit
) {

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.onboarding_animation
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition, iterations = LottieConstants.IterateForever, isPlaying = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
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
            verticalArrangement = Arrangement.spacedBy(30.dp)
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
                CustomButton(
                    params = ParamsButton(
                        text = "Let's get started!",
                        textPadding = Padding.Vertical(vertical = 13.dp)
                    ),
                    onNavigationClick = onNavigationClick
                )
            }
        }
    }
}
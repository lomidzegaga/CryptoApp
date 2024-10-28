package com.example.listio.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.composables.CustomText
import com.example.core.presentation.util.lightGreen
import com.example.core.presentation.util.mediumBlack
import com.example.core.presentation.util.solidDescription
import com.example.core.presentation.util.white
import com.example.listio.presenter.CoinListState
import com.example.listio.presenter.composables.SuggestCoin
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.Zoom
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

@Composable
fun CoinDetailsScreen(
    state: CoinListState,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    val scrollState = rememberScrollState()

    val entryCount = 15

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mediumBlack)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                tint = white,
                modifier = Modifier
                    .size(25.dp)
            )

            CustomText(
                text = state.selectedCoin?.symbol ?: "error",
                fontSize = 25.sp
            )

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = white,
                modifier = Modifier
                    .size(25.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        state.selectedCoin?.let { coin ->
            SuggestCoin(coin = coin, onAction = { })
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(mediumBlack, shape = RoundedCornerShape(20.dp)),
        ) {
            val modelProducer = remember { CartesianChartModelProducer() }
            LaunchedEffect(Unit) {
                withContext(dispatcher) {
                    modelProducer.runTransaction {
                        lineSeries { series(List(entryCount) { Random.nextFloat() * 20 }) }
                    }
                }
            }
            CartesianChartHost(
                chart =
                rememberCartesianChart(
                    rememberLineCartesianLayer(
                        lineProvider =
                        LineCartesianLayer.LineProvider.series(
                            rememberLine(remember {
                                LineCartesianLayer.LineFill.single(fill(lightGreen))
                            })
                        ),
                    ),
                ),
                modelProducer = modelProducer,
                zoomState = rememberVicoZoomState(
                    zoomEnabled = true,
                    initialZoom = Zoom.min(Zoom.static(), Zoom.Content)
                ),
                modifier = Modifier
                    .padding(vertical = 50.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .verticalScroll(scrollState)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = white,
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(7.dp))
            CustomText(
                text = state.selectedCoin?.name?.solidDescription() ?: "error",
                fontSize = TextUnit.Unspecified,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
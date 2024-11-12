package com.example.listio.presenter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.util.lightGreen
import com.example.core.presentation.util.transparent
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
fun Chart(
    modifier: Modifier = Modifier,
    entryCount: Int = 15,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    Box(
        modifier = modifier
            .padding(10.dp)
            .background(transparent),
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
            chart = rememberCartesianChart(
                rememberLineCartesianLayer(
                    lineProvider = LineCartesianLayer.LineProvider.series(
                        rememberLine(remember {
                            LineCartesianLayer.LineFill.single(fill(lightGreen))
                        })
                    ),
                ),
            ),
            modelProducer = modelProducer, zoomState = rememberVicoZoomState(
                zoomEnabled = true, initialZoom = Zoom.min(Zoom.static(), Zoom.Content)
            ),
            modifier = Modifier.padding(vertical = 50.dp)
        )
    }
}


@Preview
@Composable
fun ChartPreview() {
    Chart()
}
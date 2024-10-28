package com.example.listio.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.example.core.presentation.util.ObserveAsEvents
import com.example.core.presentation.util.toString
import com.example.listio.presenter.view_models.MainScreenVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NodeActivity() {

    private val viewModel by viewModels<MainScreenVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            StatefulNodeHost(integrationPoint = appyxIntegrationPoint, viewModel = viewModel)

            ObserveAsEvents(events = viewModel.events) { events ->
                when (events) {
                    is CoinListEvent.Error -> {
                        Toast.makeText(
                            this, events.error.toString(this), Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}
package com.raulespim.flights.feature_flighthistory.presentation.flights.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.raulespim.flights.R
import com.raulespim.flights.common.composable.ProgressComponent
import com.raulespim.flights.common.composable.TryAgainComponent
import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightsState
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightsViewModel

@Composable
fun FlightsScreen(
    onClick: (Flight) -> Unit,
    navController: NavHostController,
    viewModel: FlightsViewModel = hiltViewModel()
) {
    val uiState by viewModel.flightsState.collectAsState()

    FlightsScreenContent(uiState, navController, onClick)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightsScreenContent(
    uiState: FlightsState,
    navController: NavHostController,
    onClick: (Flight) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name), color = Color.White)
            },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black.copy(alpha = 0.1f))
            )
        },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                when (uiState) {
                    is FlightsState.Loading -> ProgressComponent()
                    is FlightsState.TryAgain -> TryAgainComponent(uiState.errorMessage)
                    is FlightsState.FlightsSuccess -> FlightsListComponent(uiState.flights, onClick)
                }
            }
        }
    )
}

@Composable
fun FlightsListComponent(flights: List<Flight>, onClick: (Flight) -> Unit) {
    val isHorizontal = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(if (isHorizontal) 3 else 2)
    ) {
        items(flights, key = { it.id }) { flight ->
            FlightListItem(flight, onClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightsScreenContentPreview() {
    FlightsScreenContent(
        uiState = FlightsState.FlightsSuccess(
            listOf(
                Flight(
                    id = "1",
                    status = "COMPLETED",
                    completionStatus = "ON_TIME",
                    startDate = "01/01/2023",
                    endDate = "01/01/2023",
                    departureTime = "12:00",
                    arrivalTime = "13:00",
                    departureAirport = "Madrid",
                    arrivalAirport = "Barcelona",
                    airplaneName = "Boeing 747"
                )
            )
        ),
        navController = rememberNavController(),
        onClick = {}
    )
}
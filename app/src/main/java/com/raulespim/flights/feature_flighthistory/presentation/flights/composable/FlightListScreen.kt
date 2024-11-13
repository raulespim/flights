package com.raulespim.flights.feature_flighthistory.presentation.flights.composable

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.raulespim.flights.R
import com.raulespim.flights.common.composable.ProgressComponent
import com.raulespim.flights.common.composable.TryAgainComponent
import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.data.model.FlightStatus
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightListState
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightViewModel

@Composable
fun FlightListScreen(
    onClick: (Flight) -> Unit,
    navController: NavHostController,
    viewModel: FlightViewModel = hiltViewModel()
) {
    val uiState by viewModel.flightListState.collectAsState()

    FlightListScreenContent(uiState, navController, onClick)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightListScreenContent(
    uiState: FlightListState,
    navController: NavHostController,
    onClick: (Flight) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.flight_history), color = Color.White)
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                when (uiState) {
                    is FlightListState.Loading -> ProgressComponent()
                    is FlightListState.TryAgain -> TryAgainComponent(uiState.errorMessage)
                    is FlightListState.FlightListSuccess -> FlightListComponent(uiState.flights, onClick)
                }
            }
        }
    )
}

@Composable
fun FlightListComponent(flights: List<Flight>, onClick: (Flight) -> Unit) {
    val context = LocalContext.current
    val categorizedFlights = categorizeFlights(context, flights)

    val allFlights = stringResource(R.string.all_flights)
    val completedFlights = context.getString(R.string.completed_flights)
    val canceledFlights = context.getString(R.string.canceled_flights)
    val upcomingFlights = context.getString(R.string.upcoming_flights)
    val inFlight = context.getString(R.string.in_flight)

    val sections = listOf(
        allFlights to flights,
        completedFlights to (categorizedFlights[completedFlights] ?: emptyList()),
        canceledFlights to (categorizedFlights[canceledFlights] ?: emptyList()),
        upcomingFlights to (categorizedFlights[upcomingFlights] ?: emptyList()),
        inFlight to (categorizedFlights[inFlight] ?: emptyList())
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        sections.forEach { (category, flightsInCategory) ->
            item {
                Text(
                    text = "${category.uppercase()} (${flightsInCategory.size})",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            if (flightsInCategory.isNotEmpty()) {
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        items(flightsInCategory, key = { it.id }) { flight ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            ) {
                                FlightListItem(flight) { onClick(it) }
                            }
                        }
                    }
                }
            } else {
                item {
                    Text(text = stringResource(R.string.no_flights_available_for_this_status),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Start,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

private fun categorizeFlights(context: Context, flights: List<Flight>): Map<String, List<Flight>> {
    return flights.groupBy { flight ->
        when (flight.status) {
            FlightStatus.Completed.value -> context.getString(R.string.completed_flights)
            FlightStatus.Canceled.value -> context.getString(R.string.canceled_flights)
            FlightStatus.Upcoming.value -> context.getString(R.string.upcoming_flights)
            FlightStatus.InFlight.value -> context.getString(R.string.in_flight)
            else -> ""
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightsScreenContentPreview() {
    FlightListScreenContent(
        uiState = FlightListState.FlightListSuccess(
            listOf(
                Flight(
                    id = "1",
                    status = "CONCLUIDO",
                    completionStatus = "ON_TIME",
                    startDate = "01/01/2023",
                    endDate = "01/01/2023",
                    departureTime = "12:00",
                    arrivalTime = "13:00",
                    departureAirport = "Madrid",
                    arrivalAirport = "Barcelona",
                    airplaneName = "Boeing 747"
                ),
                Flight(
                    id = "2",
                    status = "CANCELADO",
                    completionStatus = "ON_TIME",
                    startDate = "01/01/2023",
                    endDate = "01/01/2023",
                    departureTime = "12:00",
                    arrivalTime = "13:00",
                    departureAirport = "Madrid",
                    arrivalAirport = "Barcelona",
                    airplaneName = "Boeing 747"
                ),
                Flight(
                    id = "3",
                    status = "CANCELADO",
                    completionStatus = "ON_TIME",
                    startDate = "01/01/2023",
                    endDate = "01/01/2023",
                    departureTime = "12:00",
                    arrivalTime = "13:00",
                    departureAirport = "Madrid",
                    arrivalAirport = "Barcelona",
                    airplaneName = "Boeing 747"
                ),
                Flight(
                    id = "4",
                    status = "CANCELADO",
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
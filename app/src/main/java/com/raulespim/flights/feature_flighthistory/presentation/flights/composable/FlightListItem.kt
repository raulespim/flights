package com.raulespim.flights.feature_flighthistory.presentation.flights.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulespim.flights.feature_flighthistory.data.model.Flight

@Composable
fun FlightListItem(
    flight: Flight,
    onItemClick: (Flight) -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable(
                onClick = { onItemClick(flight) }
            )
            .padding(horizontal = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = flight.airplaneName, style = MaterialTheme.typography.titleLarge)
            Text(text = flight.status, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightListItemPreview() {
    FlightListItem(
        flight = Flight(
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
        ),
        onItemClick = {}
    )
}
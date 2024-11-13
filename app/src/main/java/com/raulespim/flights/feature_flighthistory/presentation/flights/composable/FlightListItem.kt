package com.raulespim.flights.feature_flighthistory.presentation.flights.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulespim.flights.R
import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.data.model.FlightStatus
import com.raulespim.flights.ui.theme.BlueLight
import com.raulespim.flights.ui.theme.GrayLight
import com.raulespim.flights.ui.theme.GreenLight
import com.raulespim.flights.ui.theme.RedLight

@Composable
fun FlightListItem(
    flight: Flight,
    modifier: Modifier = Modifier,
    onItemClick: (Flight) -> Unit
) {
    Card(
        onClick = { onItemClick(flight) },
        modifier = modifier
            .height(200.dp)
            .widthIn(min = 200.dp, max = 300.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.flight, flight.id),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                FlightStatusBadge(flight.status)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.departure, flight.departureAirport),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.arrival, flight.arrivalAirport),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.date, flight.startDate, flight.endDate),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.time, flight.departureTime, flight.arrivalTime),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.plane, flight.airplaneName),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(R.string.status, flight.completionStatus),
                    style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        }
    }
}

@Composable
fun FlightStatusBadge(status: String) {
    val color = when (status) {
        FlightStatus.Completed.value -> GreenLight
        FlightStatus.Canceled.value -> RedLight
        FlightStatus.InFlight.value -> BlueLight
        FlightStatus.Upcoming.value -> GrayLight
        else -> Color.Gray
    }

    Surface(
        color = color,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FlightListItemPreview() {
    FlightListItem(
        flight = Flight(
            id = "AB123",
            status = "CONCLUIDO",
            completionStatus = "NO_HORARIO",
            startDate = "01/01/2023",
            endDate = "01/01/2023",
            departureTime = "12:00",
            arrivalTime = "13:00",
            departureAirport = "JFK - John F. Kennedy International Airport",
            arrivalAirport = "LAX - Los Angeles International Airport",
            airplaneName = "Boeing 747"
        ),
        onItemClick = {}
    )
}
package com.raulespim.flights.feature_flighthistory.presentation.flights

sealed class FlightEvent {
    data object Start : FlightEvent()
}
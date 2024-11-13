package com.raulespim.flights.feature_flighthistory.presentation.flights

sealed class FlightsEvent {
    data object Start : FlightsEvent()
}
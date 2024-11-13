package com.raulespim.flights.feature_flighthistory.domain

import com.raulespim.flights.feature_flighthistory.domain.use_case.GetFlights

data class FlightUseCases(
    val getFlights: GetFlights
)

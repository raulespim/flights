package com.raulespim.flights.feature_flighthistory.data.repository_remote

import com.raulespim.flights.feature_flighthistory.data.model.Flight

interface FlightRepository {

    suspend fun getFlights(): List<Flight>
}
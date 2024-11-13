package com.raulespim.flights.feature_flighthistory.domain.repository

import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightApi
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightRepository
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor(
    private val api: FlightApi,
) : FlightRepository {

    override suspend fun getFlights(): List<Flight> {
        return api.getFlights().flights
    }

}
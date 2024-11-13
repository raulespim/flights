package com.raulespim.flights.feature_flighthistory.domain.repository

import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightApi
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightRepository
import javax.inject.Inject

class FlightRepositoryImpl @Inject constructor(
    private val api: FlightApi,
) : FlightRepository {

    /**
    Save information while app is running
     */
    companion object CacheLocal {
        lateinit var listFlights: List<Flight>
    }

    override suspend fun getFlights(): List<Flight> {
        return api.getFlights().flights.apply {
            listFlights = this
        }
    }

}
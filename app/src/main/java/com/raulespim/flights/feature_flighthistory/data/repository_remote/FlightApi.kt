package com.raulespim.flights.feature_flighthistory.data.repository_remote

import com.raulespim.flights.feature_flighthistory.data.model.FlightResponse
import retrofit2.http.GET

interface FlightApi {

    @GET("flights")
    suspend fun getFlights(): FlightResponse
}
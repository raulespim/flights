package com.raulespim.flights.feature_flighthistory.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FlightResponse(
    @SerializedName("flights") val flights: List<Flight>
)

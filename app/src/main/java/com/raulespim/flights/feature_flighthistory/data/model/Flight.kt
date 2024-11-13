package com.raulespim.flights.feature_flighthistory.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Flight(
    @SerializedName("flight_id") val id: String,
    @SerializedName("status") val status: String,
    @SerializedName("completion_status") val completionStatus: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("end_date") val endDate: String,
    @SerializedName("departure_time") val departureTime: String,
    @SerializedName("arrival_time") val arrivalTime: String,
    @SerializedName("departure_airport") val departureAirport: String,
    @SerializedName("arrival_airport") val arrivalAirport: String,
    @SerializedName("airplane_name") val airplaneName: String
)

enum class FlightStatus(val value: String) {
    Completed("CONCLUIDO"),
    Canceled("CANCELADO"),
    Upcoming("A_REALIZAR"),
    InFlight("EM_VIAGEM")
}
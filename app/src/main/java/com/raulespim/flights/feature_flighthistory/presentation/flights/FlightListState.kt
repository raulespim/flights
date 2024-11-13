package com.raulespim.flights.feature_flighthistory.presentation.flights

import com.raulespim.flights.common.UiText
import com.raulespim.flights.feature_flighthistory.data.model.Flight

sealed class FlightListState {
    data class FlightListSuccess(
        val flights: List<Flight> = emptyList()
    ) : FlightListState()

    data object Loading : FlightListState()
    data class TryAgain(val errorMessage: UiText?) : FlightListState()
}

package com.raulespim.flights.feature_flighthistory.presentation.flights

import com.raulespim.flights.common.UiText
import com.raulespim.flights.feature_flighthistory.data.model.Flight

sealed class FlightsState {
    data class FlightsSuccess(
        val flights: List<Flight> = emptyList()
    ) : FlightsState()

    data object Loading : FlightsState()
    data class TryAgain(val errorMessage: UiText?) : FlightsState()
}

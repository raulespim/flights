package com.raulespim.flights.feature_flighthistory.presentation.flights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulespim.flights.common.RequestResource
import com.raulespim.flights.feature_flighthistory.domain.FlightUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FlightsViewModel @Inject constructor(
    private val flightUsesCase: FlightUseCases,
) : ViewModel() {

    private val _flightsState = MutableStateFlow<FlightsState>(FlightsState.Loading)
    val flightsState = _flightsState.asStateFlow()

    init {
        on(FlightsEvent.Start)
    }

    fun on(event: FlightsEvent) {
        when (event) {
            is FlightsEvent.Start -> getFlights()
        }
    }

    private fun getFlights() {
        flightUsesCase.getFlights().onEach { currentResult ->

            _flightsState.value = when (currentResult) {
                is RequestResource.Success -> FlightsState.FlightsSuccess(currentResult.data!!)
                is RequestResource.Error -> FlightsState.TryAgain(currentResult.message)
                is RequestResource.Loading -> FlightsState.Loading
            }
        }.launchIn(viewModelScope)
    }
}
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
class FlightViewModel @Inject constructor(
    private val flightUsesCase: FlightUseCases,
) : ViewModel() {

    private val _flightListState = MutableStateFlow<FlightListState>(FlightListState.Loading)
    val flightListState = _flightListState.asStateFlow()

    init {
        on(FlightEvent.Start)
    }

    fun on(event: FlightEvent) {
        when (event) {
            is FlightEvent.Start -> getFlights()
        }
    }

    private fun getFlights() {
        flightUsesCase.getFlights().onEach { currentResult ->

            _flightListState.value = when (currentResult) {
                is RequestResource.Success -> FlightListState.FlightListSuccess(currentResult.data!!)
                is RequestResource.Error -> FlightListState.TryAgain(currentResult.message)
                is RequestResource.Loading -> FlightListState.Loading
            }
        }.launchIn(viewModelScope)
    }
}
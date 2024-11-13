package com.raulespim.flights.feature_flighthistory.domain.repository

sealed interface OrderType {
    data object Ascending: OrderType
    data object Descending: OrderType
}
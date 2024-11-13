package com.raulespim.flights.common

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute(val route: String) {

    @Serializable
    data object HomeScreen : ScreenRoute("home_screen")

    @Serializable
    data object FlightsScreen : ScreenRoute("flights_screen")
}
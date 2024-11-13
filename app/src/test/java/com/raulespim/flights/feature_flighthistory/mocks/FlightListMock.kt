package com.raulespim.flights.feature_flighthistory.mocks

import com.raulespim.flights.feature_flighthistory.data.model.Flight

val flightListMock = listOf(
    Flight(
        id = "AB123",
        status = "CONCLUIDO",
        completionStatus = "NO_HORARIO",
        startDate = "01/01/2023",
        endDate = "01/01/2023",
        departureTime = "12:00",
        arrivalTime = "13:00",
        departureAirport = "JFK - John F. Kennedy International Airport",
        arrivalAirport = "LAX - Los Angeles International Airport",
        airplaneName = "Boeing 747"
    ),
    Flight(
        id = "CD456",
        status = "CONCLUIDO",
        completionStatus = "NO_HORARIO",
        startDate = "01/01/2023",
        endDate = "01/01/2023",
        departureTime = "12:00",
        arrivalTime = "13:00",
        departureAirport = "JFK - John F. Kennedy International Airport",
        arrivalAirport = "LAX - Los Angeles International Airport",
        airplaneName = "Boeing 747"
    )
)
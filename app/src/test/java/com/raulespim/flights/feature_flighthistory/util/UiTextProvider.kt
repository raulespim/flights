package com.raulespim.flights.feature_flighthistory.util

import com.raulespim.flights.R
import com.raulespim.flights.common.UiText

fun getIOExceptionMessage() = UiText.Resource(R.string.check_your_internet_connection)

fun getHttpExceptionMessage(
    message: String?
) = when {
    message != null -> UiText.Dynamic(message)
    else -> UiText.Resource(R.string.an_unexpected_error_occured)
}
package com.raulespim.flights.common.util

import com.raulespim.flights.R
import com.raulespim.flights.common.UiText
import retrofit2.HttpException

fun HttpException.toErrorMessage(): UiText {
    return if(localizedMessage.isNullOrEmpty()) {
        UiText.Resource(R.string.an_unexpected_error_occured)
    } else {
        UiText.Dynamic(localizedMessage)
    }
}
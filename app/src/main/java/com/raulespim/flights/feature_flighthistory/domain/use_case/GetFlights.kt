package com.raulespim.flights.feature_flighthistory.domain.use_case

import com.raulespim.flights.R
import com.raulespim.flights.common.RequestResource
import com.raulespim.flights.common.UiText
import com.raulespim.flights.common.util.toErrorMessage
import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightRepository
import com.raulespim.flights.feature_flighthistory.domain.repository.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetFlights(
    private val repository: FlightRepository
) {

    operator fun invoke(
        order: OrderType = OrderType.Descending
    ): Flow<RequestResource<List<Flight>>> = flow {
        try {
            emit(RequestResource.Loading())
            val flights = repository.getFlights().map { it.copy() }
            emit(RequestResource.Success(flights))
        } catch (e: HttpException) {
            emit(RequestResource.Error(e.toErrorMessage()))
        } catch (e: IOException) {
            emit(RequestResource.Error(UiText.Resource(R.string.check_your_internet_connection)))
        }
    }
}
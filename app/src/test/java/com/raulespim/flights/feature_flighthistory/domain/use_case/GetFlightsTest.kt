package com.raulespim.flights.feature_flighthistory.domain.use_case

import com.raulespim.flights.common.RequestResource
import com.raulespim.flights.common.UiText
import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightRepository
import com.raulespim.flights.feature_flighthistory.mocks.flightListMock
import com.raulespim.flights.feature_flighthistory.util.assertInstanceOf
import com.raulespim.flights.feature_flighthistory.util.assertSameClass
import com.raulespim.flights.feature_flighthistory.util.getHttpExceptionMessage
import com.raulespim.flights.feature_flighthistory.util.getIOExceptionMessage
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetFlightsTest {

    private val repository: FlightRepository = mockk()
    private val getFlights = GetFlights(repository)

    @Test
    fun `invoke should return RequestResource Success`() = runTest {
        // Given
        val fakeFlights = flightListMock
        coEvery { repository.getFlights() } returns fakeFlights

        // When
        val result = getFlights().toList()

        // Then
        assertEquals(2, result.size)
        assertSameClass(RequestResource.Loading<List<Flight>>(), result[0])
        assertSameClass(RequestResource.Success(fakeFlights), result[1])
        assertEquals(fakeFlights.first().id, result[1].data!!.first().id)
    }


    @Test
    fun `invoke should return RequestResource Fail IOException`() = runTest {
        // Given
        coEvery { repository.getFlights() } throws IOException()
        val uiTextExpected = getIOExceptionMessage()
        // When
        val result = getFlights().toList()

        // Then
        assertEquals(2, result.size)
        assertSameClass(RequestResource.Loading<List<Flight>>(), result[0])
        assertSameClass(RequestResource.Error<List<Flight>>(message = uiTextExpected), result[1])
        assertInstanceOf<UiText.Resource>(result[1].message)
        assertEquals(uiTextExpected.id, (result[1].message as UiText.Resource).id)
    }

    @Test
    fun `invoke should return RequestResource Fail HttpException`() = runTest {
        // Given
        val response = mockk<Response<*>>(
            relaxed = true
        )
        val message = "error message"
        every { response.message() } returns message
        every { response.code() } returns 500

        coEvery { repository.getFlights() } throws HttpException(response)
        val uiTextExpected = getHttpExceptionMessage(message)
        // When
        val result = getFlights().toList()

        // Then
        assertEquals(2, result.size)
        assertSameClass(RequestResource.Loading<List<Flight>>(), result[0])
        assertSameClass(RequestResource.Error<List<Flight>>(message = uiTextExpected), result[1])
        assertSameClass(uiTextExpected, result[1].message)
        assertInstanceOf<UiText.Dynamic>(result[1].message)
    }
}
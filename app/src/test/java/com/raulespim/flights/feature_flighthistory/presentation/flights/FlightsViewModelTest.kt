package com.raulespim.flights.feature_flighthistory.presentation.flights

import com.raulespim.flights.common.RequestResource
import com.raulespim.flights.common.UiText
import com.raulespim.flights.feature_flighthistory.data.model.Flight
import com.raulespim.flights.feature_flighthistory.domain.FlightUseCases
import com.raulespim.flights.feature_flighthistory.mocks.flightListMock
import com.raulespim.flights.feature_flighthistory.util.assertInstanceOf
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlightsViewModelTest {

    private lateinit var flightUseCases: FlightUseCases
    private lateinit var flightViewModel: FlightViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        flightUseCases = mockk(relaxed = true)
        flightViewModel = FlightViewModel(flightUseCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
        clearAllMocks()
    }

    @Test
    fun `should update flightListState on successful flight list retrieval`() = runTest {
        // Arrange
        val successResult = RequestResource.Success(flightListMock)
        every { flightUseCases.getFlights() } returns flowOf(successResult)

        // Act
        flightViewModel.on(FlightEvent.Start)

        // Assert
        assertInstanceOf<FlightListState.FlightListSuccess>(flightViewModel.flightListState.value)
        assertSame(
            flightListMock,
            (flightViewModel.flightListState.value as FlightListState.FlightListSuccess).flights
        )
    }

    @Test
    fun `should update flightListState on error during flight list retrieval`() = runTest {
        // Arrange
        val uiText = UiText.Dynamic("Some error message")
        val errorResult = RequestResource.Error<List<Flight>>(
            message = uiText
        )
        coEvery { flightUseCases.getFlights() } returns flowOf(errorResult)

        // Act
        flightViewModel.on(FlightEvent.Start)

        // Assert
        assertInstanceOf<FlightListState.TryAgain>(flightViewModel.flightListState.value)
        assertSame(
            uiText,
            (flightViewModel.flightListState.value as FlightListState.TryAgain).errorMessage
        )
    }

    @Test
    fun `should update flightListState on loading during flight list retrieval`() = runTest {
        // Arrange
        val loadingResult = RequestResource.Loading<List<Flight>>()
        coEvery { flightUseCases.getFlights() } returns flowOf(loadingResult)

        // Act
        flightViewModel.on(FlightEvent.Start)

        // Assert
        assertInstanceOf<FlightListState.Loading>(flightViewModel.flightListState.value)
    }
}
package com.raulespim.flights.feature_flighthistory.presentation.flights.composable

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.raulespim.flights.common.RequestResource
import com.raulespim.flights.common.UiText
import com.raulespim.flights.feature_flighthistory.domain.FlightUseCases
import com.raulespim.flights.feature_flighthistory.domain.use_case.GetFlights
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class FlightListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun whenFailRequest_ShouldShowTryAgainComponent() = runTest {
        val getFlights = mockk<GetFlights>()
        val useCase = FlightUseCases(getFlights)
        val uiText = UiText.Dynamic("Error message")
        every { getFlights.invoke() } returns flowOf(
            RequestResource.Error(
                message = uiText
            )
        )

        composeTestRule.setContent {
            FlightListScreen({}, rememberNavController(), FlightViewModel(useCase))
        }

        composeTestRule.onNodeWithText(uiText.text).assertExists()
        composeTestRule.onNodeWithText(uiText.text).assertIsDisplayed()
    }
}
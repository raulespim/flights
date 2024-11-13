package com.raulespim.flights.feature_flighthistory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raulespim.flights.common.ScreenRoute
import com.raulespim.flights.feature_flighthistory.presentation.flights.composable.FlightListScreen
import com.raulespim.flights.feature_flighthistory.presentation.home.HomeScreen
import com.raulespim.flights.ui.theme.FlightsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlightsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavHost()
                }
            }
        }
    }

    @Composable
    private fun SetupNavHost() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = ScreenRoute.HomeScreen.route
        ) {
            composable(route = ScreenRoute.HomeScreen.route) {
                HomeScreen(
                    onViewFlightHistory = {
                        navController.navigate(ScreenRoute.FlightsScreen.route)
                    }
                )
            }

            composable(route = ScreenRoute.FlightsScreen.route) {
                FlightListScreen(onClick = {}, navController = navController)
            }

        }
    }
}
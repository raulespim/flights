package com.raulespim.flights

import com.raulespim.flights.di.AppModule
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightApi
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightRepository
import com.raulespim.flights.feature_flighthistory.domain.FlightUseCases
import com.raulespim.flights.feature_flighthistory.domain.repository.FlightRepositoryImpl
import com.raulespim.flights.feature_flighthistory.domain.use_case.GetFlights
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.spyk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class AppModuleTest {
    @Provides
    @Singleton
    fun provideApi(): FlightApi {
        return spyk()
    }

    @Provides
    @Singleton
    fun provideFlightRepository(api: FlightApi): FlightRepository {
        return FlightRepositoryImpl(api)
    }

    @Provides
    fun provideViewModel(cases: FlightUseCases): FlightViewModel {
        return FlightViewModel(cases)
    }

    @Provides
    @Singleton
    fun provideFlightUseCase(repository: FlightRepository): FlightUseCases {
        return FlightUseCases(
            getFlights = GetFlights(repository)
        )
    }
}
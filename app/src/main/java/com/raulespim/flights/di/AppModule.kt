package com.raulespim.flights.di

import com.raulespim.flights.common.FlightsUrl
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightApi
import com.raulespim.flights.feature_flighthistory.data.repository_remote.FlightRepository
import com.raulespim.flights.feature_flighthistory.domain.FlightUseCases
import com.raulespim.flights.feature_flighthistory.domain.repository.FlightRepositoryImpl
import com.raulespim.flights.feature_flighthistory.domain.use_case.GetFlights
import com.raulespim.flights.feature_flighthistory.presentation.flights.FlightsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): FlightApi {
        return Retrofit.Builder()
            .baseUrl(FlightsUrl.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlightApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightRepository(api: FlightApi): FlightRepository {
        return FlightRepositoryImpl(api)
    }

    @Provides
    fun provideViewModel(cases: FlightUseCases): FlightsViewModel {
        return FlightsViewModel(cases)
    }

    @Provides
    @Singleton
    fun provideFlightUseCase(repository: FlightRepository): FlightUseCases {
        return FlightUseCases(
            getFlights = GetFlights(repository)
        )
    }

}
package com.hasan.weatherapp.di

import com.hasan.weatherapp.data.repository.RepositoryImpl
import com.hasan.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
   abstract fun bindsWeatherRepository(weatherRepositoryImpl: RepositoryImpl): WeatherRepository


}
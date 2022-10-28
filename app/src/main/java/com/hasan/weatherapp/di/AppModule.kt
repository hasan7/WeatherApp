package com.hasan.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.hasan.weatherapp.data.database.Converters
import com.hasan.weatherapp.data.database.WeatherDatabase
import com.hasan.weatherapp.data.remote.WeatherApi
import com.hasan.weatherapp.data.util.MoshiParser
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }



    @Provides
    @Singleton
    fun provideWeatherDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app, WeatherDatabase::class.java, "weather_db"
        ).addTypeConverter(Converters(MoshiParser(Moshi.Builder().build())))
            .build()
    }

}
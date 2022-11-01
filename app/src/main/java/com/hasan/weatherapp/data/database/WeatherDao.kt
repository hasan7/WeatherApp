package com.hasan.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hasan.weatherapp.data.database.entity.LocationEntity

import com.hasan.weatherapp.domain.weather.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfos(infos: WeatherData)

    @Query("DELETE FROM WeatherData")
    suspend fun deleteWeatherInfo()

    @Query("SELECT * FROM WeatherData ")
     fun gettWeatherInfos(): Flow<List<WeatherData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIsLocationRequested(isRequested: LocationEntity)

    @Query("SELECT isLocationRequested FROM LocationEntity")
    suspend fun isLocationRequested(): Boolean?

    @Query("DELETE FROM LocationEntity")
    suspend fun deleteIsLocationRequested()


}


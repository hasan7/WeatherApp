package com.hasan.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.hasan.weatherapp.domain.weather.WeatherData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfos(infos: WeatherData)

//    @Query("DELETE FROM WeatherData WHERE time IN(:time)")
//    suspend fun deletetWeatherInfos(time: LocalDateTime)

    @Query("DELETE FROM WeatherData")
    suspend fun deletetWeatherInfo()

//    @Query("SELECT * FROM WeatherData WHERE time LIKE '%' || :time || '%'")
//    suspend fun gettWeatherInfos(time: LocalDateTime): List<WeatherData>
    @Query("SELECT * FROM WeatherData ")
     fun gettWeatherInfos(): Flow<List<WeatherData>>
}
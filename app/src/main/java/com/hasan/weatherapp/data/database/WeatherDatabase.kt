package com.hasan.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hasan.weatherapp.data.database.entity.LocationEntity
import com.hasan.weatherapp.domain.weather.WeatherData

@Database(
    entities = [WeatherData::class,LocationEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {

    abstract val dao: WeatherDao
}
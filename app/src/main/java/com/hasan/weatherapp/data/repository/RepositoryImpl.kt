package com.hasan.weatherapp.data.repository


import com.hasan.weatherapp.data.database.WeatherDatabase
import com.hasan.weatherapp.data.database.entity.LocationEntity
import com.hasan.weatherapp.data.mapper.toWeatherInfo
import com.hasan.weatherapp.data.remote.WeatherApi
import com.hasan.weatherapp.domain.repository.WeatherRepository
import com.hasan.weatherapp.domain.util.Resource
import com.hasan.weatherapp.domain.weather.WeatherInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val weatherApi: WeatherApi,
    val weatherDatabase: WeatherDatabase
) : WeatherRepository {

    val dao = weatherDatabase.dao
    val now = LocalDateTime.now()
    val dayOfMonth = LocalDate.now().dayOfMonth

    override suspend fun isOutdated(): Boolean {
        val list = dao.gettWeatherInfos().first()
        return (list[list.size-1].time < now)
    }

    private suspend fun WeatherDataFromDb(): WeatherInfo = withContext(Dispatchers.IO){


        val weatherdataMap = dao.gettWeatherInfos().first().groupBy { it.time.dayOfMonth}

        val currentWeatherData = weatherdataMap[dayOfMonth]?.find {
            val hour = if(now.minute < 30) now.hour else now.hour + 1
            it.time.hour == hour
        }
            return@withContext WeatherInfo(
                weatherDataPerDay = weatherdataMap,
                currentWeatherData= currentWeatherData
            )
    }

    private suspend fun WeatherDataFromApi(lat: Double, long: Double): WeatherInfo = withContext(Dispatchers.IO){

        return@withContext weatherApi.getWeatherData(lat = lat, long = long).toWeatherInfo()
    }

    override suspend fun isLocationRequested(): Boolean? = withContext(Dispatchers.IO) {

        return@withContext dao.isLocationRequested()
    }

    override suspend fun insertIsLocationRequested(value: Boolean) = withContext(Dispatchers.IO) {
        dao.insertIsLocationRequested(LocationEntity(value))
    }

    override suspend fun purgeAllData() = withContext(Dispatchers.IO){
        dao.deleteWeatherInfo()
        dao.deleteIsLocationRequested()
    }

    override suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo> {

        return try {
            if (WeatherDataFromDb().weatherDataPerDay?.isEmpty() == true){

                val weatherDataApi = WeatherDataFromApi(lat = lat, long = long)
                weatherDataApi.weatherDataPerDay?.forEach {
                it.value.forEach {
                    dao.insertWeatherInfos(it)
                }
            }
                return  Resource.Success(
                    data = weatherDataApi
                )
            } else{
                return  Resource.Success(
                    data = WeatherDataFromDb()
                )
            }

        }catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "error occurred")

        }
    }
}
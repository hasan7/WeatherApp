package com.hasan.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.weatherapp.domain.location.LocationTracker
import com.hasan.weatherapp.domain.repository.WeatherRepository
import com.hasan.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(
    val locationTracker: LocationTracker,
    val repository: WeatherRepository,

) : ViewModel() {



    private var _weatherState = MutableStateFlow(WeatherState())
    val weatherState = _weatherState.asStateFlow()



      fun getWeatherBylocation() {

         viewModelScope.launch {

             if(repository.isLocationRequested() == null){
                 locationTracker.getCurrentLocation()?.let {
                     repository.insertIsLocationRequested(true)
                     when(val result= repository.getWeatherInfo(it.latitude, it.longitude)){

                         is Resource.Success-> {

                             _weatherState.update {
                                 it.copy(
                                     weatherInfo = result.data,
                                     Loading = false,
                                     errorMassage = null
                                 )
                             }

                         }
                         is Resource.Error -> {
                             _weatherState.update {
                                 it.copy(
                                     weatherInfo = null,
                                     Loading = false,
                                     errorMassage =result.message
                                 )
                             }
                         }
                     }
                 }
             } else{
                 if (repository.isOutdated()){
                     getCurrentLocation()
                 }
                 when(val result= repository.getWeatherInfo(0.0, 0.0)){

                     is Resource.Success-> {

                         _weatherState.update {
                             it.copy(
                                 weatherInfo = result.data,
                                 Loading = false,
                                 errorMassage = null
                             )
                         }

                     }
                     is Resource.Error -> {
                         _weatherState.update {
                             it.copy(
                                 weatherInfo = null,
                                 Loading = false,
                                 errorMassage =result.message
                             )
                         }
                     }
                 }
             }


         }
     }

    fun getCurrentLocation() {
        viewModelScope.launch {
            repository.purgeAllData()
            _weatherState.update {
                it.copy(
                    weatherInfo = null,
                    Loading = true,
                    errorMassage =null
                )
            }
            getWeatherBylocation()
        }
    }
}
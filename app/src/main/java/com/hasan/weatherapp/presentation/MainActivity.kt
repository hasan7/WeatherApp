package com.hasan.weatherapp.presentation

import android.Manifest
import android.content.ContentValues.TAG

import android.os.Bundle
import android.util.Log

import android.view.View
import androidx.activity.ComponentActivity

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.hasan.weatherapp.databinding.ActivtyMainBinding

import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: ViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    lateinit var binding: ActivtyMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtyMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val weatherDate = binding.weatherDate
        val weatherImg = binding.weatherImg
        val weatherTemp = binding.weatherTemp
        val weatherType = binding.weatherType
        val weatherPre = binding.pressureText
        val weatherDrop = binding.dropText
        val weatherWind = binding.windText
        val progressBar = binding.progressBar
        val cardview = binding.cardView
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (!it.containsValue(false)) {
                viewModel.getWeatherBylocation()
            } else{
                weatherTemp.text = "please allow permissions!"
                progressBar.visibility = View.INVISIBLE
            }
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))

        val adapter = HourlyWeatherAdapter()
        val adapter2 = HourlyWeatherAdapter()
        binding.hourlyWeatherRecyclerview.adapter = adapter
        binding.hourlyWeatherRecyclerview.layoutManager = linearLayoutManager

        binding.hourlyWeatherRecyclerviewTomorrow.adapter = adapter2
        binding.hourlyWeatherRecyclerviewTomorrow.layoutManager = linearLayoutManager2

        lifecycleScope.launch {
            viewModel.weatherState.collect {

                    if(!it.Loading){
                        progressBar.visibility = View.INVISIBLE
                    }
                if(it.weatherInfo != null){
                    cardview.visibility = View.VISIBLE
                    weatherDate.text = "last update "+it.weatherInfo?.currentWeatherData?.time?.format(DateTimeFormatter.ofPattern("HH:mm"))
                    weatherImg.setImageResource(it.weatherInfo?.currentWeatherData?.weatherType?.iconRes!!)
                    weatherTemp.text = it.weatherInfo?.currentWeatherData?.temperatureCelsius.toString()+"°C"
                    weatherType.text = it.weatherInfo?.currentWeatherData?.weatherType?.weatherDesc
                    weatherPre.text = it.weatherInfo?.currentWeatherData?.pressure?.roundToInt().toString()+" hpa"
                    weatherDrop.text = it.weatherInfo?.currentWeatherData?.humidity?.roundToInt().toString()+"%"
                    weatherWind.text = it.weatherInfo?.currentWeatherData?.windSpeed?.roundToInt().toString()+" km/h"

                    adapter.data = it.weatherInfo?.weatherDataPerDay?.get(LocalDate.now().dayOfMonth)!!.filter { it.time > LocalDateTime.now() }
                    adapter2.data = it.weatherInfo?.weatherDataPerDay?.get(LocalDate.now().dayOfMonth+1)!!
                }
            }
        }

    }
}
package com.hasan.weatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.hasan.weatherapp.domain.location.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): Location? {

//        withContext(Dispatchers.IO){
//
//        }
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            return null
        }

        // TODO: hmm try diffrent coroutines like the one below, whats the diffrance
//        coroutineScope {
//            val x = async {
//                locationClient.lastLocation.addOnSuccessListener {
//                    return@addOnSuccessListener
//
//                }
//                    .addOnFailureListener {
//                        return@addOnFailureListener
//                    }
//                    .addOnCanceledListener {
//                        return@addOnCanceledListener
//                    }
//            }.await()
//            return@coroutineScope x
//        }


            return suspendCancellableCoroutine { continuation ->
                locationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,
                    object : CancellationToken() {
                        override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {

                            return CancellationTokenSource().token
                        }

                        override fun isCancellationRequested(): Boolean {
                            return false
                        }

                    }).addOnSuccessListener { location -> continuation.resume(location) }
                    .addOnFailureListener {  continuation.resume(null) }
                    .addOnCanceledListener { continuation.cancel() }
//                locationClient.lastLocation.addOnSuccessListener { location ->
//                    continuation.resume(location)
//
//                }
//                    .addOnFailureListener {
//                        continuation.resume(null)
//                    }
//                    .addOnCanceledListener {
//                        continuation.cancel()
//                    }
        }

    }
}
package com.example.runningmate

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.runningmate.other.TrackingUtility
import com.example.runningmate.ui.theme.RunningMateTheme
import com.example.runningmate.views.RunningMateApp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : ComponentActivity() {

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Install splash screen if necessary
        // installSplashScreen()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        // Check and request location permissions when the app starts
        if (!TrackingUtility.hasLocationPermissions(this)) {
            requestLocationPermissions()
        } else {
            // Proceed with accessing location
            accessLocation()
        }

        setContent {
            RunningMateTheme {
                RunningMateApp()
            }
        }
    }

    @SuppressLint("Range")
    private fun hasLocationPermissions(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) Manifest.permission.ACCESS_BACKGROUND_LOCATION else ""
        )
    }

    @SuppressLint("Range")
    private fun requestLocationPermissions() {
        // Request permissions if not granted
        EasyPermissions.requestPermissions(
            this,
            "We need your location to provide services.",
            LOCATION_PERMISSION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) Manifest.permission.ACCESS_BACKGROUND_LOCATION else ""
        )
    }

    private fun accessLocation() {
        // Check if permission is granted
        if (hasLocationPermissions()) {
            // If permission is granted, get the last known location
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationClient.lastLocation
                .addOnSuccessListener(this, OnSuccessListener<Location> { location ->
                    // Handle the location result
                    if (location != null) {
                        // Use the location data here, for example:
                        val latitude = location.latitude
                        val longitude = location.longitude

                        // Display the location in a Toast message (or use it in your app)
                        Toast.makeText(this, "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_LONG).show()
                    } else {
                        // Handle the case when no location is available
                        Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show()
                    }
                })
        } else {
            // If permission is not granted, request permission
            requestLocationPermissions()
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if(EasyPermissions.hasPermissions(this, *permissions)){
            accessLocation()
        }else {
            // If permission is permanently denied, show settings dialog
//            if (EasyPermissions.somePermissionPermanentlyDenied(this, *permissions)) {
//                AppSettingsDialog.Builder(this).build().show()
//            }
        }
    }
}

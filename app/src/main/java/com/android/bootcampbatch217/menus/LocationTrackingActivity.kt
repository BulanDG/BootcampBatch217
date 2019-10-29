package com.android.bootcampbatch217.menus

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.bootcampbatch217.R
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_location_tracking.*

/*
macam2 source positioning

1. GPS -> menggunakan sensor d device (paling akurat)
2. Cell_id -> menggunakan provider BTS terdekat
3. Wifi -> menggunakan positioning pada router / transmitter

*/

class LocationTrackingActivity : AppCompatActivity() {
    val context = this
    val PERMISSION_LOCATION=55 //angka bebas, beda tiap permission
    var fusedLocationProviderClient:FusedLocationProviderClient?=null
    var locationRequest :LocationRequest?=null
    var locationCallback : LocationCallback?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_tracking)

        buttonStartLocation.setOnClickListener {
            if(checkPermissonLocation()){
               initLocationProvider()

                buttonStartLocation.isEnabled=!buttonStartLocation.isEnabled
                buttonStopLocation.isEnabled=!buttonStopLocation.isEnabled
            }
        }

        buttonStopLocation.setOnClickListener {
            removeLocationProvider()
            buttonStartLocation.isEnabled=!buttonStartLocation.isEnabled
            buttonStopLocation.isEnabled=!buttonStopLocation.isEnabled
        }

        enableLocationRequest()

    }

    fun checkPermissonLocation():Boolean{
        val currentAPIVersion =  Build.VERSION.SDK_INT

        if(currentAPIVersion>=Build.VERSION_CODES.M){
            if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED||
                    checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_LOCATION)

                return false
            } else{
                return true
            }
        }else{
            return true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==PERMISSION_LOCATION){
            if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                && grantResults[1]==PackageManager.PERMISSION_GRANTED){

                enableLocationRequest()
            }else{
                Toast.makeText(context, "Anda belum memberikan ijin akses location",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initLocationProvider(){
        if(fusedLocationProviderClient==null){
            fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(context)
            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }
    }

    fun removeLocationProvider(){
        fusedLocationProviderClient!!.removeLocationUpdates(locationCallback)
    }

    fun buildLocationRequest(){
        locationRequest= LocationRequest()
        locationRequest!!.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest!!.setInterval(5000) //interval waktu refresh location
        locationRequest!!.setFastestInterval(2000)
        locationRequest!!.setSmallestDisplacement(10f)
    }

    fun buildLocationCallback(){
        locationCallback=object :LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
//                super.onLocationResult(location)

                for(location in locationResult.locations){
                    val latitude_val =location.latitude.toString()
                    val longitude_val = location.longitude.toString()
                    val altitude_val = location.altitude.toString() //ketinggian
                    val accuracy_val = location.accuracy.toString() //

                    //set hasil
                    latitude.text=latitude_val
                    longitude.text=longitude_val
                }
            }
        }
    }

    fun enableLocationRequest(){
        if(checkPermissonLocation()){
            buildLocationRequest()
            buildLocationCallback()

            if(!checkIsGPSEnabled()){
                showLocationSetting()
            }
        }
    }

    fun checkIsGPSEnabled():Boolean{
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return true
        }
        else{
            return false
        }
    }

    fun showLocationSetting(){
        val info = AlertDialog.Builder(context)
        info.setMessage("Anda Belum Mengaktifkan Location, apakah anda mau mengaktifkannya?")
        info.setCancelable(false)
        info.setPositiveButton("Ya"){dialog, id ->
            val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS) //open menu setting location
            startActivity(intent)
        }
        info.setNegativeButton("Tidak"){dialog, id ->
            dialog.cancel()
        }

        info.create().show()
    }
}

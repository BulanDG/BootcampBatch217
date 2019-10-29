package com.android.bootcampbatch217.menus

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.bootcampbatch217.R
import kotlinx.android.synthetic.main.activity_air_location.*
import mumayank.com.airlocationlibrary.AirLocation

class AirLocationActivity : AppCompatActivity() {
    val context = this
    var airLocation: AirLocation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_location)

        buttonGetAirLocation.setOnClickListener {
            getMyCurrentLocation()
        }

        if(!checkIsGPSEnabled()){
            showLocationSetting()
        }
    }

    fun getMyCurrentLocation(){
        airLocation = AirLocation(context, true,
            true,
            object:AirLocation.Callbacks{
                override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                    Toast.makeText(context,"Gagal mendeteksi lokasi!!",Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(location: Location) {
                    val latitude_value= location.latitude
                    val longitude_value =location.longitude

                    latitude.text =latitude_value.toString()
                    longitude.text =longitude_value.toString()
                }

            })
    }

    //handle request result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        airLocation!!.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        airLocation!!.onActivityResult(requestCode,resultCode,data)
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
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS) //open menu setting location
            startActivity(intent)
        }
        info.setNegativeButton("Tidak"){dialog, id ->
            dialog.cancel()
        }

        info.create().show()
    }
}

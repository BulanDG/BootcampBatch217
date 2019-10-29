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

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_air_location.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_maps.latitude
import kotlinx.android.synthetic.main.activity_maps.longitude
import mumayank.com.airlocationlibrary.AirLocation

/*
1. to create maps activity -> file-> new-> google-> google maps activity
2. configure gradle app
implementation 'com.github.mumayank:AirLocation:1.2'
3. configure gradle project
 classpath 'com.google.gms:google-services:4.3.2'
4. set api key on xml layout
*/

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    var context= this
    var airLocation:AirLocation?=null

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        buttonMyCurrentLocation.setOnClickListener {
            getMyCurrentLocation()
        }

        if(!checkIsGPSEnabled()){
            showLocationSetting()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0,151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun getMyCurrentLocation(){
        airLocation = AirLocation(context, true,
            true,
            object: AirLocation.Callbacks{
                override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                    Toast.makeText(context,"Gagal mendeteksi lokasi!!", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(location: Location) {
                    val latitude_value= location.latitude
                    val longitude_value =location.longitude

                    latitude.text =latitude_value.toString()
                    longitude.text =longitude_value.toString()

                    //1. konversi lat long menjadi LatLng
                    val myCurrentLocation = LatLng(location.latitude,location.longitude)
                    //2. tambahkan marker dan title
                    mMap.addMarker(MarkerOptions().position(myCurrentLocation).title("saya sekarang di sini!!"))
                    //3. pindahkan titik tengah map ke my current location
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myCurrentLocation))
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

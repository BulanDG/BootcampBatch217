package com.android.bootcampbatch217.menus

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.android.bootcampbatch217.R
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.ByteArrayOutputStream
import java.util.jar.Manifest

class CameraActivity : AppCompatActivity() {
    val context= this
    val ID_PEMANGGIL=1
    val ID_PERMISSION_CAMERA=11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        buttonCamera.setOnClickListener {
            if(checkPermissionCamera()){
                ambilImageDariCamera()
            }
        }
    }

    fun checkPermissionCamera():Boolean{
        val currentAPIVersion:Int = Build.VERSION.SDK_INT //check versi android OS
        if(currentAPIVersion>=Build.VERSION_CODES.M){
            //runtime permission
            if(checkSelfPermission(android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                //belum dapat ijin
                requestPermissions(Array<String>(1){android.Manifest.permission.CAMERA},ID_PERMISSION_CAMERA)
                return false
            }else{
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
        if(requestCode==ID_PERMISSION_CAMERA){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //diijinkan
                ambilImageDariCamera()
            }else{
                //tidak diijinkan
                Toast.makeText(context,"Anda harus beri ijin akses untuk menggunakan fitur ini",Toast.LENGTH_SHORT)
            }
        }
    }

    fun ambilImageDariCamera(){

        val intentCamera= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera,ID_PEMANGGIL) // return diambil di fungsi onActivityResult
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==ID_PEMANGGIL && resultCode==Activity.RESULT_OK){
            showImageFromCamera(data)
        }
    }

    fun showImageFromCamera(data:Intent?){
        data?.let {
            //proses konversi menjadi bitmap
            var bitmap= data.extras?.get("data") as Bitmap
            var byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
            previewPhotoFromCamera.setImageBitmap(bitmap)
        }

    }


}

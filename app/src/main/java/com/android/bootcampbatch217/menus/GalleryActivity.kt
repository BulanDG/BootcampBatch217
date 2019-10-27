package com.android.bootcampbatch217.menus

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    val context=this

    //id beda tiap activity
    val ID_PEMANGGIL=2
    val ID_PERMISSION_GALERY=22

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        buttonGallery.setOnClickListener {
            if(checkPermissionCamera()){
                ambilImageDariGalery()
            }

        }
    }

    fun checkPermissionCamera():Boolean{
        val currentAPIVersion:Int = Build.VERSION.SDK_INT //check versi android OS
        if(currentAPIVersion>= Build.VERSION_CODES.M){
            //runtime permission
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                //belum dapat ijin
                requestPermissions(Array<String>(1){android.Manifest.permission.READ_EXTERNAL_STORAGE},ID_PERMISSION_GALERY)
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
        if(requestCode==ID_PERMISSION_GALERY){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //diijinkan
                ambilImageDariGalery()
            }else{
                //tidak diijinkan
                Toast.makeText(context,"Anda harus beri ijin akses untuk menggunakan fitur ini",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun ambilImageDariGalery(){
        val intentGalery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intentGalery.setType("image/*")
            intentGalery.setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(Intent.createChooser(intentGalery,"Pilih Photo"),ID_PEMANGGIL)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==ID_PEMANGGIL && resultCode== Activity.RESULT_OK){
            showImageFromGalery(data)
        }else{
            //

        }
    }

    fun showImageFromGalery(data:Intent?){
        var bitmap: Bitmap?=null

        data?.let {
            bitmap= MediaStore.Images.Media.getBitmap(context.contentResolver,data.data)

            //set image dengan glide
            Glide.with(context).load(bitmap).into(previewPhotoFromGallery)
        }
    }
}

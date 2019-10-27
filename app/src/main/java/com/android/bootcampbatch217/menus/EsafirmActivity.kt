package com.android.bootcampbatch217.menus

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.bootcampbatch217.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.esafirm.imagepicker.features.ImagePicker
import kotlinx.android.synthetic.main.activity_esafirm.*

class EsafirmActivity : AppCompatActivity() {
    val context = this
    val REQUEST_FOR_CAMERA= 3
    val REQUEST_FOR_GALERY=4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esafirm)

        buttonEsafirmCamera.setOnClickListener {
            cameraMenggunakanEsafirm()
        }

        buttonEsafirmGalery.setOnClickListener {
            galeryMenggunakanEsafirm()
        }
    }

    fun cameraMenggunakanEsafirm(){
        ImagePicker.cameraOnly().start(context,REQUEST_FOR_CAMERA)
    }

    fun galeryMenggunakanEsafirm(){
        ImagePicker.create(context)
                    .multi()
                    .limit(5)
                    .folderMode(true)
                    .start( REQUEST_FOR_GALERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQUEST_FOR_CAMERA){
            var image = ImagePicker.getFirstImageOrNull(data)
            image?.let {
                var options = BitmapFactory.Options()
                var bitmap=BitmapFactory.decodeFile(image.path,options)

                Glide.with(context).load(bitmap).into(previewImageEsafirmCamera)
            }
        }else if(requestCode==REQUEST_FOR_GALERY){
            var images: List<com.esafirm.imagepicker.model.Image> = ImagePicker.getImages(data)

            for(image in images){
                image?.let {
                    var options = BitmapFactory.Options()
                    var bitmap = BitmapFactory.decodeFile(image.path,options)

                    val imageView = ImageView(context)
                    Glide.with(context)
                            .load(bitmap)
                            .apply(RequestOptions().override(700,700))
                            .into(imageView)
                    sliderImageEsafirm.addView(imageView)
                }
            }

        }
    }
}

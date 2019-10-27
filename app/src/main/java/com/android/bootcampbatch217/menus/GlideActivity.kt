package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bootcampbatch217.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_glide.*

class GlideActivity : AppCompatActivity() {
    val context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        buttonLoadImageGlide.setOnClickListener {
            loadImageDenganGlide()
        }
    }

    fun loadImageDenganGlide(){
        val urlImage="https://atlas-content-cdn.pixelsquid.com/stock-images/realistic-eye-yellow-eyes-q1zK46E-600.jpg"

        //basic used
        //Glide.with(context).load(urlImage).into(previewImageGlide)

        //ekstra
        Glide.with(context)
            .load(urlImage)
            .thumbnail(0.1f)
            .into(previewImageGlide)


        //tips set image to xml using glide, could reduce resources
        //Glide.with(context)
        //  .load(R.drawable.icon_profile)
        //  .thumbnail(0.1f)
        //  .into(previewImageGlide)
    }
}

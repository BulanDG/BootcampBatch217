package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bootcampbatch217.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {
    val context= this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        buttonLoadImagePicasso.setOnClickListener {
            loadImageDenganPicasso()
        }
    }

    fun loadImageDenganPicasso(){
        val urlImage="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Blue_rose-artificially_coloured.jpg/220px-Blue_rose-artificially_coloured.jpg"
        // Picasso.get().load(urlImage).into(previewImagePicasso)

        //Image With Placeholder
        Picasso.get()
            .load(urlImage)
            .placeholder(R.drawable.icon_profile)
            .error(R.drawable.icon_error)
            .into(previewImagePicasso)
    }
}

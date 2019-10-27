package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.utilities.PHOTO_MODEL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_photo.*

class DetailPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_photo)
        val model=PHOTO_MODEL

        albumId.text=model!!.albumId.toString()
        photoId.text=model!!.id.toString()
        detailPhotoTitle.text=model!!.title

        Picasso.get().load(model.url).into(detailPhotoView)
    }
}

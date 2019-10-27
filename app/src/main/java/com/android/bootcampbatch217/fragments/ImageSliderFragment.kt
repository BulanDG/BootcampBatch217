package com.android.bootcampbatch217.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.bootcampbatch217.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.picasso.Picasso
import com.stfalcon.frescoimageviewer.ImageViewer

class ImageSliderFragment(val urlImage:String): Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val customView = inflater.inflate(R.layout.fragment_image_slider,container,false)

        //set image
        val imageView= customView.findViewById(R.id.imageSliderItem) as ImageView
        Picasso.get().load(urlImage).into(imageView)

        //action click image
        imageView.setOnClickListener{view->
            //initialize Fresco
            Fresco.initialize(view.context)

            //zoom image
            var urlImages=ArrayList<String>()
                urlImages.add(urlImage)
            ImageViewer.Builder(view.context,urlImages).setStartPosition(0).show()

        }
        return customView
    }

}
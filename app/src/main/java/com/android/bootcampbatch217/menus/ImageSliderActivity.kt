package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.ImageSliderAdapter
import com.android.bootcampbatch217.fragments.ImageSliderFragment
import kotlinx.android.synthetic.main.activity_image_slider.*

class ImageSliderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)

        val arrayImage= arrayOf(
            "https://i.ytimg.com/vi/d2-hLa4-cdg/maxresdefault.jpg",
            "https://pbs.twimg.com/media/BP9tyLyCUAAb55n.jpg",
            "http://pngimg.com/uploads/minions/minions_PNG57.png",
            "https://p7.hiclipart.com/preview/50/36/951/bob-the-minion-kevin-the-minion-minions-clip-art-minions.jpg",
            "https://data.whicdn.com/images/30148200/original.jpg",
            "https://i.pinimg.com/originals/54/4a/cd/544acd6390811f051377946dbd397313.jpg",
            "https://i.pinimg.com/originals/b9/56/58/b95658e6d57a2b215add964458784da9.jpg",
            "https://i.pinimg.com/originals/34/4c/fe/344cfe315d0e8b2b1abceb161334c8fb.jpg",
            "https://www.trzcacak.rs/myfile/detail/242-2424520_female-girl-minions-png-girl-minion.png",
            "https://cdn.imgbin.com/23/7/1/imgbin-bob-the-minion-stuart-the-minion-girl-minions-minions-minion-pb5wXFf8My5PmS2gjkM2vDH6u.jpg"
        )

        //circle list imagesliderfragment
        var imageSliderFragments= ArrayList<ImageSliderFragment>()
        arrayImage.forEach { urlImage->
            val imageFragment = ImageSliderFragment(urlImage)
            imageSliderFragments.add(imageFragment)
        }

        //set image slider
        val adapterImageSlider = ImageSliderAdapter(supportFragmentManager,imageSliderFragments)
        imageViewPager.adapter = adapterImageSlider

        //circle indicator
        circleIndicator.setViewPager(imageViewPager)
    }
}

package com.android.bootcampbatch217.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.bootcampbatch217.fragments.ImageSliderFragment

class ImageSliderAdapter(fm: FragmentManager,
                         val listImageFragments: List<ImageSliderFragment>) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return listImageFragments[position]
    }

    override fun getCount(): Int {
        return listImageFragments.size
    }

}
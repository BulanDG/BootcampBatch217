package com.android.bootcampbatch217.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.bootcampbatch217.fragments.FragmentDua
import com.android.bootcampbatch217.fragments.FragmentSatu
import com.android.bootcampbatch217.fragments.FragmentTiga

class TabLayoutAdapter(context: Context, fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->FragmentSatu()
            1->FragmentDua()
            2->FragmentTiga()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        //ada tiga tab
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Tab 1"
            1->"Tab 2"
            2->"Tab 3"
            else->""
        }
    }

}
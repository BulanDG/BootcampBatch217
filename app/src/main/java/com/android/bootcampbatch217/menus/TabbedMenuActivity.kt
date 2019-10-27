package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.TabLayoutAdapter
import kotlinx.android.synthetic.main.activity_tabbed_menu.*

class TabbedMenuActivity : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed_menu)

        val adapterTabbedMenu = TabLayoutAdapter(context,supportFragmentManager)
        viewPager.adapter=adapterTabbedMenu

        slidingTabs.setupWithViewPager(viewPager)
    }
}

package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.CustomListAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    val context=this
    val MERK_MOBIL = arrayOf("Honda","Ferari","Ford","Suzuki","Toyota","BMW")
    val DESKRIPSI_MOBIL= arrayOf(
        "Mobil Honda Jazz disukasi semua orang",
        "Mobil Ferari",
        "Mobil Ford",
        "Mobil Suzuki",
        "Mobil Toyota",
        "Mobil BMW")

    val ICON_MOBIL= intArrayOf(
        R.drawable.logo_honda,
        R.drawable.logo_ferari,
        R.drawable.logo_ford,
        R.drawable.logo_suzuki,
        R.drawable.logo_toyota,
        R.drawable.logo_bmw
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        //isiListDefault()
         isiListCustom()
    }

    fun isiListDefault(){
        val adapter= ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,MERK_MOBIL)
        listMerkMobil.adapter=adapter
        listMerkMobil.setOnItemClickListener(this@ListViewActivity)
    }

    fun isiListCustom(){
        val adapter= CustomListAdapter(context,MERK_MOBIL,DESKRIPSI_MOBIL,ICON_MOBIL)
        listMerkMobil.adapter=adapter
    }

    //add fungsi click @item, dengan meng extend AdapterView.OnItemClickListener
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //fungsi
        Toast.makeText(context,"Anda mengklik $position ${MERK_MOBIL[position]}",
            Toast.LENGTH_SHORT).show()
    }
}



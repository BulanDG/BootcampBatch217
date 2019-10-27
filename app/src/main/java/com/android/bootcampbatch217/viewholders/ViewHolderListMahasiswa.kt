package com.android.bootcampbatch217.viewholders

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R

class ViewHolderListMahasiswa(itemView: View):RecyclerView.ViewHolder(itemView) {
    var listMahasiswa:LinearLayout
    var namaMahasiswa:TextView

    init {
        listMahasiswa=itemView.findViewById(R.id.listItemMahasiswa) as LinearLayout
        namaMahasiswa=itemView.findViewById(R.id.itemNamaMahasiswa) as TextView

    }

    fun setNamaMahasiswa(nama:String?,index:Int){
        namaMahasiswa.text=nama

        if(index%2==0){
            listMahasiswa.setBackgroundColor(Color.GRAY)
        }else{
            listMahasiswa.setBackgroundColor(Color.GREEN)
        }

    }

}
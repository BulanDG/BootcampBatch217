package com.android.bootcampbatch217.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R

class CustomListAdapter(val context: Context,
                        val MERK_MOBIL: Array<String>,
                        val DESKRIPSI_MOBIL: Array<String>,
                        val ICON_MOBIL: IntArray):BaseAdapter() {

    //custom view list
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView=convertView

        //inflater ->mengganti layout default dengan custom
        val inflater=context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //viewholder berfungsi hold tiap2 item dalam layout custom agar memudahkan custom action @item
        val holder:ViewHolder
        if(myConvertView==null){
            holder=ViewHolder()
            myConvertView=inflater.inflate(R.layout.custom_list_layout,null)

            //inisialisasi element list item custom
            holder.titleList=myConvertView!!.findViewById(R.id.titleList) as TextView
            holder.descriptionList=myConvertView!!.findViewById(R.id.descriptionList) as TextView
            holder.iconList=myConvertView!!.findViewById(R.id.iconList) as ImageView

            holder.customListItem=myConvertView!!.findViewById(R.id.customListItem) as LinearLayout
        }else{
            holder= myConvertView.tag as ViewHolder

        }

        //set value
        holder.titleList!!.text=MERK_MOBIL[position]
        holder.descriptionList!!.text=DESKRIPSI_MOBIL[position]
        holder.iconList!!.setImageResource(ICON_MOBIL[position])

        // set action listener
        holder.customListItem!!.setOnClickListener{
           Toast.makeText(context,"List item ke $position=${MERK_MOBIL[position]}",Toast.LENGTH_SHORT).show()
        }

        //ekstra beda warna ganjil & genap
        if(position%2==0){
            holder.customListItem!!.setBackgroundColor(Color.GRAY)
        }else{
            holder.customListItem!!.setBackgroundColor(Color.CYAN)
        }


        myConvertView.tag=holder
        return myConvertView
    }

    override fun getItem(position: Int): Any? {
      return null
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    //fungsi hitung jumlah Item list
    override fun getCount(): Int {
       return MERK_MOBIL.size
    }

    internal inner class ViewHolder{
        var iconList:ImageView?=null
        var titleList:TextView?=null
        var descriptionList:TextView?=null

        var customListItem: LinearLayout?=null
    }
}
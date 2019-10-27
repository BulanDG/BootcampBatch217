package com.android.bootcampbatch217.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.menus.DetailPhotoActivity
import com.android.bootcampbatch217.models.PhotoModel
import com.android.bootcampbatch217.utilities.PHOTO_MODEL
import com.bumptech.glide.Glide

class ListPhotoAdapter(val context: Context,
                       val photoModels: ArrayList<PhotoModel>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView

        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder: ViewHolderListPhoto
        if(myConvertView==null){
            holder = ViewHolderListPhoto()
            myConvertView = inflater.inflate(R.layout.list_photo_layout, null)

        //inisialisasi elemen view holder
            holder.thumbnailPhoto = myConvertView!!.findViewById(R.id.thumbnailPhoto) as ImageView
            holder.titlePhoto = myConvertView!!.findViewById(R.id.titlePhoto) as TextView
        }else{
            holder = myConvertView.tag as ViewHolderListPhoto
        }

        //set value
        holder.titlePhoto!!.text = photoModels[position].title

        var urlThumbnail = photoModels[position].thumbnailUrl
        Glide.with(context).load(urlThumbnail).into(holder.thumbnailPhoto!!)

        //listener u/k thumbnail photo
        holder.thumbnailPhoto!!.setOnClickListener{
            val intent=Intent(context,DetailPhotoActivity::class.java)
            PHOTO_MODEL=photoModels[position]
            context.startActivity(intent)
        }

        myConvertView.tag = holder
        return myConvertView
    }

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return photoModels.size
    }

    inner class ViewHolderListPhoto{
        var titlePhoto: TextView? = null
        var thumbnailPhoto: ImageView? = null
    }


}
package com.android.bootcampbatch217.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.models.UserModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ListUserAdapter(val context: Context,val userModels:ArrayList<UserModel>):BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView=convertView

        //inflater ->mengganti layout default dengan custom
        val inflater=context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //viewholder berfungsi hold tiap2 item dalam layout custom agar memudahkan custom action @item
        var holder: ViewHolderListUser

        if(myConvertView==null){
            holder= ViewHolderListUser()
            myConvertView=inflater.inflate(R.layout.list_user_layout,null)

            //inisialisasi element view holder
            holder.avatar=myConvertView!!.findViewById(R.id.avatar)
            holder.full_name=myConvertView!!.findViewById(R.id.fullname)
            holder.email=myConvertView!!.findViewById(R.id.email)
            holder.id=myConvertView!!.findViewById(R.id.id)
        }else{
           holder=myConvertView.tag as ViewHolderListUser

        }

        //set value element view jolder


        val valueFullName=userModels[position].first_name+" "+userModels[position].last_name
        holder.full_name!!.text=valueFullName

        val valueEmail=userModels[position].email
        holder.email!!.text=valueEmail

        val valueId=userModels[position].id.toString()
        holder.id!!.text=valueId

        val urlAvatar=userModels[position].avatar

        /*
        val requestImage = ImageRequest(
            urlAvatar,
            Response.Listener{ response ->
                holder.avatar!!.setImageBitmap(response)
            },
            0,
            0,
            ImageView.ScaleType.FIT_XY,
            Bitmap.Config.ARGB_8888,
            Response.ErrorListener { error ->
                Toast.makeText(context, "Error load image : ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        Volley.newRequestQueue(context).add(requestImage)
        */
        Picasso.get()
            .load(urlAvatar)
            .placeholder(R.drawable.icon_profile)
            .error(R.drawable.icon_error)
            .into(holder.avatar!!)

        //click listener
        myConvertView.tag=holder
        return myConvertView
    }

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        userModels?.let {
            return userModels.size
        }
        return 0
    }

    inner class ViewHolderListUser(){
        var avatar:ImageView?=null
        var full_name:TextView?=null
        var email:TextView?=null
        var id:TextView?=null
    }

}
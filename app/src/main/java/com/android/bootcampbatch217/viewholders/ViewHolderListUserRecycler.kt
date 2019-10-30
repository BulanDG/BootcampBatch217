package com.android.bootcampbatch217.viewholders


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.models.listuser.Data
import com.squareup.picasso.Picasso

class ViewHolderListUserRecycler(itemView: View):RecyclerView.ViewHolder(itemView) {
    val avatar=itemView.findViewById(R.id.avatar)  as ImageView
    val fullname=itemView.findViewById(R.id.fullname) as TextView
    val email=itemView.findViewById(R.id.email) as TextView
    val id=itemView.findViewById(R.id.id) as TextView

    fun setModel(model: Data, position: Int){
        fullname.text=model.first_name+" "+model.last_name
        email.text=model.email
        id.text=model.id.toString()
        Picasso.get().load(model.avatar).into(avatar)
    }
}
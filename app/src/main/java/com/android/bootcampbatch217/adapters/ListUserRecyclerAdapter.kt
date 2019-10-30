package com.android.bootcampbatch217.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.models.listuser.Data
import com.android.bootcampbatch217.viewholders.ViewHolderListUserRecycler
import com.google.android.material.snackbar.Snackbar

class ListUserRecyclerAdapter(val context: Context,
                              val listUserModels:ArrayList<Data>):RecyclerView.Adapter<ViewHolderListUserRecycler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListUserRecycler {
        val customLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.list_user_recycler,
            parent,
            false
        )
        return ViewHolderListUserRecycler(customLayout)
    }

    override fun getItemCount(): Int {
        return listUserModels.size
    }

    override fun onBindViewHolder(holder: ViewHolderListUserRecycler, position: Int) {
        val model=listUserModels[position]
        holder.setModel(model,position)

        //menambahkan listener onclick di button, jika ingin di list, set onclick di linearlayout
        holder.avatar.setOnClickListener{view->
            Snackbar.make(view,
                "${model.first_name} ${model.last_name}",
                Snackbar.LENGTH_LONG).show()
        }
    }

}
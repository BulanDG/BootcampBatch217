package com.android.bootcampbatch217.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.models.Buku
import com.android.bootcampbatch217.viewholders.ViewHolderListBuku

class ListBukuAdapter(val context: Context, val listBuku :List<Buku>):RecyclerView.Adapter<ViewHolderListBuku>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListBuku {
        val customView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_list_buku_layout,parent,false)

        return  ViewHolderListBuku(customView)
    }

    override fun getItemCount(): Int {
        return listBuku.size
    }

    override fun onBindViewHolder(holder: ViewHolderListBuku, position: Int) {
        holder.setBuku(listBuku[position])


    }
}
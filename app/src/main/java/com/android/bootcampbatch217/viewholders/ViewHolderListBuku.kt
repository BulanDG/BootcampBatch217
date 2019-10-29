package com.android.bootcampbatch217.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.models.Buku
import kotlinx.android.synthetic.main.custom_list_buku_layout.view.*

class ViewHolderListBuku(itemView:View) :RecyclerView.ViewHolder(itemView){

    val judulBuku:TextView
    val kategoriBuku:TextView
    val pengarangBuku:TextView
    val penerbitBuku:TextView
    val stokBuku:TextView
    val hargaBuku:TextView
    val iconEdit :ImageView
    val iconDelete:ImageView

    init {
        judulBuku = itemView.findViewById(R.id.judulBuku)
        kategoriBuku = itemView.findViewById(R.id.kategoriBuku)
        pengarangBuku = itemView.findViewById(R.id.pengarangBuku)
        stokBuku = itemView.findViewById(R.id.stokBuku)
        penerbitBuku = itemView.findViewById(R.id.penerbitBuku)
        hargaBuku = itemView.findViewById(R.id.hargaBuku)
        iconEdit = itemView.findViewById(R.id.iconEdit)
        iconDelete = itemView.findViewById(R.id.iconDelete)
    }

    fun setBuku(buku:Buku){
        judulBuku.text=buku.judul_buku
        kategoriBuku.text=buku.kategori_buku
        pengarangBuku.text=buku.pengarang_buku
        penerbitBuku.text=buku.penerbit_buku
        stokBuku.text=buku.stok.toString()
        hargaBuku.text=buku.harga.toString()
    }

}
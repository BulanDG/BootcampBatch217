package com.android.bootcampbatch217.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.mahasiswa.DetailMahasiswaActivity
import com.android.bootcampbatch217.mahasiswa.EditDataMahasiswaActivity
import com.android.bootcampbatch217.viewholders.ViewHolderListMahasiswa

class ListMahasiswaAdapter(val context: Context, val namaMahasiswa:ArrayList<String>, val idMahasiswa:ArrayList<Int>): RecyclerView.Adapter<ViewHolderListMahasiswa>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListMahasiswa {
        var customView=LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_list_mahasiswa_layout,parent,false)

        return ViewHolderListMahasiswa(customView)
    }

    override fun getItemCount(): Int {
        return namaMahasiswa.size
    }

    override fun onBindViewHolder(holder: ViewHolderListMahasiswa, position: Int) {

        holder.setNamaMahasiswa(namaMahasiswa[position],position)

        //listener jika list item diklik
        holder.listMahasiswa.setOnClickListener{
            val id = idMahasiswa[position]
            tampilkanDialogPilihanAksi(id)
        }
    }

    fun tampilkanDialogPilihanAksi(id:Int){
        //buat list pilihan
        val listPilihan = arrayOf<String>("Lihat Detail Mahasiswa", "Update Data Mahasiswa","Delete Data Mahasiswa")

        val options= AlertDialog.Builder(context)
        options.setTitle("Pilih Aksi ID=$id")
        options.setItems(listPilihan){ dialog, which ->
            when(which){
                0->{
                    //lihat detail
                    val intentDetail= Intent(context, DetailMahasiswaActivity::class.java)
                    intentDetail.putExtra("ID",id)
                    context.startActivity(intentDetail)

                }
                1->{
                    //edit data
                    val intentDetail= Intent(context, EditDataMahasiswaActivity::class.java)
                    intentDetail.putExtra("ID",id)
                    context.startActivity(intentDetail)
                }
                2->{
                    //delete data

                }
            }

        }
        options.create().show()
    }

}
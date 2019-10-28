package com.android.bootcampbatch217.adapters

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.databases.DatabaseMahasiswaHelper
import com.android.bootcampbatch217.mahasiswa.DaftarMahasiswaActivity
import com.android.bootcampbatch217.mahasiswa.DetailMahasiswaActivity
import com.android.bootcampbatch217.mahasiswa.EditDataMahasiswaActivity
import com.android.bootcampbatch217.utilities.ID
import com.android.bootcampbatch217.utilities.TABEL_BIODATA
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
                    intentDetail.putExtra(ID,id)
                    context.startActivity(intentDetail)
                }
                1->{
                    //edit data
                    val intentDetail= Intent(context, EditDataMahasiswaActivity::class.java)
                    intentDetail.putExtra(ID,id)
                    context.startActivity(intentDetail)
                }
                2->{
                    val konfirmasiDelete= AlertDialog.Builder(context)
                        konfirmasiDelete.setMessage("Yakin mau menghapus dta ini?")
                            .setPositiveButton("Ya",DialogInterface.OnClickListener { dialog, which ->
                                //delete data
                                val databaseHelper = DatabaseMahasiswaHelper(context)
                                val db= databaseHelper.writableDatabase
                                val queryDelete = "DELETE FROM $TABEL_BIODATA WHERE $ID=$id"
                                db.execSQL(queryDelete)

                                //memanggil fungsi refresh list yg ada di DaftarMahasiswaActivity
                                val dma=context as DaftarMahasiswaActivity
                                dma.refreshList()
                            })
                            .setNegativeButton("Tidak",DialogInterface.OnClickListener { dialog, which ->
                                dialog.cancel()
                            })
                            .setCancelable(true)

                    konfirmasiDelete.create().show()


                }
            }

        }
        options.create().show()
    }

}
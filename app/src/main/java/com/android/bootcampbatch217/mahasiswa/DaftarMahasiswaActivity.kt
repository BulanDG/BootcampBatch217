package com.android.bootcampbatch217.mahasiswa

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.ListMahasiswaAdapter
import com.android.bootcampbatch217.databases.DatabaseMahasiswaHelper
import com.android.bootcampbatch217.menus.EsafirmActivity
import com.android.bootcampbatch217.utilities.*
import kotlinx.android.synthetic.main.activity_daftar_mahasiswa.*

class DaftarMahasiswaActivity : AppCompatActivity() {
    val context=this
    val databaseHelper = DatabaseMahasiswaHelper(context)

    var namaMahasiswa= ArrayList<String>()
    var idMahasiswa = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mahasiswa)

        buttonInputNamaMahasiswa.setOnClickListener {
            val intent= Intent(context, InputDataMahasiswaActivity::class.java)
            startActivity(intent)
        }

        //set layout manager utk recyclerview
        val layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        listNamaMahasiswa.layoutManager=layoutManager

        readDataMahasiswa()
    }

    fun readDataMahasiswa(){
        //inisiasi sqlite db baca data
        val db = databaseHelper.readableDatabase

        //baca data sqlite -> rawQuery
        val queryRead= "SELECT * FROM $TABEL_BIODATA"
        val cursor: Cursor = db.rawQuery(queryRead,null)

        if(cursor.count==0){
            Toast.makeText(context,"Data tidak ditemukan",Toast.LENGTH_SHORT).show()

        }else{
            for (c in 0 until cursor.count){
                cursor.moveToPosition(c)
                val nama=cursor.getString(2) //
                val id = cursor.getInt(0)

                namaMahasiswa.add(nama)
                idMahasiswa.add(id)
            }

            tampilkanDataMahasiswa()
        }
    }

    fun tampilkanDataMahasiswa(){
        val adapterListMahasiswa = ListMahasiswaAdapter(context,namaMahasiswa,idMahasiswa)
        listNamaMahasiswa.adapter =adapterListMahasiswa
        adapterListMahasiswa.notifyDataSetChanged()
    }

    fun refreshList(){
        println("Refresh List>>>>")
        namaMahasiswa=ArrayList<String>()
        idMahasiswa=ArrayList<Int>()

        readDataMahasiswa()
    }

    override fun onResume() {
        super.onResume()

        refreshList()
    }
}

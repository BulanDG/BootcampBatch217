package com.android.bootcampbatch217.perpustakaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.databases.DatabasePerpustakaanHelper
import kotlinx.android.synthetic.main.activity_perpustakaan.*

class PerpustakaanActivity : AppCompatActivity() {
    val context =this
    val databaseHelper =DatabasePerpustakaanHelper(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perpustakaan)

        buttonImportDatabse.setOnClickListener {
            databaseHelper.createDatabaseFromImportedFile()
            buttonTambahBuku.visibility = View.VISIBLE
            buttonTampilkanBuku.visibility= View.VISIBLE
            buttonImportDatabse.visibility=View.GONE


        }

        buttonTampilkanBuku.setOnClickListener {
            val intent= Intent(context,ListBukuActivity::class.java)
            startActivity(intent)
        }

        buttonTambahBuku.setOnClickListener {
            val intent= Intent(context,TambahBukuActivity::class.java)
            startActivity(intent)
        }

        cekDataBase()
    }

    fun cekDataBase(){
        if(databaseHelper.checkDatabase()){
            buttonTambahBuku.visibility=View.VISIBLE
            buttonTampilkanBuku.visibility=View.VISIBLE
            buttonImportDatabse.visibility=View.GONE
        }
    }
}

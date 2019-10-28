package com.android.bootcampbatch217.perpustakaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.databases.DatabasePerpustakaanHelper
import com.android.bootcampbatch217.databases.DatabasePerpustakaanQueryHelper
import kotlinx.android.synthetic.main.activity_list_buku.*

class ListBukuActivity : AppCompatActivity() {
    val context=this
    val databaseHelper = DatabasePerpustakaanHelper(context)
     val databaseQueryHelper = DatabasePerpustakaanQueryHelper(databaseHelper)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_buku)
        tampilkanSemuaBuku()
    }

    fun tampilkanSemuaBuku(){
        val listBuku= databaseQueryHelper.readSemuaBukuModels()
        totalBuku.text=listBuku.size.toString()
    }
}

package com.android.bootcampbatch217.mahasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.databases.DatabaseMahasiswaHelper
import com.android.bootcampbatch217.utilities.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_mahasiswa.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailMahasiswaActivity : AppCompatActivity() {
    val context = this
    val databaseHelper= DatabaseMahasiswaHelper(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mahasiswa)

        //tangkap intent extra
        val bundle:Bundle?=intent.extras
        bundle?.let {
            val id = bundle!!.getInt(ID)
            readDetailMahasiswa(id)
        }

    }

    fun readDetailMahasiswa(id:Int){
        val db= databaseHelper.readableDatabase

        //baca data ->rawquery

//        val queryRead="SELECT * FROM '$TABEL_BIODATA' WHERE $ID=$id"
//        val cursor=db.rawQuery(queryRead,null)

        //baca data ->projection
        val projection = arrayOf<String>(ID, NIM,
            NAMA, GENDER, TANGGAL_LAHIR, ALAMAT, JURUSAN, PATH_FOTO
        )
        val cursor= db.query(TABEL_BIODATA,projection,null,null,null,null,null)
        if(cursor!!.count==1){
            cursor.moveToFirst()
            labelNamaMahasiswa.text = cursor.getString(2)
            labelNIMMahasiswa.text=cursor.getInt(1).toString()
            labelTanggalLahir.text=cursor.getString(4)
            labelGenderMahasisw.text=cursor.getString(3)
            labelJurusanMahasiswa.text=cursor.getString(6)


            val imagePath = cursor.getString(7)
            Glide.with(context).load(imagePath).into(fotoProfilMahasiswa)

        }else{
            Toast.makeText(context,"Data mahasiswa tidak ditemukan!",Toast.LENGTH_SHORT)
        }

    }
}

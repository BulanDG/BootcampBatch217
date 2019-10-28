package com.android.bootcampbatch217.mahasiswa

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.databases.DatabaseMahasiswaHelper
import com.android.bootcampbatch217.utilities.*
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import kotlinx.android.synthetic.main.activity_input_data_mahasiswa.*
import java.text.SimpleDateFormat
import java.util.*

class InputDataMahasiswaActivity : AppCompatActivity() {

    val context =this
    var PHOTO_PATH=""

    //    var databaseHelper: DatabaseMahasiswaHelper? = null
    var databaseHelper= DatabaseMahasiswaHelper(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data_mahasiswa)

        isiSpinnerJurusan()
        setBirthdayPicker()

        inputPhotoMahasiswa.setOnClickListener{
            ambilFotoProfil()
        }

        buttonSimpanDataMahasiswa.setOnClickListener {
            validasiInputDataMahasiswa()
        }

    }

    fun validasiInputDataMahasiswa(){
        val nim =inputNomorInduk.text.toString().trim()
        val namaLengkap= inputNamaMahasiswa.text.toString().trim()
        val tanggalLahir=tanggalLahirMahasiswa.text.toString()
        val gender = inputGenderMahasiswa.text.toString()
        val foto = PHOTO_PATH
        val alamat = inputAlamatMahasiswa.text.toString().trim()
        val jurusan= inputJurusanMahasiswa.selectedItemPosition //index selection


        if(nim.equals("")){
            Toast.makeText(context,"Anda belum mengisi nim",Toast.LENGTH_SHORT).show()
        }
        else if(namaLengkap.equals("")){
            Toast.makeText(context,"Anda belum mengisi nama lengkap",Toast.LENGTH_SHORT).show()
        }
        else if(tanggalLahir.equals("")){
            Toast.makeText(context,"Anda belum mengisi tanggal lahir",Toast.LENGTH_SHORT).show()
        }
        else if(gender.equals("")){
            Toast.makeText(context,"Anda belum mengisi gender",Toast.LENGTH_SHORT).show()
        }
        else if(foto.equals("")){
            Toast.makeText(context,"Anda belum memilih foto",Toast.LENGTH_SHORT).show()
        }
        else if(alamat.equals("")){
            Toast.makeText(context,"Anda belum mengisi alamat",Toast.LENGTH_SHORT).show()
        }
        else if(jurusan==0){
            Toast.makeText(context,"Anda belum mengisi jurusan",Toast.LENGTH_SHORT).show()
        }
        else{
            insertKeDatabaseMahasiswa(Integer.parseInt(nim),
            namaLengkap,
            gender,
            tanggalLahir,
            alamat,
            ARRAY_JURUSAN[jurusan],
            foto)
        }
    }

    fun insertKeDatabaseMahasiswa(nim:Int,
                                  namaLengkap:String,
                                  gender:String,
                                  tanggalLahir:String,
                                  alamat:String,
                                  jurusan:String,
                                  foto:String){

        //dg cara content values
        val content = ContentValues()
        content.put(NIM,nim)
        content.put(NAMA,namaLengkap)
        content.put(GENDER,gender)
        content.put(TANGGAL_LAHIR,tanggalLahir)
        content.put(ALAMAT,alamat)
        content.put(JURUSAN,jurusan)
        content.put(PATH_FOTO,foto)

        //insert
        val db =databaseHelper!!.writableDatabase
        db.insert(TABEL_BIODATA,null,content)
        Toast.makeText(context,"Data sudah disimpan!!",Toast.LENGTH_SHORT).show()
        finish()
    }


    fun ambilFotoProfil(){
        ImagePicker.create(this@InputDataMahasiswaActivity)
            .single()
            .limit(1)
            .folderMode(true)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(ImagePicker.shouldHandle(requestCode,resultCode,data)){
            val foto=ImagePicker.getFirstImageOrNull(data)
            foto?.let {
                val options=BitmapFactory.Options()
                val bitmap = BitmapFactory.decodeFile(foto!!.path,options)

                Glide.with(context).load(bitmap).into(inputPhotoMahasiswa)
                PHOTO_PATH=foto!!.path
            }
        }
    }

    fun isiSpinnerJurusan(){
        val adapterJurusan=ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,
            ARRAY_JURUSAN)

        adapterJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inputJurusanMahasiswa.adapter = adapterJurusan
    }

    fun setBirthdayPicker(){
        val today = Calendar.getInstance()
        val yearNow = today.get(Calendar.YEAR)
        val monthNow = today.get(Calendar.MONTH)
        val dayNow = today.get(Calendar.DATE)

        tanggalLahirMahasiswa.setOnClickListener{
            val datePickerDialog = DatePickerDialog(context, R.style.CustomDatePicker,
                DatePickerDialog.OnDateSetListener{view,year,month,dayofMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year,month,dayofMonth)

                    //konversi ke string
                    val formatDate = SimpleDateFormat("dd/MM/yyyy")
                    val tanggal = formatDate.format(selectedDate.time)
                    tanggalLahirMahasiswa.text = tanggal
                },
                yearNow,
                monthNow,
                dayNow
            )
            datePickerDialog.show()
        }

    }
}

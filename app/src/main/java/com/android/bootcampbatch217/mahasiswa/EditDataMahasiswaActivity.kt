package com.android.bootcampbatch217.mahasiswa

import android.app.DatePickerDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.databases.DatabaseMahasiswaHelper
import com.android.bootcampbatch217.utilities.*
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.*
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.inputAlamatMahasiswa
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.inputGenderMahasiswa
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.inputJurusanMahasiswa
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.inputNamaMahasiswa
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.inputNomorInduk
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.inputPhotoMahasiswa
import kotlinx.android.synthetic.main.activity_edit_data_mahasiswa.tanggalLahirMahasiswa
import java.text.SimpleDateFormat
import java.util.*

class EditDataMahasiswaActivity : AppCompatActivity() {
    val context=this
    val databaseHelper= DatabaseMahasiswaHelper(context)
    var ID_BIODATA=0
    var imagePath:String= ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data_mahasiswa)

        isiSpinnerJurusan()

        //step 1 load detail data
        //tangkap intent extra
        val bundle:Bundle?=intent.extras
        bundle?.let {
            val id = bundle!!.getInt(ID)
            ID_BIODATA=id
            loadDataMahasiswa(id)
        }

        //ambil poto profil
        inputPhotoMahasiswa.setOnClickListener{
            ambilFotoProfil()
        }

        //step 2 validasi input
        buttonUpadateDataMahasiswa.setOnClickListener {
            validasiInputDataMahasiswa(ID_BIODATA)
        }

        //step 3 update data

    }

    fun validasiInputDataMahasiswa(id:Int){
        val nim =inputNomorInduk.text.toString().trim()
        val namaLengkap= inputNamaMahasiswa.text.toString().trim()
        val tanggalLahir=tanggalLahirMahasiswa.text.toString()
        val gender = inputGenderMahasiswa.text.toString()
        val foto = imagePath
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
            updateKeDatabaseMahasiswa(id,Integer.parseInt(nim),
                namaLengkap,
                gender,
                tanggalLahir,
                alamat,
                ARRAY_JURUSAN[jurusan],
                imagePath)
        }
    }

    fun updateKeDatabaseMahasiswa(id:Int,nim:Int,
                                  namaLengkap:String,
                                  gender:String,
                                  tanggalLahir:String,
                                  alamat:String,
                                  jurusan:String,
                                  foto:String){

        //cara 1 rawquery
        val db =databaseHelper!!.writableDatabase

        /*
          val queryUpdate ="UPDATE $TABEL_BIODATA " +
                  "SET " +
                  "$NIM=$nim, " +
                  "$NAMA='$namaLengkap'," +
                  "$GENDER='$gender'," +
                  "$TANGGAL_LAHIR='$tanggalLahir'," +
                  "$ALAMAT='$alamat'," +
                  "$JURUSAN='$jurusan'," +
                  "$PATH_FOTO='$foto'" +
                  "WHERE $ID=$id"

          db.execSQL(queryUpdate)
      */

        //cara 2 content values
        val content = ContentValues()
        content.put(NIM,nim)
        content.put(NAMA,namaLengkap)
        content.put(GENDER,gender)
        content.put(TANGGAL_LAHIR,tanggalLahir)
        content.put(ALAMAT,alamat)
        content.put(JURUSAN,jurusan)
        content.put(PATH_FOTO,foto)

        db.update(TABEL_BIODATA,content,"$ID=?", arrayOf("$id"))

        finish()
    }

    fun ambilFotoProfil(){
        ImagePicker.create(this@EditDataMahasiswaActivity)
            .single()
            .limit(1)
            .folderMode(true)
            .start()
    }

    fun isiSpinnerJurusan(){
        val adapterJurusan= ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,
            ARRAY_JURUSAN)

        adapterJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inputJurusanMahasiswa.adapter = adapterJurusan
    }

    fun setBirthdayPicker(){
        //dd/MM/yyyy
        val today= tanggalLahirMahasiswa.text.toString()
        val dayNow = today.substring(0,2).toInt()
        val monthNow = today.substring(3,5).toInt()-1
        val yearNow = today.substring(6).toInt()

        tanggalLahirMahasiswa.setOnClickListener{
            val datePickerDialog = DatePickerDialog(context, R.style.CustomDatePicker,
                DatePickerDialog.OnDateSetListener{ view, year, month, dayofMonth ->
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

    fun loadDataMahasiswa(id: Int){
        val db = databaseHelper.readableDatabase

        //cara raw query CARA 1
        // val queryRead = "SELECT * FROM '$TABEL_BIODATA' WHERE $ID=$id"
        // val cursor = db.rawQuery(queryRead, null)

        //cara projection CARA 2
        val projection = arrayOf<String>(
            ID,
            NIM, NAMA, GENDER, TANGGAL_LAHIR, ALAMAT, JURUSAN, PATH_FOTO
        )
        val selection = ID + "=?"
        val selectionArgs= arrayOf(id.toString())
        val cursor= db.query(TABEL_BIODATA,projection,selection,selectionArgs,null,null,null)
        if(cursor!!.count==1){
            //data ditemukan
            cursor.moveToFirst()

            inputNomorInduk.setText(cursor.getInt(1).toString())
            inputNamaMahasiswa.setText(cursor.getString(2))
            inputGenderMahasiswa.setText(cursor.getString(3))
            tanggalLahirMahasiswa.text = cursor.getString(4)
            setBirthdayPicker()
            inputAlamatMahasiswa.setText(cursor.getString(5))

            inputJurusanMahasiswa.setSelection(ARRAY_JURUSAN.indexOf(cursor.getString(6)))

            imagePath = cursor.getString(7)
            Glide.with(context).load(imagePath).into(inputPhotoMahasiswa)
        }
        else{
            //data tidak ditemukan
            Toast.makeText(context, "Data Mahasiswa Tidak Ditemukan!", Toast.LENGTH_SHORT).show()
        }
    }
}

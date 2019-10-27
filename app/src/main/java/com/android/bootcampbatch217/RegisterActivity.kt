package com.android.bootcampbatch217

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.bootcampbatch217.utilities.ARRAY_HOBI
import com.android.bootcampbatch217.utilities.ARRAY_PEKERJAAN
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.tan

class RegisterActivity : AppCompatActivity() {
    val context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSpinnerData()
        tanggalLahir.setOnClickListener{
            pilihTanggalLahir()
        }

        checkAgreement()
        buttonRegister.setOnClickListener {
            validasiInput()
        }
    }

    fun validasiInput(){
        val namaLengkap=inputNamaLengkap.text.toString().trim()
        var alamatLengkap=inputAlamat.text.toString().trim()
        var umur = inputUmur.text.toString().trim()
        val nomorTelepon=inputNomorTelepon.text.toString().trim()
        val email= inputEmailAddress.text.toString().trim()

        val selectedGender= gender.checkedRadioButtonId

        val selectedPekerjaan=jenisPekerjaan.selectedItemPosition

        val selectedPendidikan=gelarPendidikan.selectedItemPosition

        val selectedHobi=hobiAnda.selectedItemPosition

        val selectedTanggalLahir=tanggalLahir.text.toString()

        //validasi email format
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+"

        //logic validasi
        if(namaLengkap.length==0){
            Toast.makeText(context,"Anda belum isi nama",Toast.LENGTH_SHORT).show()
        }
        else if(alamatLengkap.length==0){
            Toast.makeText(context,"Anda belum isi alamat",Toast.LENGTH_SHORT).show()
        }
        else if(umur.length==0){
            Toast.makeText(context,"Anda belum isi umur",Toast.LENGTH_SHORT).show()
        }
        else if(nomorTelepon.length==0){
            Toast.makeText(context,"Anda belum isi telepon",Toast.LENGTH_SHORT).show()
        }
        else if(email.length==0){
            Toast.makeText(context,"Anda belum isi email",Toast.LENGTH_SHORT).show()
        }
        else if(!email.matches(emailPattern.toRegex())){
            Toast.makeText(context,"Anda belum isi telepon",Toast.LENGTH_SHORT).show()
        }
        else if(selectedGender==-1){
            Toast.makeText(context,"Anda belum memilih gender",Toast.LENGTH_SHORT).show()
        }
        else if(selectedPekerjaan==0){
            Toast.makeText(context,"Anda belum memilih pekerjaan",Toast.LENGTH_SHORT).show()
        }
        else if(selectedPendidikan==0){
            Toast.makeText(context,"Anda belum memilih pendidikan",Toast.LENGTH_SHORT).show()
        }
        else if(selectedHobi==0){
            Toast.makeText(context,"Anda belum memilih hobi",Toast.LENGTH_SHORT).show()
        }
        else if(selectedTanggalLahir.length==0){
            Toast.makeText(context,"Anda belum memilih tanggal lahir",Toast.LENGTH_SHORT).show()
        }else{
            Snackbar.make(findViewById(R.id.contentAll)
                ,"Anda berhasil register",
                Snackbar.LENGTH_SHORT).show()
        }
    }

    fun checkAgreement(){
        buttonRegister.isEnabled=false
        agreementCheck.setOnClickListener{
            if(agreementCheck.isChecked){
                buttonRegister.isEnabled=true
            }else{
                buttonRegister.isEnabled=false

            }
        }
    }

    fun pilihTanggalLahir(){
        val today=Calendar.getInstance() //get today date
        val yearNow = today.get(Calendar.YEAR)
        val monthNow = today.get(Calendar.MONTH)
        val dayNow=today.get(Calendar.DATE)

        //date picker
        val datePicker= DatePickerDialog(context,R.style.CustomDatePicker,
            DatePickerDialog.OnDateSetListener{
                view,year,month,dayOfMonth->
                val selectedDate=Calendar.getInstance()
                selectedDate.set(year,month,dayOfMonth)

                //konversi ke String
                val formatter=SimpleDateFormat("dd/MM/yyyy")
                val tanggal= formatter.format(selectedDate.time)

                //set ke view
                tanggalLahir.text= tanggal
            },yearNow,monthNow,dayNow
        )

        datePicker.show()
    }

    fun setSpinnerData(){
        //cara 1 untuk spinner jenis pekerjaan -- menggunakan array constanta
        val adapterJenisPekerjaan= ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,
            ARRAY_PEKERJAAN)
        adapterJenisPekerjaan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jenisPekerjaan.adapter= adapterJenisPekerjaan

        //cara 2 untuk spinner pendidikan -- menggunakan values string
        val adapterGelarPendidikan= ArrayAdapter.createFromResource(context,
            R.array.gelar_pendidikan,android.R.layout.simple_spinner_item)
        gelarPendidikan.adapter=adapterGelarPendidikan

        //cara 3
        val adapterHobi= ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,
            ARRAY_HOBI)
        hobiAnda.adapter= adapterHobi


    }
}

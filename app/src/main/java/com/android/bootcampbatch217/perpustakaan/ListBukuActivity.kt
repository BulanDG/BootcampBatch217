package com.android.bootcampbatch217.perpustakaan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.ListBukuAdapter
import com.android.bootcampbatch217.databases.DatabasePerpustakaanHelper
import com.android.bootcampbatch217.databases.DatabasePerpustakaanQueryHelper
import com.android.bootcampbatch217.models.Buku
import kotlinx.android.synthetic.main.activity_list_buku.*

class ListBukuActivity : AppCompatActivity() {
    val context=this
    val databaseHelper = DatabasePerpustakaanHelper(context)
     val databaseQueryHelper = DatabasePerpustakaanQueryHelper(databaseHelper)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_buku)

        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        listBukuRecycler.layoutManager=layoutManager

        tampilkanSemuaBuku()

        buttonCariBuku.setOnClickListener {
            hideKeyboard()
            validasiKeyboard()
        }

        //tambahkan fungsi autosearch jika ada perubahan input text
        inputKeyboard.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count==0){
                    tampilkanSemuaBuku()
                }else{
                    validasiKeyboard()
                }
            }

        })

    }

    fun hideKeyboard(){
        val hide =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(currentFocus!!.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)

    }

    fun validasiKeyboard(){
        val keyword= inputKeyboard.text.toString().trim()
        if(keyword.equals("")){
            Toast.makeText(context,"Keyword tidak bole kosong!",Toast.LENGTH_SHORT).show()
        }else{
            val listBuku= databaseQueryHelper.cariBukuModels(keyword)
            tampilkanListBuku(listBuku)
        }
    }

    fun tampilkanSemuaBuku(){
        val listBuku= databaseQueryHelper.readSemuaBukuModels()
       tampilkanListBuku(listBuku)
    }

    fun tampilkanListBuku(listBuku:List<Buku>){
        totalBuku.text=listBuku.size.toString()

        val adapterBuku = ListBukuAdapter(context,listBuku)
        listBukuRecycler.adapter = adapterBuku
        adapterBuku.notifyDataSetChanged()

    }
}

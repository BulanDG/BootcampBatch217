package com.android.bootcampbatch217.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.room.entities.Word
import com.android.bootcampbatch217.room.viewmodels.WordViewModel
import kotlinx.android.synthetic.main.activity_room_word.*

class RoomWordActivity : AppCompatActivity() {
    val context =this
    val newWordActivityRequestCode = 1

    lateinit var wordViewModel : WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_word)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(context)

        recyclerView.adapter =adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //ambil viewmodel baru yg sudah ada dari viewmodel provider
        wordViewModel=ViewModelProvider(context).get(WordViewModel::class.java)

        //tambahkan observer
        wordViewModel.allWords.observe(context, Observer { word->
            adapter.setWords(word)

        })

        fab.setOnClickListener{
            val intent = Intent(context, NewWordActivity::class.java)
            startActivityForResult(intent,newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWordActivityRequestCode && resultCode ==Activity.RESULT_OK){
            data?.let {
                val word_text = data.getStringExtra(NewWordActivity.EXTRA_REPLY)
                val word_value =data.getIntExtra(NewWordActivity.EXTRA_REPLY_VALUE,0)

                val word=Word(word_text,word_value)
                wordViewModel.insert(word)
                Unit
            }
        }else{
            Toast.makeText(context,"Word tidak disimpan karena kosong!!",Toast.LENGTH_SHORT)
        }
    }
}

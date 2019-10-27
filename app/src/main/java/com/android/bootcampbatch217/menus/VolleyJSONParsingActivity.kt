package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.ListUserAdapter
import com.android.bootcampbatch217.models.UserModel
import com.android.bootcampbatch217.utilities.loadingAnimationAndText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_volley_jsonparsing.*

class VolleyJSONParsingActivity : AppCompatActivity() {
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_jsonparsing)

        buttonGetListUser.setOnClickListener {
            getUserListFromAPI()
        }
    }

    fun getUserListFromAPI(){
        val loading= loadingAnimationAndText("Please Wait!!")

        //ambil JSON dari API Menggunakan Volley
        val url = "https://reqres.in/api/users?page=1"
         val requestJSON =JsonObjectRequest(
             Request.Method.GET,
             url,
             null,
             Response.Listener { response->
                 loading.dismiss()
                 println("Sukses: ${response.toString()}")

                 //parsing JSON

                 val jsonArray=response.getJSONArray("data")
                 val sizeArray= jsonArray.length()
                 var userModels=ArrayList<UserModel>()

                 if(sizeArray>0){
                     for(n in 0 until sizeArray){
                         val jsonObject=jsonArray.getJSONObject(n)

                         val model= UserModel()
                         model.id=jsonObject.getInt("id")
                         model.email=jsonObject.getString("email")
                         model.first_name=jsonObject.getString("first_name")
                         model.last_name=jsonObject.getString("last_name")
                         model.avatar=jsonObject.getString("avatar")

                         userModels.add(model)

                         //kirim ke custom adapter
                         isiListAdapter(userModels)

                         // val email=jsonObject.getString("email")
                         // println("$n = $email")
                     }

                     //kirim ke custom adapter


                 }else{
                     Toast.makeText(context,"List User kososng",Toast.LENGTH_SHORT).show()
                 }

             },
             Response.ErrorListener { error->
                 loading.dismiss()
                 Toast.makeText(context,"Error: ${error.message}",Toast.LENGTH_SHORT).show()
                 println("Error get list user: ${error.message}")
             }
         )
        Volley.newRequestQueue(context).add(requestJSON)
    }

    fun isiListAdapter(userModels: ArrayList<UserModel>){
        val adapterUser=ListUserAdapter(context,userModels)
        listUser.adapter=adapterUser
    }
}


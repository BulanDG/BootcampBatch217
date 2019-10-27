package com.android.bootcampbatch217.menus

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.utilities.loadingAnimation
import com.android.bootcampbatch217.utilities.loadingAnimationAndText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_volley.*

class VolleyActivity : AppCompatActivity() {
    val context= this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)

        buttonLoadString.setOnClickListener {
            panggilAPI1()
        }

        buttonLoadImage.setOnClickListener {
            panggilImageFromWeb()
        }
    }

    fun panggilAPI1(){
        //start progressdialog
        val loading=loadingAnimationAndText("Silahkan Tunggu...")
        loading.show()

        val url="http://jsonplaceholder.typicode.com/posts?userId=1"

        //String request menggunakan volley
        val requestAPI=StringRequest(
            Request.Method.GET,//method
            url,//url source
            Response.Listener {
                //response success
                response ->  textResult.text=response
                loading.dismiss()
            },
            Response.ErrorListener {
                //response error
                error ->  Toast.makeText(context,"Error : ${error.message}",Toast.LENGTH_SHORT).show()
                loading.dismiss()
            }
        )

        Volley.newRequestQueue(context).add(requestAPI) // execute volley request
    }

    fun panggilImageFromWeb(){
        val loading=loadingAnimation()
        loading.show()

        val url ="https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        //image request menggunakan volley
        val requestImage=ImageRequest(
            url,
            Response.Listener {response->
                loading.dismiss()
                imageFromWeb.setImageBitmap(response)
            },
            0,
            0,
            ImageView.ScaleType.FIT_XY,
            Bitmap.Config.ARGB_8888,
            Response.ErrorListener { error->
                //response error
                loading.dismiss()
                Toast.makeText(context,"Error load Image : ${error.message}",Toast.LENGTH_SHORT).show()
            }
        )

        Volley.newRequestQueue(context).add(requestImage) // execute volley request

    }
}

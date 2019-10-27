package com.android.bootcampbatch217.menus

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.ListPhotoAdapter
import com.android.bootcampbatch217.models.PhotoModel
import com.android.bootcampbatch217.utilities.loadingAnimation
import com.android.bootcampbatch217.utilities.loadingAnimationAndText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_advance_parsing.*

class AdvanceParsingActivity : AppCompatActivity() {
    val context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advance_parsing)

        buttonLoadJSONArray.setOnClickListener {
            panggilAPIPhotos()
        }
    }

    fun panggilAPIPhotos(){
        val loading= loadingAnimation()
        loading.show()
        val urlAPI="https://jsonplaceholder.typicode.com/photos?albumid=1"

        val requestJSON= JsonArrayRequest(
            Request.Method.GET,
            urlAPI,
            null,
            Response.Listener { response->
                loading.dismiss()
                //parsing
                val sizeArray=response.length()
                if(sizeArray>0){
                    var photoModels= ArrayList<PhotoModel>()
                    for(n in 0 until sizeArray){
                        val photoObject=response.getJSONObject(n)
                        var model = PhotoModel()
                        model.albumId=photoObject.getInt("albumId")
                        model.id=photoObject.getInt("id")
                        model.title=photoObject.getString("title")
                        model.url=photoObject.getString("url")
                        model.thumbnailUrl=photoObject.getString("thumbnailUrl")

                        photoModels.add(model)
                    }

                    //kirim ke adapter
                    isiListPhotoAdapter(photoModels)
                }else{
                    Toast.makeText(context,"List photo kosong",Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                error->
                loading.dismiss()
                Toast.makeText(context,"Error: ${error.message}",Toast.LENGTH_SHORT).show()

            }
        )
        Volley.newRequestQueue(context).add(requestJSON)
    }
    fun isiListPhotoAdapter(photoModels: ArrayList<PhotoModel>){
        val adapterPhoto = ListPhotoAdapter(context,photoModels)
        listPhotos.adapter=adapterPhoto

    }

}

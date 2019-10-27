package com.android.bootcampbatch217.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_multi_level_parsing.*

class MultiLevelParsingActivity : AppCompatActivity() {
    val context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_level_parsing)
        loadNYTimesAPI()
    }

    fun loadNYTimesAPI(){
//loading dgn progress bar
        progressBar.visibility = ProgressBar.VISIBLE
        contentNews.visibility = ProgressBar.GONE

        val urlAPI= "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=9b693ffaa5fe451090146e5c90fbed78&q=indonesia&page=0"

        val requestAPI = JsonObjectRequest(
            Request.Method.GET,
            urlAPI,
            null,
            Response.Listener { response ->
                progressBar.visibility = ProgressBar.GONE
                contentNews.visibility = ProgressBar.VISIBLE

                println("json = $response")

                //parsing
                //level 1 = response
                val responseObject = response.getJSONObject("response")

                //level 2 = docs
                val docsArray = responseObject.getJSONArray("docs")
                val countNews = docsArray.length()
                totalNews.text = countNews.toString()

                //level 3 = snippet
                val snippetValue = docsArray.getJSONObject(0).getString("snippet")
                sippetNews.text=snippetValue

                //level 3= multimedia
                val objectIndexSatu=docsArray.getJSONObject(1)
                val countMultimedia=objectIndexSatu.getJSONArray("multimedia").length()
                totalMedia.text=countMultimedia.toString()

                //level 4=crop
                val objectIndexDua=docsArray.getJSONObject(2)
                val arrayMultimedia=objectIndexDua.getJSONArray("multimedia")
                val cropValue=arrayMultimedia.getJSONObject(0).getString("crop_name")
                cropName.text=cropValue

                //level 5=legacy xlarge
                val legacyObject= arrayMultimedia.getJSONObject(0).getJSONObject("legacy")
                val urlValue="https://www.nytimes.com/"+legacyObject.getString("xlarge")

                Glide.with(context).load(urlValue).into(legacyXlarge)

            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "Error : ${error.message}", Toast.LENGTH_SHORT).show()
                println("Error : ${error.message}")
            }
        )

        Volley.newRequestQueue(context).add(requestAPI)
    }
}

package com.android.bootcampbatch217.menus

import Data
import ListUserModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.adapters.ListUserRecyclerAdapter
import com.android.bootcampbatch217.retrofit_utilities.APIUtilities
import com.android.bootcampbatch217.retrofit_utilities.RequestAPIServices
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    val context=this
    val apiServices:RequestAPIServices=APIUtilities.apiServices

    var listUserModel= ArrayList<Data>()
    var adapterListUserRecycler: ListUserRecyclerAdapter?=null

    var page =1
    var total_page=1
    var is_still_loading=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        //inisialisasi orientation recycle view //mengatur mode recyclerView -> VERTIKAL, HORIZONTAL, GRID
        val layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        listUserRetrofit.layoutManager=layoutManager

        buttonLoadAPIRetrofit.setOnClickListener {
            panggilAPIListUser(page)
        }
    }

    fun panggilAPIListUser(page:Int){
        is_still_loading=true

        //loading gaya skeleton
        val skeletonLoading:RecyclerViewSkeletonScreen= Skeleton.bind(listUserRetrofit)
                                                        .adapter(adapterListUserRecycler)
                                                        .load(R.layout.list_user_skeleton)
                                                        .shimmer(true)
                                                        .duration(1000)
                                                        .show()
        apiServices.getListUsers(page).enqueue(object: Callback<ListUserModel>{
            override fun onResponse(call: Call<ListUserModel>, response: Response<ListUserModel>) {
                is_still_loading=false
                skeletonLoading.hide()

                //baca response code
                if(response.code()==200){
                    if(listUserModel.size>0){
                        val dataArray:List<Data> = response.body()!!.data
                        dataArray.forEach { data ->
                            listUserModel.add(data)
                        }
                    }else{
                        //isi pertama kali
                        listUserModel=response.body()!!.data as ArrayList<Data>
                        total_page=response.body()!!.total_pages
                    }
                    tampilkanListUser()
                    // Toast.makeText(context,"Jumlah data = ${response.body()!!.per_page}",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Something went wrong ${response.code()}",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ListUserModel>, t: Throwable) {
                is_still_loading=false
                skeletonLoading.hide()

                Toast.makeText(context,"Error: ${t.message}",Toast.LENGTH_SHORT).show()
                println("Error: ${t.message}")
            }

        })

    }

    fun tampilkanListUser(){
        adapterListUserRecycler= ListUserRecyclerAdapter(context,listUserModel)
        adapterListUserRecycler!!.notifyDataSetChanged()



        listUserRetrofit.adapter= adapterListUserRecycler
        losdMorePaging()
    }

    fun losdMorePaging(){
        //ototmatis panggil ulang fungsi panggilAPIListUser jika scroll layar sudah max
        val layoutManager=listUserRetrofit.layoutManager as LinearLayoutManager

        //fungsi deteksi scroll
        listUserRetrofit.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                //1: deteksi posisi item terakhir dari RecyclerView
                val lastItemPosition=layoutManager.findLastVisibleItemPosition()

                //2: handle aksi scrolling vertikal
                val isScrollVertical=recyclerView.canScrollVertically(1)

                if(lastItemPosition==layoutManager.itemCount-1
                    && !isScrollVertical
                    && page <= total_page
                    && !is_still_loading){

                    page +=1
                    panggilAPIListUser(page)

                }
            }
        })

    }
}

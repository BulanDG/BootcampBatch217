package com.android.bootcampbatch217.retrofit_utilities

import com.bumptech.glide.manager.RequestManagerTreeNode
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    var retrofit:Retrofit?=null
    fun getClient(baseUrl:String):Retrofit?{
        val interceptor= HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        //set Timeout while connect to server
        val client=OkHttpClient.Builder()
            .connectTimeout(60,TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        if(retrofit==null){
            retrofit=Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}
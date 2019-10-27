package com.android.bootcampbatch217.retrofit_utilities

object APIUtilities {
    val BASE_URL_API="https://reqres.in/api/"
    val apiServices: RequestAPIServices=
        RetrofitClient.getClient(BASE_URL_API)!!.create(RequestAPIServices::class.java)
}
package com.android.bootcampbatch217.retrofit_utilities

import CreateUserModel
import ListUserModel
import com.android.bootcampbatch217.models.patchuser.PatchUserModel
import com.android.bootcampbatch217.models.putuser.PutUserModel
import retrofit2.Call
import retrofit2.http.*

interface RequestAPIServices {
    //deklarasikan disini (semua method yang ada di API yg akan digunakan )

    //api get list user => request get data
    @GET("users")
    fun getListUsers(@Query("page")page: Int): Call<ListUserModel>

    //api POST user => request create data
    @FormUrlEncoded
    @POST("users")
    fun createUser(@Field("name")name:String,@Field("job")job:String):Call<CreateUserModel>

    //api put user => request update data, all data parameter need to specify
    @FormUrlEncoded
    @PUT("users/{id}")
    fun putUser(@Path("id")id:Int,@Field("name")name:String,@Field("job")job:String): Call<PutUserModel>

    //api patch user => request update data,
    @FormUrlEncoded
    @PATCH("users/{id}")
    fun patchUser(@Path("id")id:Int,@Field("name")name:String,@Field("job")job:String):Call<PatchUserModel>
}
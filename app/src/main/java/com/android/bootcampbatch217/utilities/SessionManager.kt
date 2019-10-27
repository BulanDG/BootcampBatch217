package com.android.bootcampbatch217.utilities

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    //nama2 shared preferences harus unik
    val SHARED_PREFERENCES= "SESSION_MANAGER_1" //versi data, jika berubah maka yg lama hilang
    val KEY_USERNAME="USERNAME"
    val KEY_PASSWORD="PASSWORD"
    val KEY_LOGIN_FLAG="LOGIN"
    val KEY_REMEMBER_ME="REMEMBER"

    protected fun retrieveSharedPreferences(context:Context):SharedPreferences{
        return context.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE) // getSharedPreferences=open db(sharedprefences)
    }

    protected fun retrieveSharedPreferencesEditor(context: Context):SharedPreferences.Editor{
        return retrieveSharedPreferences(context).edit()
    }

    //function sesuai kebutuhan aplikasi kita
    //1 simpan data-data login
    fun simpanDataLogin(context: Context,userName:String,password:String,remember:Boolean){
        val editor=retrieveSharedPreferencesEditor(context)

        editor.putString(KEY_USERNAME,userName)
        editor.putString(KEY_PASSWORD,password)
        editor.putBoolean(KEY_REMEMBER_ME,remember)
        editor.putBoolean(KEY_LOGIN_FLAG,true)

        editor.commit()
    }

    //2 cek flag login
    fun cekLogin(context: Context):Boolean{
        val login=retrieveSharedPreferences(context).getBoolean(KEY_LOGIN_FLAG,false)
        return login
    }

    //3 ambil username
    fun getUserName(context: Context):String?{
        return retrieveSharedPreferences(context).getString(KEY_USERNAME,"")
    }

    //4 ambil password
    fun getPassword(context: Context):String?{
        return retrieveSharedPreferences(context).getString(KEY_PASSWORD,"")
    }

    //5 cek flag remember
    fun cekRememberMe(context: Context):Boolean{
        return retrieveSharedPreferences(context).getBoolean(KEY_REMEMBER_ME,false)
    }

    //6 Logout
    fun logout(context: Context){
        val editor=retrieveSharedPreferencesEditor(context)
        if(cekRememberMe(context)){
            //true
            editor.putBoolean(KEY_LOGIN_FLAG,false)
        }else{
            //false
            editor.putString(KEY_USERNAME,"")
            editor.putString(KEY_PASSWORD,"")
            editor.putBoolean(KEY_REMEMBER_ME,false)
            editor.putBoolean(KEY_LOGIN_FLAG,false)
        }
        editor.commit()
    }

}
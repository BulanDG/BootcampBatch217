package com.android.bootcampbatch217.menus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.bootcampbatch217.R
import com.android.bootcampbatch217.models.createuser.CreateUserModel
import com.android.bootcampbatch217.models.patchuser.PatchUserModel
import com.android.bootcampbatch217.models.putuser.PutUserModel
import com.android.bootcampbatch217.retrofit_utilities.APIUtilities
import com.android.bootcampbatch217.retrofit_utilities.RequestAPIServices
import com.android.bootcampbatch217.utilities.loadingAnimationAndText
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_retrofit_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitPostActivity : AppCompatActivity() {
    val context=this
    val apiServices: RequestAPIServices = APIUtilities.apiServices


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_post)

        buttonSubmitData.setOnClickListener {
            hideKeyBoard()
            val nama=inputNamaUser.text.toString().trim()
            val job:String=inputJobUser.text.toString().trim()
            submitDataUser(nama,job)
        }

        buttonPutData.setOnClickListener {
            hideKeyBoard()
            val id: Int =inputIdUser.text.toString().toInt()
            val nama=inputNamaUser.text.toString().trim()
            val job:String=inputJobUser.text.toString().trim()
            putDataUser(id,nama,job)

        }

        buttonPatchData.setOnClickListener {
            hideKeyBoard()
            val id: Int =inputIdUser.text.toString().toInt()
            val nama=inputNamaUser.text.toString().trim()
            val job:String=inputJobUser.text.toString().trim()
            patchDataUser(id,nama,job)

        }

        //auto hide key
        contentCreateUser.setOnTouchListener { view, motionEvent ->
            hideKeyBoard()
        }
    }

    fun hideKeyBoard():Boolean{
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)

        return true
    }

    fun submitDataUser(nama:String,job:String){
        val loading=loadingAnimationAndText("Mengirim data,silahkan tunggu ...")
        loading.show()
        apiServices.createUser(nama,job).enqueue(object :Callback<CreateUserModel>{
            override fun onResponse(
                call: Call<CreateUserModel>,
                response: Response<CreateUserModel>
            ) {
                loading.dismiss()

                //baca response
                if(response.code()==201){
                    val teks=response.body()!!.name+" "+response.body()!!.createdAt
                    Snackbar.make(contentCreateUser,teks,Snackbar.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CreateUserModel>, t: Throwable) {
               loading.dismiss()
                Toast.makeText(context,"Error ${t.message}",Toast.LENGTH_SHORT).show()
                println("Error ${t.message}")
            }
        })


    }

    fun putDataUser(id:Int,nama:String,job:String){
        val loading=loadingAnimationAndText("Mengirim data,silahkan tunggu ...")
        loading.show()
        apiServices.putUser(id,nama,job).enqueue(object :Callback<PutUserModel>{
            override fun onResponse(
                call: Call<PutUserModel>,
                response: Response<PutUserModel>
            ) {
                loading.dismiss()

                //baca response
                if(response.code()==200){
                    val teks=response.body()!!.name+" "+response.body()!!.updatedAt
                    Snackbar.make(contentCreateUser,teks,Snackbar.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PutUserModel>, t: Throwable) {
                loading.dismiss()
                Toast.makeText(context,"Error ${t.message}",Toast.LENGTH_SHORT).show()
                println("Error ${t.message}")
            }
        })


    }

    fun patchDataUser(id:Int,nama:String,job:String){
        val loading=loadingAnimationAndText("Mengirim data,silahkan tunggu ...")
        loading.show()
        apiServices.patchUser(id,nama,job).enqueue(object :Callback<PatchUserModel>{
            override fun onResponse(
                call: Call<PatchUserModel>,
                response: Response<PatchUserModel>
            ) {
                loading.dismiss()

                //baca response
                if(response.code()==200){
                    val teks=response.body()!!.name+" "+response.body()!!.updatedAt
                    Snackbar.make(contentCreateUser,teks,Snackbar.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PatchUserModel>, t: Throwable) {
                loading.dismiss()
                Toast.makeText(context,"Error ${t.message}",Toast.LENGTH_SHORT).show()
                println("Error ${t.message}")
            }
        })


    }
}

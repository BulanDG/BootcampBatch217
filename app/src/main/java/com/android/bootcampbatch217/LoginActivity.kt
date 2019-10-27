package com.android.bootcampbatch217

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.bootcampbatch217.utilities.EXTRA_PASSWORD
import com.android.bootcampbatch217.utilities.EXTRA_USERNAME
import com.android.bootcampbatch217.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //action handler u/k
        buttonLogin.setOnClickListener{
            validasiInput()
        }

        //action handler uk text register new user
        registerNewUser.setOnClickListener{
            pindahKeRegisterActivity()
        }

    }

    fun validasiInput(){
        //ambil user name & pass
        var username=inputUsername.text.toString().trim()
        var password= inputPassword.text.toString().trim()
        var isRemember= checkRemember.isChecked //true false

        if(username.length==0){
            Toast.makeText(context,"Username belum diisi",Toast.LENGTH_SHORT).show()
        } else if(password.length==0){
            Toast.makeText(context,"Password belum diisi",Toast.LENGTH_SHORT).show()
        }else{
            //logic menyimpan username & pass
            //shared preference
            SessionManager().simpanDataLogin(context,username,password,isRemember)

            //pindah screen
            pindahKeMainActivity(username,password)
        }

        Toast.makeText(context,
            "Username=$username , Password=$password",
            Toast.LENGTH_LONG).show()
    }

    fun pindahKeMainActivity(username:String,password:String){
        val intent= Intent(this,MainActivity::class.java) //this = diri sendiri , mau ke mana

        //send parameter to another activiy
        intent.putExtra(EXTRA_USERNAME,username)
        intent.putExtra(EXTRA_PASSWORD,password)


        startActivity(intent)
        finish()// mendestroy activiti ini = splashscreen
    }

    fun pindahKeRegisterActivity(){
        val intent= Intent(this,RegisterActivity::class.java) //this = diri sendiri , mau ke mana
        startActivity(intent)
    }
}

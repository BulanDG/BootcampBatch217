package com.android.bootcampbatch217

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.android.bootcampbatch217.utilities.DELAY_SPLASH_SCREEN
import com.android.bootcampbatch217.utilities.SessionManager
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    val context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //code untuk fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        setContentView(R.layout.activity_splash_screen)

        buatJedaWaktu()
    }

    fun buatJedaWaktu(){
        //menggunakan timer & timerTask
        var timerTask= object :TimerTask(){ // instance objeck
            override fun run() {
                if(SessionManager().cekLogin(context)){
                    //sudah login
                    pindahKeMainActivity()

                }else{
                    //belum login
                    pindahKeLoginActivity()
                }
                //pindahKeLoginActivity() //To change body of created functions use File | Settings | File Templates.
            }

        }
        var timer:Timer = Timer()
        timer.schedule(timerTask, DELAY_SPLASH_SCREEN)
    }

    fun pindahKeMainActivity(){
        val intent=Intent(this,MainActivity::class.java) //this = diri sendiri , mau ke mana
        startActivity(intent)
        finish()// mendestroy activiti ini = splashscreen
    }

    fun pindahKeLoginActivity(){
        val intent=Intent(this,LoginActivity::class.java) //this = diri sendiri , mau ke mana
        startActivity(intent)

        finish()

    }
}
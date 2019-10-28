package com.android.bootcampbatch217

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import com.android.bootcampbatch217.mahasiswa.DaftarMahasiswaActivity
import com.android.bootcampbatch217.menus.*
import com.android.bootcampbatch217.perpustakaan.PerpustakaanActivity
import com.android.bootcampbatch217.utilities.EXTRA_PASSWORD
import com.android.bootcampbatch217.utilities.EXTRA_USERNAME
import com.android.bootcampbatch217.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cekUserNamePasswordDariExtra()
        //cekUserNamePasswordDariSessionManager()

        buttonLogout.setOnClickListener{
            logoutConfirmation()
        }
        menuActionClick()
    }

    fun menuActionClick(){
        //menu1
        menu1.setOnClickListener{
            val intent=Intent(context,ListViewActivity::class.java)
            startActivity(intent)
        }

        //menu2
        menu2.setOnClickListener{
            val intent=Intent(context,VolleyActivity::class.java)
            startActivity(intent)
        }

        //menu3
        menu3.setOnClickListener{
            val intent=Intent(context,VolleyJSONParsingActivity::class.java)
            startActivity(intent)
        }

        //menu4
        menu4.setOnClickListener{
            val intent=Intent(context,PicassoActivity::class.java)
            startActivity(intent)
        }

        //menu5
        menu5.setOnClickListener{
            val intent=Intent(context,GlideActivity::class.java)
            startActivity(intent)
        }

        //menu6
        menu6.setOnClickListener{
            val intent=Intent(context,AdvanceParsingActivity::class.java)
            startActivity(intent)
        }

        //menu7
        menu7.setOnClickListener{
            val intent=Intent(context,MultiLevelParsingActivity::class.java)
            startActivity(intent)
        }

        //menu8
        menu8.setOnClickListener{
            val intent=Intent(context,RetrofitActivity::class.java)
            startActivity(intent)
        }

        //menu9
        menu9.setOnClickListener{
            val intent=Intent(context,RetrofitPostActivity::class.java)
            startActivity(intent)
        }

        //menu10
        menu10.setOnClickListener{
            val intent=Intent(context,TabbedMenuActivity::class.java)
            startActivity(intent)
        }

        //menu 11
        menu11.setOnClickListener{
            val intent=Intent(context,ImageSliderActivity::class.java)
            startActivity(intent)
        }

        //menu 12
        menu12.setOnClickListener{
            val intent=Intent(context,CameraActivity::class.java)
            startActivity(intent)
        }

        //menu 13
        menu13.setOnClickListener{
            val intent=Intent(context,GalleryActivity::class.java)
            startActivity(intent)
        }

        //menu 14
        menu14.setOnClickListener{
            val intent=Intent(context,EsafirmActivity::class.java)
            startActivity(intent)
        }

        //menu 15
        menu15.setOnClickListener{
            val intent=Intent(context,DaftarMahasiswaActivity::class.java)
            startActivity(intent)
        }

        //menu 16
        menu16.setOnClickListener{
            val intent=Intent(context,PerpustakaanActivity::class.java)
            startActivity(intent)
        }
    }

    fun cekUserNamePasswordDariSessionManager(){
        var valueUsername=SessionManager().getUserName(context)
        var valuePassword:String?=SessionManager().getPassword(context)

        labelName.text=valueUsername+" "+valuePassword
    }

    fun cekUserNamePasswordDariExtra(){
        var bundle:Bundle?= intent.extras
        //if bundle not null
        bundle?.let {
            var valueUsername=bundle!!.getString(EXTRA_USERNAME)
            var valuePassword:String?=bundle!!.getString(EXTRA_PASSWORD)

            labelName.text=valueUsername+" "+valuePassword
        }
    }


    fun logoutConfirmation(){
        val confirm=AlertDialog.Builder(context)
        confirm.setMessage("AndaYakin mau logout?")
            .setPositiveButton("Ya"){
                dialog, which ->logoutAplikasi()
            }
            .setNegativeButton("Tidak"){
                dialog, which ->  dialog.cancel()
            }
            .setCancelable(false)
        confirm.create().show()
    }

    fun logoutAplikasi(){
        //clear data login
        SessionManager().logout(context)

        //kembali activity login
        val intent=Intent(context,LoginActivity::class.java)
        startActivity(intent)
        //close main activity
        finish()
    }

    override fun onResume() {
        super.onResume()
    }

}

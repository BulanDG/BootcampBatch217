package com.android.bootcampbatch217.databases

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DatabasePerpustakaanHelper(val context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        internal val DATABASE_NAME = "db_perpustakaan.db"
        internal val DATABASE_VERSION = 1
        internal val DATABASE_PATH = "/data/data/com.android.bootcampbatch217/databases/"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //skip
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion < newVersion){
            deleteDatabase()
        }
    }

    //pengecekan database -> exist or not
     fun checkDatabase() : Boolean{
        var db: SQLiteDatabase? = null

        try{
            db = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME,
                null
                , SQLiteDatabase.OPEN_READONLY)

        } catch (ex: SQLException){
            println("checkDatabase SQLException : ${ex.message}")
            Log.e("checkDatabase", ex.message)
        }
        finally {
            db?.close()
        }

        return if(db == null) false else true
    }

    //create new database dng cara import file db via stream
    @Throws(IOException::class)
    private fun importDatabase(){
        //1 buka file db
        val inputStream = context.assets.open(DATABASE_NAME)

        //2 siapkan path utk menyimpan database hasil import
        val outputFilePath = DATABASE_PATH + DATABASE_NAME

        //3 write file
        val outputStream = FileOutputStream(outputFilePath)
        val buffer = ByteArray(1024)
        do{
            val bytesRead = inputStream.read(buffer)
            outputStream.write(buffer, 0, bytesRead)
        } while (bytesRead != -1)

        //close all file connection
        outputStream.flush()
        outputStream.close()
        inputStream.close()

        println("Import database success!!!")
    }

    //fungsi membuat database dari import db
    @Throws(IOException::class)
    fun createDatabaseFromImportedFile(){
        if(checkDatabase()){
            //database sudah ada
            println("Database sudah ada!!!")
        }
        else{
            //database belum ada
            try {
                importDatabase()
            }catch (x: IOException){
                println("createDatabaseFromImportedFile : ${x.message}")
            }
        }
    }

    //fungsi delete database lama
    fun deleteDatabase(){
        val file = File(DATABASE_PATH + DATABASE_NAME)
        if(file.exists()){
            file.delete()

            println("Old database successfully deleted!!!")
        }
    }
}
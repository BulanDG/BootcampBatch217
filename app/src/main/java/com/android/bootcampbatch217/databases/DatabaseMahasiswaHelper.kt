package com.android.bootcampbatch217.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.android.bootcampbatch217.utilities.*

class DatabaseMahasiswaHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        internal val DATABASE_NAME = "database_mahasiswa.db"
        internal val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        //create tabel
        val query="CREATE TABLE '$TABEL_BIODATA' (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                " 'nim' INTEGER," +
                " 'nama' TEXT," +
                " 'gender' TEXT," +
                " 'tanggal_lahir' TEXT," +
                "'alamat' TEXT,"+
                "'jurusan' TEXT," +
                " 'path_foto' TEXT)"

        db!!.execSQL(query)

        //preload data 1 record
        val queryInsert= "INSERT INTO '$TABEL_BIODATA' ($NIM,$NAMA,$GENDER,$TANGGAL_LAHIR,$ALAMAT,$JURUSAN,$PATH_FOTO) " +
                "VALUES (123456,'Peter parker','L','01/10/1975','Newyork City','Hukum','-')"

        db!!.execSQL(queryInsert)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //
    }
}


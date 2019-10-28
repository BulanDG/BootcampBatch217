package com.android.bootcampbatch217.databases

import android.database.Cursor
import com.android.bootcampbatch217.models.Buku

class DatabasePerpustakaanQueryHelper(val databaseHelper: DatabasePerpustakaanHelper){

    //local constants
    val NAMA_TABEL_BUKU = "stok_buku"
    val FIELD_ID = "id"
    val FIELD_JUDUL = "judul_buku"
    val FIELD_KATEGORI = "kategori_buku"
    val FIELD_PENGARANG = "pengarang_buku"
    val FIELD_PENERBIT = "penerbit_buku"
    val FIELD_HARGA = "harga"
    val FIELD_STOK = "stok"

    private fun readSemuaBuku(): Cursor{
        val db = databaseHelper.readableDatabase

        val queryRead = "SELECT * FROM $NAMA_TABEL_BUKU"

        return db.rawQuery(queryRead, null)
    }

    private fun konversiCursorKeListBukuModel(cursor: Cursor): ArrayList<Buku>{
        var listBuku = ArrayList<Buku>()

        for(c in 0 until cursor.count){
            cursor.moveToPosition(c)

            val buku = Buku()
            buku.id = cursor.getInt(0)
            buku.judul_buku = cursor.getString(1)
            buku.kategori_buku = cursor.getString(2)
            buku.pengarang_buku = cursor.getString(3)
            buku.penerbit_buku = cursor.getString(4)
            buku.harga = cursor.getInt(5)
            buku.stok = cursor.getInt(6)

            listBuku.add(buku)
        }

        return listBuku
    }

    fun readSemuaBukuModels(): List<Buku>{
        var listBuku = ArrayList<Buku>()

        val cursor = readSemuaBuku()
        if(cursor.count > 0){
            listBuku = konversiCursorKeListBukuModel(cursor)
        }

        return listBuku
    }
}
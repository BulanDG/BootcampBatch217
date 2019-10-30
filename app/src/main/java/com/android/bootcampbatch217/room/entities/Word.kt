package com.android.bootcampbatch217.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//WORD TABLE
@Entity(tableName = "word_table") //inisiasi nama tabel
class Word(
    @PrimaryKey
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "value") val value: Int //add field
)
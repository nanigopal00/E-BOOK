package com.example.e_book.Doman.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BooksTable(
    @PrimaryKey var key:Long = 0,
   @ColumnInfo var bookimage:String = "",
    @ColumnInfo  var booklink:String ="",
    @ColumnInfo var bookname:String ="",
    @ColumnInfo var catagori:String="",


)

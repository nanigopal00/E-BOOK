package com.example.e_book.Doman.database

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [BooksTable::class], version = 2, exportSchema = false)
abstract class database: RoomDatabase() {
    abstract fun getDao(): BooksDao
}
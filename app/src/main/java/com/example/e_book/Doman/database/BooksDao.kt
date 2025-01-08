package com.example.e_book.Doman.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: BooksTable)
    @Delete
    suspend fun deleateBooks(books: BooksTable)
    @Query("SELECT * FROM BooksTable")
    fun getallbooks():Flow<List<BooksTable>>

}
package com.example.e_book.common.Di



import android.app.Application
import androidx.room.Room
import com.example.e_book.Doman.database.database
import com.example.e_book.data.network.getDataRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn (SingletonComponent::class)
object hiltmodul {
    @Provides
    @Singleton
    fun getrepo():getDataRepo{
        return getDataRepo()
    }
    @Provides
    @Singleton
    fun getBooksdataBase(app: Application): database {
        return Room.databaseBuilder(app, database::class.java,"booksdb").build()
    }



}
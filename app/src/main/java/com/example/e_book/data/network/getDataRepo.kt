package com.example.e_book.data.network

import android.util.Log
import com.example.e_book.common.resultstate
import com.example.e_book.data.model.BooksDtoModel
import com.example.e_book.data.model.catagorimodel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class getDataRepo {
    fun getallcatarori():Flow<resultstate<List<catagorimodel>>> = callbackFlow {
       trySend(resultstate.Loading(state = true))
        var valueevent = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               // Log.d("snapsort", "$snapshot")
                var items:List<catagorimodel> = emptyList()
                   items= snapshot.children.map {

                       it.getValue<catagorimodel>()!!

                   }

                trySend(resultstate.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                trySend(resultstate.Error(message = error.message.toString()))
            }

        }

        Firebase.database.reference.child("boookcatagori").addValueEventListener(valueevent)
        awaitClose{
            Firebase.database.reference.child("boookcatagori").removeEventListener(valueevent)
            close()


        }



    }
    // heare is getting all catagori function
    fun getallbooks():Flow<resultstate<List<BooksDtoModel>>> = callbackFlow {
       trySend(  resultstate.Loading(state = true))
        var valueevent = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                    val data: List<BooksDtoModel> = snapshot.children.mapNotNull{ childSnapshot ->
                        val book = childSnapshot.getValue<BooksDtoModel>()
                        if (book == null) {
                            Log.e("FirebaseData", "Failed to parse book: $childSnapshot")
                            null
                        } else {
                            val key = childSnapshot.key // Get the key here
                            book.copy(key = key) // Create a new object with the key
                        }
                    }

                    trySend(resultstate.Success(data = data))


            }

            override fun onCancelled(error: DatabaseError) {
                trySend(resultstate.Error(message = error.message.toString()))


            }

        }
        Firebase.database.reference.child("books").addValueEventListener(valueevent)
        awaitClose{
            Firebase.database.reference.child("books").removeEventListener(valueevent)
            close()
        }




    }
    fun getallbooksBycatagori(catagori:String):Flow<resultstate<List<BooksDtoModel>>> = callbackFlow {
        trySend(  resultstate.Loading(state = true))

        var valueevent = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var data:List<BooksDtoModel> = emptyList()
                data = snapshot.children.filter { filter->
                    filter.getValue<BooksDtoModel>()!!.catagori == catagori

                }.map { singel->
                 var book =  singel.getValue<BooksDtoModel>()!!
                   var key = singel.key
                    book.copy(key = key)

                }
                trySend(resultstate.Success(data = data))


            }

            override fun onCancelled(error: DatabaseError) {
                trySend(resultstate.Error(message = error.message.toString()))


            }

        }
        Firebase.database.reference.child("books").addValueEventListener(valueevent)
        awaitClose{
            Firebase.database.reference.child("books").removeEventListener(valueevent)
            close()
        }




    }



}
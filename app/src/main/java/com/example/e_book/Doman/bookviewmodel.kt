package com.example.e_book.Doman

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.Doman.database.database
import com.example.e_book.common.resultstate
import com.example.e_book.data.model.BooksDtoModel
import com.example.e_book.data.model.catagorimodel
import com.example.e_book.data.network.getDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class bookviewmodel@Inject constructor(var reop:getDataRepo,var datase: database):ViewModel() {
    var statecatagoriSc = mutableStateOf(catagoriScreenState())
    var books = MutableStateFlow<List<BooksDtoModel>>(emptyList())
    var getbooksRoom = datase.getDao().getallbooks()
    var BooksByCatagoriScreen = mutableStateOf(catagoriScreenState())


 init {
     viewModelScope.launch() {

         reop.getallcatarori().collect { result ->
           when (result) {
                 is resultstate.Error -> {
                     statecatagoriSc.value =  catagoriScreenState(error = result.message)
                 }
                 is resultstate.Loading -> {

                     statecatagoriSc.value =  catagoriScreenState(state = result.state)
                 }
                 is resultstate.Success -> {
                     statecatagoriSc.value =   catagoriScreenState(data = result.data, state = false)

                 }
             }

         }
         }
     }


    fun getallbooks() {
        viewModelScope.launch {
            reop.getallbooks().collect() { result ->
                when (result) {
                    is resultstate.Error -> {


                    }

                    is resultstate.Loading -> {


                    }

                    is resultstate.Success -> {
                        books.update {
                            result.data
                        }

                    }
                }

            }
        }
    }

      suspend  fun getbooksBycatagori(catagori:String){
                reop.getallbooksBycatagori(catagori = catagori ).collect(){result->
                   when(result){
                        is resultstate.Error ->{
                        //    BooksByCatagoriScreen.value = catagoriScreenState( error = result.message)



                        }
                        is resultstate.Loading -> {
                            BooksByCatagoriScreen.value =  catagoriScreenState( state = result.state)




                        }
                        is resultstate.Success ->{
                            Log.d("nanigopal", "onDataChange: ${result.data}")
                            BooksByCatagoriScreen.value = catagoriScreenState( bookbycatagorisc = result.data ,state = false)


                        }
                    }

                }




        }





 }


data class catagoriScreenState(
    var state:Boolean=false,
    var data:List<catagorimodel> = emptyList(),
    var bookbycatagorisc:List<BooksDtoModel> = emptyList(),
    var error:String=""
)







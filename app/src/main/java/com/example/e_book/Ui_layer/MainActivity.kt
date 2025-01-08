package com.example.e_book.Ui_layer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.e_book.Doman.bookviewmodel
import com.example.e_book.ui.theme.EBOOKTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var viewmodel = hiltViewModel<bookviewmodel>()
            viewmodel.getallbooks()

            EBOOKTheme {
                Box(modifier = Modifier.fillMaxSize()) {

                    var navcontroll = rememberNavController()
                   NavHost(startDestination = home , navController = navcontroll){
                       composable<home> {
                           home(navcontroll,viewmodel)
                       }
                       composable<pdfscreen> {
                           var data = it.toRoute<pdfscreen>()

                           pdfscreenB(navcontroll, data.key,data.bookimage,data.booklink,data.bookname,data.catagori,viewmodel)
                       }
                       composable<bookmark> {
                           bookmarkScreen(viewmodel,navcontroll)
                       }
                       composable<bookbycatagori> {
                           var data =  it.toRoute<bookbycatagori>()
                          bookBycatagori(viewmodel,navcontroll,data.catori)
                       }

                   }



                }
            }
        }
    }
    @Serializable
    object home
    @Serializable
    data class pdfscreen(
        var bookimage:String,
        var booklink:String,
        var bookname:String,
        var catagori:String,
        var key: String


        )
    @Serializable
    object bookmark
    @Serializable
    data class bookbycatagori(
            var catori:String
            )
}



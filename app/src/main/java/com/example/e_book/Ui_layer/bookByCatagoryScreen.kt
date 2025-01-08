package com.example.e_book.Ui_layer

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.example.e_book.Doman.bookviewmodel
import com.example.e_book.Doman.database.BooksTable
import com.example.e_book.R
import com.example.e_book.ui.theme.myColor
import com.example.e_book.ui.theme.myCustomFontFamily
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun bookBycatagori(viewmodel: bookviewmodel, navcontroll: NavHostController, catori: String) {
    LaunchedEffect(key1 = true) {
        viewmodel.getbooksBycatagori(catagori = catori)
    }
    var screenstate = viewmodel.BooksByCatagoriScreen.value
   Scaffold (
       topBar = {
           TopAppBar(

               navigationIcon = {
                   IconButton(
                       onClick = {
                           navcontroll.popBackStack()
                       },
                       Modifier
                           .size(37.dp)
                           .padding(start = 8.dp),
                       colors = IconButtonDefaults.iconButtonColors(
                           containerColor = Color.White.copy(0.4f)
                       )
                   ) {
                       Icon(
                           Icons.Filled.ArrowBack,
                           contentDescription = "",
                           Modifier.size(31.dp),
                           tint = Color.Black
                       )
                   }
               },
               title = {
                   Text(
                       text = catori,
                       color = Color.Black.copy(0.9f),
                       modifier = Modifier.padding(start = 7.dp),
                       style = MaterialTheme.typography.titleMedium,
                       fontFamily = myCustomFontFamily,
                       fontWeight = FontWeight.Bold,
                       fontSize = 24.sp
                   )
               },
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.White.copy(0.2f),
                   titleContentColor = Color.Black,
               ),
//               actions = {
//                   IconButton(onClick = {
////                       GlobalScope.launch {
////                           if (key != null) {
////                               viewmodel.datase.getDao().insertBooks(BooksTable(key = key.toLong(), bookimage = bookimage, booklink = booklink, bookname = bookname, catagori = catagori))
////                           }
////                       }
////                       Toast.makeText(contaxt, " Book Added Your Bookmark", Toast.LENGTH_SHORT).show()
//
//                   },
//                       Modifier
//                           .size(42.dp)
//                           .padding(end = 8.dp), colors = IconButtonDefaults.iconButtonColors(
//                           containerColor = MaterialTheme.colorScheme.background.copy(0.7f)
//                       )) {
//                       Icon(Icons.Rounded.StarBorder, contentDescription = "",Modifier.size(27.dp),tint = Color.Black)
//
//                   }
//               },
               modifier = Modifier
                   .shadow(
                       elevation = 1.dp,
                       spotColor = Color.Black.copy(0.2f)
                   )
                   .background(Color.White.copy(0.7f))
           )
       }

   ){it->

       Column(modifier = Modifier.fillMaxSize().padding(it)) {
           if (screenstate.state) {
               Column(Modifier.fillMaxSize()) {
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
                   ShimmeringPlaceholder()
               }
           } else if (screenstate.bookbycatagorisc.isEmpty()) {
               Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center) {
                   Icon(painter = painterResource(R.drawable.iconbook), contentDescription = null, modifier = Modifier.size(80.dp),
                       tint = Color.Red.copy(0.8f))
                   Text(
                       text = "No Books found..",
                       //style = MaterialTheme.typography.bodyLarge.copy(fontFamily = myCustomFontFamily),
                       textAlign = TextAlign.Center,
                       fontSize = 15.sp,
                       fontWeight = FontWeight.SemiBold,
                       modifier = Modifier.padding(top = 5.dp)
                   )

               }

           }else if (screenstate.bookbycatagorisc.isNotEmpty())
           {


               Text(
                   "All Books",
                   modifier = Modifier
                       .align(Alignment.Start)
                       .padding(start = 13.dp, top = 4.dp),
                   fontSize = 17.sp,
                   fontWeight = FontWeight.Bold,
                   color = Color.Black,
                   style = MaterialTheme.typography.headlineLarge.copy(
                       fontWeight = FontWeight.Bold,
                       fontFamily = myCustomFontFamily
                   )
               )
               Spacer(Modifier.heightIn(2.dp))
               LazyColumn(
                   Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.spacedBy(2.dp),
                   contentPadding = PaddingValues(3.dp)
               ) {
                   items(screenstate.bookbycatagorisc.size) { index ->
                       BookItem(
                           bookskey = screenstate.bookbycatagorisc[index].key,
                           bookImage = screenstate.bookbycatagorisc[index].bookimage,
                           bookName =  screenstate.bookbycatagorisc[index].bookname,
                           bookpdflink = screenstate.bookbycatagorisc [index].booklink,
                           bookCategory =  screenstate.bookbycatagorisc[index].catagori,
                           navcontrol = navcontroll,
                           viewmodel= viewmodel
                       )
                   }
               }


           }


       }


   }



@Composable
fun BookItem(
    bookImage: String,
    bookName: String,
    bookCategory: String,
    navcontrol: NavHostController,
    bookpdflink: String,
    viewmodel: bookviewmodel,
    bookskey: String?
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isFilled by remember { mutableStateOf(false) } // State for the icon
    Column(modifier = Modifier.clickable(
        interactionSource = interactionSource,
        indication = rememberRipple(color = myColor),
        onClick = {
            navcontrol.navigate(
                MainActivity.pdfscreen(
                    bookimage = bookImage,
                    booklink = bookpdflink,
                    bookname = bookName,
                    catagori = bookCategory,
                    key = bookskey!!,
                )
            )

        }
    )) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(145.dp)
                .padding(13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedCard(
                modifier = Modifier
                    .size(height = 145.dp, width = 80.dp)
                    .shadow(
                        elevation = 3.dp,
                        spotColor = Color.Black.copy(0.5f)
                    ),
                shape = RoundedCornerShape(12.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(bookImage)
                        .size(Size(80, 145))
                        .build(),
                    contentDescription = "Book Image",
                    modifier = Modifier.size(width = 80.dp, height = 145.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(Modifier.widthIn(13.dp))
            Column(
                modifier = Modifier.size(height = 160.dp, width = 235.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = bookName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black.copy(0.7f),
                    fontFamily = myCustomFontFamily,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = bookCategory,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black.copy(0.5f),
                        fontFamily = myCustomFontFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.widthIn(8.dp))
                    Icon(
                        painter = painterResource(R.drawable.book),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black.copy(0.6f)
                    )
                }
            }
            var contaxt = LocalContext.current

            IconButton(onClick = {
                isFilled = !isFilled
                GlobalScope.launch {
                    if (bookskey != null) {
                        viewmodel.datase.getDao().insertBooks(BooksTable(key = bookskey.toLong(), bookimage = bookImage, booklink = bookpdflink, bookname = bookName, catagori = bookCategory))
                    }
                    withContext(Dispatchers.Main){
                        Toast.makeText(contaxt, " Books Added Your Bookmarks ", Toast.LENGTH_SHORT).show()

                    }
                }

            }, modifier = Modifier.size(34.dp)) {
                Icon(
                    imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.StarOutline, // Use the state here
                    contentDescription = "",
                    modifier = Modifier.size(27.dp),
                    tint = Color.Red.copy(0.7f)
                )
            }
        }
        HorizontalDivider(modifier = Modifier.shimmer().padding(horizontal = 3.dp))
    }
}

}

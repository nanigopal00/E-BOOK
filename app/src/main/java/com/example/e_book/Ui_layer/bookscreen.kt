package com.example.e_book.Ui_layer

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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

@Composable
fun bookscreen(viewmodel: bookviewmodel, navcontroll: NavHostController) {
    val books = viewmodel.books.collectAsState().value
    val searchItemValue = rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.heightIn(5.dp))

//        OutlinedTextField(
//            onValueChange = { searchItemValue.value = it },
//            value = searchItemValue.value,
//            singleLine = true,
//            modifier = Modifier
//                .height(55.dp)
//                .fillMaxWidth()
//                .padding(horizontal = 6.dp),
//            shape = RoundedCornerShape(30.dp),
//            placeholder = {
//                Text("Search Books", color = Color.Black.copy(alpha = 0.6f))
//            },
//            leadingIcon = {
//                Icon(
//                    Icons.Outlined.Search,
//                    null,
//                    modifier = Modifier.padding(start = 12.dp),
//                    tint = Color.Black.copy(alpha = 0.6f)
//                )
//            },
//            colors = TextFieldDefaults.colors(
//                focusedIndicatorColor = Color.Black.copy(0.7f),
//                unfocusedLabelColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Black.copy(alpha = 0.6f),
//                focusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color.Transparent,
//                cursorColor = Color.Black.copy(0.4f)
//            ),
//            trailingIcon = {
//                Icon(
//                    Icons.Default.KeyboardVoice,
//                    null,
//                    modifier = Modifier.padding(end = 14.dp),
//                    tint = Color.Black.copy(alpha = 0.6f)
//                )
//            }
//        )

        if (books.isEmpty()) {
            Column(Modifier.fillMaxSize()) {
                SimmeringPlaceholder()
                SimmeringPlaceholder()
                SimmeringPlaceholder()
                SimmeringPlaceholder()
                SimmeringPlaceholder()
                SimmeringPlaceholder()

            }
        } else if (books.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No books found.",
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = myCustomFontFamily),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
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
                items(books.size) { index ->
                    BookItem(
                        bookskey = books[index].key,
                        bookImage = books[index].bookimage,
                        bookName = books[index].bookname,
                        bookpdflink = books[index].booklink,
                        bookCategory = books[index].catagori,
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
@Preview(showSystemUi = true)
@Composable
fun SimmeringPlaceholder() {
    Row(
        modifier = Modifier
            .shimmer() // <- Affects all subsequent UI elements
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .size(100.dp, 150.dp)
                .background(Color.LightGray),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(Color.LightGray),
            )
            Box(
                modifier = Modifier
                    .size(140.dp, 26.dp)
                    .background(Color.LightGray),
            )
        }
    }
}
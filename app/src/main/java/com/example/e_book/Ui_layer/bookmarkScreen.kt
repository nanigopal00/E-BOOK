package com.example.e_book.Ui_layer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.example.e_book.Doman.bookviewmodel
import com.example.e_book.R
import com.example.e_book.ui.theme.myColor
import com.example.e_book.ui.theme.myCustomFontFamily
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun bookmarkScreen(viewmodel: bookviewmodel = viewModel(), navcontroll: NavHostController) {
   Scaffold (Modifier.fillMaxSize(),
       topBar = {
           TopAppBar(
               navigationIcon = {
                   IconButton(onClick = {
                       navcontroll.popBackStack()
                   },
                       Modifier
                           .size(37.dp)
                           .padding(start = 8.dp), colors = IconButtonDefaults.iconButtonColors(
                       containerColor  =Color.White.copy(0.4f)
                   )) {
                       Icon(Icons.Filled.ArrowBack, contentDescription = "",Modifier.size(31.dp),tint = Color.Black)

                   }

               },
               title = {

                   Text(text = "My Bookmarks ", color = myColor, modifier = Modifier.padding(start = 7.dp),
                       style = MaterialTheme.typography.titleMedium, fontFamily = myCustomFontFamily,
                       fontWeight = FontWeight.Bold,
                       fontSize = 23.sp
                   )
               },
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.White.copy(0.4f),
                   titleContentColor = Color.Black,
               ),
               modifier = Modifier.background(
                   color = Color.White
               )


           )

       })  { it->
       Column(
           Modifier
               .fillMaxSize()
               .padding(it)) {
           val searchItemValue = rememberSaveable { mutableStateOf("") }
           val focusRequester = remember { FocusRequester() }
           val isTextFieldFocused = remember { mutableStateOf(false) }
           var bookmarksbook = viewmodel.getbooksRoom.collectAsState(initial = emptyList())
           Column(Modifier.background(Color.White.copy(0.2f))) {
               OutlinedTextField(
                   onValueChange = { searchItemValue.value = it },
                   value = searchItemValue.value,
                   singleLine = true,
                   modifier = Modifier
                       .height(55.dp)
                       .fillMaxWidth()
                       .padding(horizontal = 15.dp)
                       .focusRequester(focusRequester)
                       .onFocusChanged { focusState ->
                           isTextFieldFocused.value = focusState.isFocused
                       },
                   shape = RoundedCornerShape(15.dp),
                   placeholder = {
                       Text("Search Books", color = Color.Black,
                           fontSize = 13.sp,
                           modifier = Modifier.shimmer())
                   },
                   leadingIcon = {
                       Icon(
                           Icons.Outlined.Search,
                           null,
                           modifier = Modifier.padding(start = 12.dp).shimmer(),
                           tint = Color.Black
                       )
                   },
                   colors = TextFieldDefaults.colors(
                       focusedIndicatorColor =  myColor,
                       unfocusedLabelColor = Color.Transparent,
                       unfocusedIndicatorColor = myColor,
                       focusedContainerColor = Color.Transparent,
                       unfocusedContainerColor = Color.Transparent,
                       cursorColor = Color.Black.copy(0.4f)
                   ),
                   trailingIcon = {
                       if (isTextFieldFocused.value && searchItemValue.value.isNotEmpty()) {
                           IconButton(onClick = {
                               searchItemValue.value = ""
                               focusRequester.freeFocus()
                           }) {
                               Icon(
                                   Icons.Outlined.Cancel,
                                   null,
                                   modifier = Modifier.padding(end = 14.dp),
                                   tint = Color.Black.copy(alpha = 0.5f)
                               )
                           }
                       }
                   }
               )
               Spacer(Modifier.height(8.dp))


           }
           Text(
               "All Books",
               modifier = Modifier
                   .padding(start = 13.dp, top = 4.dp),
               fontSize = 17.sp,
               fontWeight = FontWeight.Bold,
               color = Color.Black,
               style = MaterialTheme.typography.headlineLarge.copy(
                   fontWeight = FontWeight.Bold,
                   fontFamily = myCustomFontFamily))
           Spacer(Modifier.height(1.dp))

           LazyColumn(
               Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.spacedBy(2.dp),
               contentPadding = PaddingValues(3.dp)
           ) {
               items(bookmarksbook.value.filter {
                   it.bookname .contains(searchItemValue.value)
               }) {
                   val interactionSource = remember { MutableInteractionSource() }
                 //  var isFilled by remember { mutableStateOf(false) } // State for the icon
                   Column(modifier = Modifier.clickable(
                       interactionSource = interactionSource,
                       indication = rememberRipple(color = myColor),
                       onClick = {
                        navcontroll.navigate(
                            MainActivity.pdfscreen( key = it.key.toString(), bookimage = it.bookimage, booklink = it.booklink, bookname = it.bookname, catagori = it.catagori)
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
                                       .data(it.bookimage)
                                       .size(Size(80, 145))
                                       .build(),
                                   contentDescription = "Book Image",
                                   modifier = Modifier.size(width = 80.dp, height = 135.dp),
                                   contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                               )
                           }
                           Spacer(Modifier.widthIn(13.dp))
                           Column(
                               modifier = Modifier.size(height = 160.dp, width = 235.dp),
                               verticalArrangement = Arrangement.Center
                           ) {

                               Text(
                                   text = it.bookname,
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
                                       text = it.catagori,
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
                               GlobalScope.launch {
                                   viewmodel.datase.getDao().deleateBooks(it)
                               }
                               Toast.makeText(contaxt, " Deleted Bookmarks ", Toast.LENGTH_SHORT).show()



                           }, modifier = Modifier.size(38.dp)) {
                               Icon(
                                   imageVector = Icons.Filled.Delete, // Use the state here
                                   contentDescription = "",
                                   modifier = Modifier.size(24.dp),
                                   tint = Color.Red.copy(0.7f)
                               )
                           }
                       }
                       HorizontalDivider(modifier = Modifier.shimmer().padding(horizontal = 3.dp))
                   }

               }
           }





       }







   }

}
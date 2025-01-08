package com.example.e_book.Ui_layer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_book.Doman.bookviewmodel
import com.example.e_book.Doman.database.BooksTable
import com.example.e_book.ui.theme.myColor
import com.example.e_book.ui.theme.myCustomFontFamily
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pdfscreenB(
    navController: NavHostController,
    key: String,
    bookimage: String,
    booklink: String,
    bookname: String,
    catagori: String,
    viewmodel: bookviewmodel,

    ) {
var contaxt = LocalContext.current
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
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
                        text = bookname,
                        color =Color.Black.copy(0.9f),
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
                actions = {
                    IconButton(onClick = {
                       GlobalScope.launch {
                           if (key != null) {
                               viewmodel.datase.getDao().insertBooks(BooksTable(key = key.toLong(), bookimage = bookimage, booklink = booklink, bookname = bookname, catagori = catagori))
                           }
                       }
                        Toast.makeText(contaxt, " Book Added Your Bookmark", Toast.LENGTH_SHORT).show()

                    },
                        Modifier
                            .size(42.dp)
                            .padding(end = 8.dp), colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background.copy(0.7f)
                    )) {
                        Icon(Icons.Rounded.StarBorder, contentDescription = "",Modifier.size(27.dp),tint = Color.Black)

                    }
                },
                modifier = Modifier
                    .shadow(
                        elevation = 1.dp,
                        spotColor = Color.Black.copy(0.2f)
                    )
                    .background(Color.White.copy(0.7f))
            )
        }
    ) { innerPadding ->
        BouquetPdfViewer(booklink, Modifier.padding(innerPadding))
    }
}

@Composable
fun BouquetPdfViewer(pdfLink: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(pdfLink),
        isZoomEnable = true
    )
    var isPdfLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = pdfState.currentPage, key2 = pdfState.error) {
        if (pdfState.currentPage == 0 && pdfState.error == null) {
            isPdfLoading = true
        } else {
            isPdfLoading = false
        }
    }
    LaunchedEffect(key1 = pdfState.error) {
        pdfState.error?.let {
            isPdfLoading = false
            Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (isPdfLoading) {
            ElevatedCard(modifier = Modifier.background(Color.White.copy(0.1f))
                .size(56.dp).align(Alignment.Center), shape = RoundedCornerShape(90.dp)
                ) {
                Column(Modifier.size(55.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(modifier = Modifier
                        .size(37.dp),
                        color = myColor,strokeWidth = 4.dp)
                }
            }
        }
        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        )
    }


}


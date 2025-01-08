package com.example.e_book.Ui_layer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_book.Doman.bookviewmodel
import com.example.e_book.R
import com.example.e_book.common.tabitem
import com.example.e_book.ui.theme.drwercolour
import com.example.e_book.ui.theme.myColor
import com.example.e_book.ui.theme.myCustomFontFamily
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun home(
    navcontroll: NavHostController = NavHostController(context = LocalContext.current),
    viewmodel: bookviewmodel,
    ) {
    val tabItems = arrayOf(
        tabitem("Category", iconselect = Icons.Filled.Category, iconnotselected =Icons.Outlined.Category ),
        tabitem("Books", iconselect = Icons.Filled.Book, iconnotselected =Icons.Outlined.Book ),
//       tabitem("Books", iconselect = Icons.Filled.Bookmark, iconnotselected =Icons.Outlined.BookmarkBorder ),
//        tabitem("Books", iconselect = Icons.Filled.Menu, iconnotselected =Icons.Outlined.Menu )
    )
    var scope = rememberCoroutineScope()
    var pagerstate = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f){
        tabItems.size
    }
    var drowerselect = remember {
        mutableStateOf(0)
    }
    var contaxt = LocalContext.current
    var drowerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        scrimColor = Color.Transparent,
        drawerState = drowerstate,
        drawerContent = {
            ModalDrawerSheet(
                Modifier.widthIn(min = 200.dp, max = 320.dp),
                drawerShape = RoundedCornerShape(20.dp),
                drawerContainerColor = MaterialTheme.colorScheme.onPrimary,
                drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
                windowInsets = WindowInsets(0.dp)



            ) {
               Drowerheader()
                Spacer(Modifier.heightIn(10.dp))
                Spacer(Modifier.heightIn(30.dp))
                NavigationDrawerItem(
                    label = { Text("Share",
                        fontWeight = FontWeight.Bold,
                        color =  if (drowerselect.value==0) myColor else Color.Black
                    ) },
                    icon = { Icon(Icons.Outlined.Share, contentDescription = "Share",
                        tint = if (drowerselect.value==0) myColor else Color.Black) },
                    selected = drowerselect.value==0,
                    onClick = {

                        drowerselect.value =0
                        scope.launch { drowerstate.close() }
                        Toast.makeText(contaxt, "Share ", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(Modifier.heightIn(14.dp))
                NavigationDrawerItem(
                    label = { Text("Bookmark",
                        fontWeight = FontWeight.Bold,
                        color =  if (drowerselect.value==1) myColor else Color.Black
                   ) },
                    icon = { Icon(Icons.Outlined.Bookmark, contentDescription = "Bookmark",
                        tint = if (drowerselect.value==1)drwercolour else Color.Black) },
                    selected = drowerselect.value==1,
                    onClick = {
                       drowerselect.value =1
                        scope.launch { drowerstate.close() }
                        navcontroll.navigate(
                            MainActivity.bookmark
                        )
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(Modifier.heightIn(14.dp))
                NavigationDrawerItem(
                    label = { Text("Version",
                        fontWeight = FontWeight.Bold,
                      color =   if (drowerselect.value==2) myColor else Color.Black
                    ) },
                    icon = { Icon(Icons.Outlined.Error, contentDescription = "Version",
                        tint = if (drowerselect.value==2) myColor else Color.Black) },
                    selected = drowerselect.value==2,
                    onClick = {

                        drowerselect.value =2
                        scope.launch { drowerstate.close() }
                        Toast.makeText(contaxt, "Current Virsion 1.0", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(Modifier.heightIn(14.dp))
                NavigationDrawerItem(
                    label = { Text("Bug Report",
                        fontWeight = FontWeight.Bold,
                        color = if (drowerselect.value==3) myColor else Color.Black
                    ) },
                    icon = { Icon(Icons.Outlined.BugReport, contentDescription = "Email",
                        tint = if (drowerselect.value==3) myColor else Color.Black) },
                    selected = drowerselect.value==3,
                    onClick = {
                        // implement email

                        drowerselect.value =3
                        scope.launch { drowerstate.close() }
                        Toast.makeText(contaxt, "Contact Soon..", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(Modifier.heightIn(14.dp))
                NavigationDrawerItem(
                    label = { Text("Source Code",
                        fontWeight = FontWeight.Bold,
                        color = if (drowerselect.value==4) myColor else Color.Black
                    ) },
                    icon = { Icon(Icons.Outlined.Code, contentDescription = "Email",
                        tint = if (drowerselect.value==4) myColor else Color.Black) },
                    selected = drowerselect.value==4,
                    onClick = {
                        // implement Github link

                        drowerselect.value =4
                        scope.launch { drowerstate.close() }
                        Toast.makeText(contaxt, "Code are Avilable Soon..", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                Spacer(Modifier.heightIn(60.dp))
                HorizontalDivider(Modifier.padding(horizontal = 30.dp))
                Spacer(Modifier.heightIn(30.dp))
               ElevatedCard( modifier = Modifier.heightIn(25.dp).widthIn(60.dp).align(Alignment.CenterHorizontally),
                   colors = CardDefaults.cardColors(
                       containerColor = Color.White
                   )) {
                   Text(text = "1 . 0", color = Color.Black.copy(0.9f), modifier = Modifier.align(Alignment.CenterHorizontally),
                       style = MaterialTheme.typography.titleMedium,
                       fontWeight = FontWeight.ExtraBold,
                       fontSize = 14.sp
                   )
               }





            }

        },

    )  {
        Scaffold(Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                          scope.launch {
                              drowerstate.open()
                          }
                        },
                            Modifier
                                .size(42.dp)
                                .padding(end = 0.dp), colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(0.7f)
                        )) {
                            Icon(Icons.Rounded.Menu, contentDescription = "",Modifier.size(29.dp),tint = Color.Black)

                        }
                    },
                    title = {

                        Text(text = "Bookly", color = myColor, modifier = Modifier.padding(start = 6.dp),
                            style = MaterialTheme.typography.titleMedium, fontFamily = myCustomFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 34.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor =Color.White.copy(0.2f),
                        titleContentColor = Color.Black,
                    ), actions = {
                        IconButton(onClick = {},
                            Modifier
                                .size(42.dp)
                                .padding(end = 0.dp), colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(0.7f)
                        )) {
                            Icon(Icons.Outlined.Search, contentDescription = "",Modifier.size(33.dp),tint = Color.Black)

                        }
                        Spacer(Modifier.widthIn(9.dp))
                        IconButton(onClick = {
                            navcontroll.navigate(MainActivity.bookmark)
                        },
                            Modifier
                                .size(42.dp)
                                .padding(end = 0.dp), colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(0.7f)
                        )) {
                            Icon(Icons.Filled.Bookmark, contentDescription = "",Modifier.size(26.dp),tint = myColor)

                        }
                        Spacer(Modifier.widthIn(4.dp))
                    }

                )

            }) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {
                TabRow(selectedTabIndex = pagerstate.settledPage,
                    containerColor = Color.White.copy(0.2f),
                    modifier = Modifier,
                    contentColor = Color.Black,
                    indicator = { tabPositions ->
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[pagerstate.currentPage])
                                .height(3.dp)
                                .background(myColor)
                        )
                    }

                ) {
                    tabItems.forEachIndexed { index, item ->
                        Tab(selected = pagerstate.currentPage==index, onClick = {
                            scope.launch {
                                pagerstate.animateScrollToPage(index)

                            }

                        },
                            icon = {
                                Icon(
                                    imageVector = if (pagerstate.currentPage == index) item.iconselect else item.iconnotselected,
                                    contentDescription = "", modifier = Modifier.size(
                                        if (pagerstate.currentPage == index) 30.dp else 26.dp
                                    ),tint = if (pagerstate.currentPage==index) myColor else Color.Black
                                )
                            }
                        )
                    }

                }
                HorizontalPager(state = pagerstate,modifier= Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(0.2f))) { currentpage->
                    when(currentpage){
                        0->{
                            catagoriscreen(viewmodel, navcontroll = navcontroll)

                        }
                        1->{
                            bookscreen(viewmodel,navcontroll)



                        }


                    }


                }
            }


        }

    }

   }

@Composable
fun Drowerheader() {
  Row  (
      Modifier
          .fillMaxWidth()
          .height(260.dp).background(drwercolour.copy(0.1f))) {
     Column(
         Modifier
             .fillMaxSize()
             .background(
                 color = Color.Transparent
             ),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center) {
         Spacer(Modifier.heightIn(29.dp))
         Icon(painter = painterResource(R.drawable.iconbook), contentDescription = null, modifier = Modifier.size(80.dp),
             tint = Color.Red.copy(0.8f))
         Text(text = " Explore Bookly", color = myColor, modifier = Modifier.padding(top = 3.dp),
             style = MaterialTheme.typography.titleMedium, fontFamily = myCustomFontFamily,
             fontWeight = FontWeight.Bold,
             fontSize = 26.sp
         )

         Text(text = "Support@booky.com", color = Color.Black.copy(0.8f), modifier = Modifier,
             style = MaterialTheme.typography.titleMedium, fontFamily = myCustomFontFamily,
             fontWeight = FontWeight.Bold,
             fontSize = 14.sp
         )

//         Text(text = "Version 1.0 ", color = Color.Black.copy(0.7f), modifier = Modifier.padding(start = 10.dp),
//             style = MaterialTheme.typography.titleMedium, fontFamily = myCustomFontFamily,
//             fontWeight = FontWeight.Bold,
//             fontSize = 12.sp
//         )
     }


  }
}

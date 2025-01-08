package com.example.e_book.Ui_layer

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Size
import com.example.e_book.Doman.bookviewmodel
import com.example.e_book.ui.theme.myColor
import com.example.e_book.ui.theme.myCustomFontFamily
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun catagoriscreen(viewmodel: bookviewmodel, onCategoryClick: (String) -> Unit = {}, navcontroll:NavHostController) {
    val state = viewmodel.statecatagoriSc.value
    val yourShimmerTheme = defaultShimmerTheme.copy()
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(7.dp)
    ) {
        if (state.state) {
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
        } else if (state.data.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No categories found.",
                    style = MaterialTheme.typography.bodyLarge.copy(fontFamily = myCustomFontFamily),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize()) {
                Text(
                    text = "Explore Categories",
                    fontSize = 23.sp,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = myCustomFontFamily
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 7.dp)
                )

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(state.data) { category ->
                        CategoryItem(
                            imageUrl = category.image,
                            categoryName = category.catagoriname,
                            onCategoryClick = { onCategoryClick(category.catagoriname) },
                            navcontrol = navcontroll
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    imageUrl: String,
    categoryName: String,
    onCategoryClick: () -> Unit,
    navcontrol: NavHostController
) {
    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(color = myColor),
                onClick = onCategoryClick
            )
            .animateContentSize(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().clickable {
            navcontrol.navigate(
                MainActivity.bookbycatagori(catori = categoryName)
            )

        }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .size(Size.ORIGINAL)
                    .crossfade(true)
                    .build(),
                contentDescription = "Category Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            )
                        )
                    )
            )
            Text(
                text = categoryName,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = myCustomFontFamily,
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun ShimmeringPlaceholder() {
    Row(
        modifier = Modifier
            .shimmer() // <- Affects all subsequent UI elements
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .size(80.dp, 80.dp)
                .background(Color.LightGray),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(Color.LightGray),
            )
            Box(
                modifier = Modifier
                    .size(120.dp, 20.dp)
                    .background(Color.LightGray),
            )
        }
    }
}
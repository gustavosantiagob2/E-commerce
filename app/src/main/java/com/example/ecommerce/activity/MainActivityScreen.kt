package com.example.ecommerce.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ecommerce.R.drawable.fav_icon
import com.example.ecommerce.R.drawable.search_icon
import com.example.ecommerce.compenents.Banners
import com.example.ecommerce.compenents.BottomMenu
import com.example.ecommerce.compenents.CategoryList
import com.example.ecommerce.compenents.SectionTitle
import com.example.ecommerce.model.CategoryModel
import com.example.ecommerce.model.ItemsModel
import com.example.ecommerce.model.SliderModel
import com.example.ecommerce.viewmodel.MainViewModel

@Composable
fun MainActivityScreen(navController: NavController, onCartClick: () -> Unit) {
    val viewModel = MainViewModel()
    val banners = remember { mutableStateListOf<SliderModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>() }
    val recommended = remember { mutableStateListOf<ItemsModel>() }
    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showRecommended by remember { mutableStateOf(true) }

//    //Banner
    LaunchedEffect(Unit) {
        viewModel.loadBanners()
        viewModel.banners.observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

//    //Categories
    LaunchedEffect(Unit) {
        viewModel.loadCategory()
        viewModel.categories.observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    //Recommended
    LaunchedEffect(Unit) {
        viewModel.loadRecommended()
        viewModel.recommended.observeForever {
            recommended.clear()
            recommended.addAll(it)
            showRecommended = false
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
    ) {
        val (scrollList, bottomMenu) = createRefs()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(scrollList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = "Welcome Back",
                            color = Color.Black
                        )
//
                        Text(
                            text = "Gustavo",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                    ) {
                        Image(
                            painter = painterResource(fav_icon),
                            contentDescription = "Fav Icon",
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Image(
                            painter = painterResource(search_icon),
                            contentDescription = "Search Icon",
                        )
                    }
                }
            }
            item {
                if (showBannerLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Banners(banners)
                }
            }
            item {
                SectionTitle("Categories", "See All")
            }
            item {
                if (showCategoryLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    CategoryList(categories, navController = navController)
                }
            }
            item {
                SectionTitle("Recommendation", "See All")
            }
            item {
                if (showRecommended) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center

                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    ListItems(recommended, navController)
                }
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
        BottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomMenu) {
                    bottom.linkTo(parent.bottom)
                },
            onItemClick = onCartClick
        )
    }
}


@Preview
@Composable
private fun MainActivityScreenPreview() {
//    MainActivityScreen {}
}
package com.example.ecommerce.activity

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.ecommerce.R
import com.example.ecommerce.R.drawable.fav_icon
import com.example.ecommerce.R.drawable.search_icon
import com.example.ecommerce.compenents.BottomMenu
import com.example.ecommerce.model.CategoryModel
import com.example.ecommerce.model.ItemsModel
import com.example.ecommerce.model.SliderModel
import com.example.ecommerce.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainActivityScreen( navController: NavController,onCardClick: () -> Unit) {
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
            onItemClick = onCardClick
        )
    }
}

@SuppressLint("AutoboxingStateCreation")
@Composable
fun CategoryList(categories: List<CategoryModel>, navController: NavController) {
    var selectedIndex by remember { mutableStateOf(-1) }
    var selectedCategory by remember { mutableStateOf<CategoryModel?>(null) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 8.dp)
    ) {
        items(categories.size) { index: Int ->
            CategoryItem(
                categories[index],
                isSelected = selectedIndex == index,
                onItemClicked = {
                    selectedIndex = index
                    selectedCategory = categories[index]

                }
            )
        }
    }

    LaunchedEffect(selectedIndex) {
        if (selectedIndex != -1 ) {
            delay(1000)
            val encodedTitle = URLEncoder.encode(selectedCategory?.title, StandardCharsets.UTF_8.toString())
            navController.navigate("list/${selectedCategory?.id}/$encodedTitle")

        }
    }
}


@Composable
fun Nav(selectedIndex: Int, navController: NavController, category: CategoryModel){
    // LaunchedEffect fora do LazyRow para evitar que seja chamado em cada item

}

@Composable
fun CategoryItem(
    item: CategoryModel,
    isSelected: Boolean,
    onItemClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { onItemClicked() }
            .background(
                color = if (isSelected) colorResource(R.color.purple_500) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.picUrl,
            contentDescription = item.title,
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = if (isSelected) Color.Transparent else Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentScale = ContentScale.Inside,
            colorFilter = if (isSelected) {
                ColorFilter.tint(Color.White)
            } else {
                ColorFilter.tint(Color.Black)
            }
        )

        if (isSelected) {
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
        }
    }
}


@Composable
fun Banners(banners: List<SliderModel>) {
    AutoSlidingCarousel(banners = banners)
}

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    banners: List<SliderModel>,
    pagerState: PagerState = rememberPagerState(pageCount = { banners.size }),
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = banners.size
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banners[page].url)
                    .build(),
                contentDescription = "Image for url page",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(16.dp, 16.dp, 16.dp, 8.dp)
                    .height(200.dp)
            )
        }

        DotsIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banners.size,
            selectedIndex = if (isDragged) pagerState.currentPage else pagerState.currentPage,
            dotSize = 8.dp
        )
    }
}


@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.purple_500),
    unSelectedColor: Color = Color.Gray,
    dotSize: Dp,
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }

        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    ) {

    }
}

@Composable
fun SectionTitle(title: String, actionText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = actionText,
            color = colorResource(R.color.purple_500),
        )
    }
}


@Preview
@Composable
private fun MainActivityScreenPreview() {
//    MainActivityScreen {}
}
package com.example.ecommerce.compenents

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.ecommerce.model.SliderModel


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

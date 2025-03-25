package com.example.ecommerce.compenents

import androidx.compose.runtime.Composable
import com.example.ecommerce.model.SliderModel

@Composable
fun Banners(banners: List<SliderModel>) {
    AutoSlidingCarousel(banners = banners)
}
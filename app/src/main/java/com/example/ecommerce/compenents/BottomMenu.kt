package com.example.ecommerce.compenents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Composable
fun BottomMenu(modifier: Modifier = Modifier, onItemClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            .background(colorResource(R.color.purple_500), shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        BottomMenuItem(painterResource(R.drawable.btn_1),"Explore")
        BottomMenuItem(painterResource(R.drawable.btn_2),"Cart", onItemClick)
        BottomMenuItem(painterResource(R.drawable.btn_3),"Favorite")
        BottomMenuItem(painterResource(R.drawable.btn_4),"Orders")
        BottomMenuItem(painterResource(R.drawable.btn_5),"Profile")
    }
}
package com.example.ecommerce.compenents

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecommerce.R
import com.example.ecommerce.model.ItemsModel
import com.google.gson.Gson


@Composable
fun RecommendedItem(items: List<ItemsModel>, pos: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .height(255.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = items[pos].picUrl.firstOrNull(),
            contentDescription = items[pos].title,
            modifier = Modifier
                .width(255.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                .height(175.dp)
                .padding(8.dp)
                .clickable {
                    navController.navigate("detail/${Uri.encode(Gson().toJson(items[pos]))}")
                },
            contentScale = ContentScale.Inside
        )
        Text(
            text = items[pos].title,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "R$ ${items[pos].price}",
                color = colorResource(R.color.purple_500),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = "Rating",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = items[pos].rating.toString(),
                color = Color.Black,
                fontSize = 15.sp,
            )
        }
    }
}

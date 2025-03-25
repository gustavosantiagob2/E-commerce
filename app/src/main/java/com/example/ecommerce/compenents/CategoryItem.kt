package com.example.ecommerce.compenents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ecommerce.R
import com.example.ecommerce.model.CategoryModel


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


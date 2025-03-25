package com.example.ecommerce.compenents

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerce.model.CategoryModel
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


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

package com.example.ecommerce.compenents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerce.model.ItemsModel

@Composable
fun ListItemsFullSize(items: List<ItemsModel>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(
            items.size
        ) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RecommendedItem(items, row, navController)
            }
        }

    }
}
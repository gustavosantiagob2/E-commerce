package com.example.ecommerce.activity

import ManagmentCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ecommerce.model.ItemsModel
import com.example.ecommerce.route.Screen

@Composable
fun DetailActivity(navController: NavController, item: ItemsModel) {
    var context = LocalContext.current
    var menagmentCard = remember { ManagmentCart(context) }

    DetailScreen(
        item = item ,
        onBackClick = { navController.popBackStack() },
        onAddToCartClick = {
            item.numberInCard
            menagmentCard.insertFood(item)
        },
        onCartClick = {
            navController.navigate(Screen.Cart.route)
        }
    )

}
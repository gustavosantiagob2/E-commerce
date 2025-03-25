package com.example.ecommerce.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ecommerce.compenents.ListItemScreen
import com.example.ecommerce.viewmodel.MainViewModel

@Composable
fun ListItemActivity(id: String,title: String,  onBackClick: () -> Unit, navController: NavController) {
    val viewModel = MainViewModel()
    ListItemScreen(
        title = title,
        onBackClick = onBackClick,
        viewModel = viewModel,
        id = id,
        navController = navController
    )

}




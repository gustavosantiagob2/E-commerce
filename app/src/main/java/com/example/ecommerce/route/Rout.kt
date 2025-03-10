package com.example.ecommerce.route

sealed class Screen(val route: String){
    object Start: Screen("start")
    object Welcome: Screen("welcome")
}
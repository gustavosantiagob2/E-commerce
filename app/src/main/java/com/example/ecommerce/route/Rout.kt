package com.example.ecommerce.route

sealed class Screen(val route: String){
    object Start: Screen("start")
    object Welcome: Screen("welcome")
    object List: Screen("list/{id}/{title}")
    object Detail: Screen("detail/{items}")
}
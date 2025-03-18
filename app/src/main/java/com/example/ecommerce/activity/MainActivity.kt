package com.example.ecommerce.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecommerce.model.ItemsModel
import com.example.ecommerce.route.Screen
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                NavigationRout()
            }
        }
    }
}

@Composable
fun NavigationRout(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            IntroScreen {
                navController.navigate(Screen.Start.route)
            }
        }
        composable(Screen.Start.route) {
            MainActivityScreen(navController = navController) {}
        }
        composable(Screen.List.route,
            arguments =
            listOf(
                navArgument("id"){ type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""

            ListItemActivity(
                id = id,
                title = title,
                onBackClick = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("items") { type = NavType.StringType })
            ) {backStackEntry ->
            val json = backStackEntry.arguments?.getString("items")
            val items = Gson().fromJson(json,ItemsModel::class.java)

            DetailActivity(
                navController = navController,
                item = items
            )

        }

    }
}
package com.example.ecommerce.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ecommerce.route.NavigationRout
import com.example.ecommerce.ui.theme.EcommerceTheme

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

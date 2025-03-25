package com.example.ecommerce.compenents

import ManagmentCart
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerce.model.ItemsModel

@Composable
fun CartList(
    cartItems: ArrayList<ItemsModel>,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        items(cartItems) { item ->
            CartItem(
                cartItems,
                item,
                managmentCart,
                onItemChange
            )
        }
    }
}

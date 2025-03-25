package com.example.ecommerce.activity

import ManagmentCart
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ecommerce.R
import com.example.ecommerce.compenents.CartList
import com.example.ecommerce.compenents.CartSummary
import com.example.ecommerce.util.calculatorCart

@Composable
fun CartScreen(
    menagmentCart: ManagmentCart = ManagmentCart(LocalContext.current),
    onBackClick: () -> Unit,
) {
    var cartItems = remember { mutableStateOf(menagmentCart.getListCart()) }
    var tax = remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 36.dp)
        ) {
            val (backBtn, cartTxt) = createRefs()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartTxt) {
                        centerTo(parent)
                    },
                text = "Your Cart",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = "back page",
                modifier = Modifier
                    .clickable {
                        onBackClick()
                    }
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
        }
        if (cartItems.value.isEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "Cart is empty",
            )
        } else {
            CartList(
                cartItems = cartItems.value,
                managmentCart = menagmentCart,
            ) {
                cartItems.value = menagmentCart.getListCart()
                calculatorCart(menagmentCart, tax)
            }

            CartSummary(
                itemTotal = menagmentCart.getTotalFee(),
                tax = tax.value,
                delivery = 10.0
            )
        }
    }
}

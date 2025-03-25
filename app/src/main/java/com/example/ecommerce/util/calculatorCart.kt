package com.example.ecommerce.util

import ManagmentCart
import androidx.compose.runtime.MutableState

fun calculatorCart(menagmentCart: ManagmentCart, tax: MutableState<Double>) {
    val percenterTax = 0.02
    tax.value = Math.round((menagmentCart.getTotalFee() * percenterTax * 100)) / 100.0
}
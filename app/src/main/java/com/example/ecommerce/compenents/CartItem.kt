package com.example.ecommerce.compenents

import ManagmentCart
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.rememberAsyncImagePainter
import com.example.ecommerce.Helper.ChangeNumberItemsListener
import com.example.ecommerce.R
import com.example.ecommerce.model.ItemsModel


@Composable
fun CartItem(
    cartItems: ArrayList<ItemsModel>,
    item: ItemsModel,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        val (pic, titleText, feeEachItem, Quantity) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(item.picUrl[0]),
            contentDescription = "item image",
            modifier = Modifier
                .size(90.dp)
                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp)
                .constrainAs(pic) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = item.title,
            modifier = Modifier
                .constrainAs(titleText) {
                    start.linkTo(pic.end)
                    top.linkTo(parent.top)
                }
                .padding(start = 8.dp, top = 8.dp)
        )
        Text(
            text = "$${item.price}",
            color = colorResource(R.color.purple_200),
            modifier = Modifier
                .constrainAs(feeEachItem) {
                    start.linkTo(pic.end)
                    top.linkTo(titleText.bottom)
                }
                .padding(start = 8.dp, top = 8.dp)
        )
        Text(
            text = "$${item.numberInCard * item.price}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(feeEachItem) {
                    start.linkTo(pic.end)
                    top.linkTo(titleText.bottom)
                }
                .padding(start = 8.dp)
        )
        ConstraintLayout(
            modifier = Modifier
                .width(100.dp)
                .constrainAs(Quantity) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
        ) {
            val (plusCartBtn, minusCartBtn, numberItemText) = createRefs()
            Text(
                text = item.numberInCard.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(numberItemText) {
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        colorResource(R.color.purple_200),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .constrainAs(plusCartBtn) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        managmentCart.plusItem(
                            cartItems,
                            cartItems.indexOf(item),
                            object : ChangeNumberItemsListener {
                                override fun onChanged() {
                                    onItemChange()
                                }
                            }
                        )
                    }
            ) {
                Text(
                    text = "+",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .constrainAs(minusCartBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        managmentCart.minusItem(
                            cartItems,
                            cartItems.indexOf(item),
                            object : ChangeNumberItemsListener {
                                override fun onChanged() {
                                    onItemChange()
                                }
                            }
                        )
                    }
            ) {
                Text(
                    text = "-",
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
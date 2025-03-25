package com.example.ecommerce.activity

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.rememberAsyncImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.compenents.ImageThumbnail
import com.example.ecommerce.compenents.ModelSelector
import com.example.ecommerce.compenents.RatingBar
import com.example.ecommerce.model.ItemsModel

@SuppressLint("AutoboxingStateCreation")
@Composable
fun DetailScreen(
    item: ItemsModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit,
) {
    var selectedImageImageUrl by remember { mutableStateOf(item.picUrl.first()) }
    var selectedModelIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 36.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            val (back, fav) = createRefs()

            Image(
                painterResource(R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { onBackClick() }
                    .constrainAs(back) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
            Image(
                painter = painterResource(R.drawable.fav_icon),
                contentDescription = "favorite",
                modifier = Modifier
                    .constrainAs(fav){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model = selectedImageImageUrl),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            items(item.picUrl){imageUrl ->
                ImageThumbnail(
                    imageUrl = imageUrl,
                    isSelected = selectedImageImageUrl == imageUrl,
                    onClick = {
                        selectedImageImageUrl = imageUrl
                    }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 23.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(end = 16.dp)
            )
            Text(
                text = "$${item.price}",
                fontSize = 22.sp
            )
        }
        RatingBar(rating = item.rating)
        ModelSelector(
            models = item.model,
            selectedModelIndex = selectedModelIndex,
            onModelSelected = {selectedModelIndex = it}
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = item.description,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(
                onClick = onAddToCartClick,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.purple_500)),
                modifier = Modifier
                    .weight(1F)
                    .padding(end = 8.dp)
                    .height(50.dp)
            ) {
                Text (text = "Buy Now", fontSize = 18.sp)
            }
            IconButton(
                onClick = onCartClick,
                modifier = Modifier
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.btn_2),
                    contentDescription = "Cart icon",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewDetailScreen() {

}
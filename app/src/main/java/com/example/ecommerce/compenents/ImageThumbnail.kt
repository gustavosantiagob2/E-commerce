package com.example.ecommerce.compenents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.ecommerce.R


@Composable
fun ImageThumbnail(
    imageUrl: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backColor = if (isSelected) colorResource(R.color.purple_500) else Color.LightGray

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(55.dp)
            .then(
                if (isSelected) {
                    Modifier.border(
                        1.dp,
                        colorResource(R.color.purple_500),
                        RoundedCornerShape(10.dp)
                    )
                } else {
                    Modifier
                }
            )
            .background(backColor, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewImageThumbnail() {
    ImageThumbnail(
        imageUrl = "",
        isSelected = true,
        onClick = {}
    )
}
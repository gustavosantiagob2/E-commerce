package com.example.ecommerce.compenents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R


@Composable
fun RatingBar(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Select Model",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1F)
        )
        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = "star icon",
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(
            text = "$rating Rating",
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRatingBar() {
    RatingBar(
        rating = 10.0
    )
}
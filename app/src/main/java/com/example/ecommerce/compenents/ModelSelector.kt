package com.example.ecommerce.compenents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Composable
fun ModelSelector(
    models: List<String>,
    selectedModelIndex: Int,
    onModelSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        itemsIndexed(models){index, model ->
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(48.dp)
                    .then(
                        if (index == selectedModelIndex) {
                            Modifier
                                .border(
                                    1.dp,
                                    colorResource(R.color.purple_500),
                                    RoundedCornerShape(10.dp)
                                )
                        } else {
                            Modifier
                        }
                    )
                    .background(
                        if (index == selectedModelIndex) colorResource(R.color.purple_200) else Color.LightGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onModelSelected(index) }
                    .padding(horizontal = 16.dp)
            ){
                Text(
                    text = model,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = if(index == selectedModelIndex) colorResource(R.color.purple_500) else Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewModelSelector() {
    ModelSelector(
        models = listOf("Model 1", "Model 2", "Model 3"),
        selectedModelIndex = 1,
        onModelSelected = {}
    )
}
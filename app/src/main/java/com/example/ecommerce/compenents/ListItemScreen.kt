package com.example.ecommerce.compenents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.R
import com.example.ecommerce.viewmodel.MainViewModel


@Composable
fun ListItemScreen(
    title: String,
    onBackClick: () -> Unit,
    viewModel: MainViewModel,
    id: String,
    navController: NavController
) {

    val items by viewModel.recommended.observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        viewModel.loadFiltered(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 36.dp, start = 16.dp, end = 16.dp)
        ) {
            val (backBtn, cartTxt) = createRefs()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartTxt) { centerTo(parent) },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                text = title,
                color = Color.Black
            )
            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { onBackClick() }
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
        }
        if(isLoading){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else{
            ListItemsFullSize(items, navController)
        }

        LaunchedEffect(items) {
            isLoading = items.isEmpty()
        }
    }
}


@Preview
@Composable
private fun Prev() {
    val viewModel = MainViewModel()
    val id: String = ""
    val title: String = ""
    var navController = rememberNavController()

    ListItemScreen(
        title = title,
        onBackClick = { navController.popBackStack() },
        viewModel = viewModel,
        id = id,
        navController
    )
}

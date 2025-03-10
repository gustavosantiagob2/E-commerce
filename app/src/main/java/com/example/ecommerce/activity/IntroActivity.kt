package com.example.ecommerce.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import com.example.ecommerce.R.string.intro_sub_title
import com.example.ecommerce.R.string.intro_title
import com.example.ecommerce.R.string.letgo
import com.example.ecommerce.R.string.sign

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 68.dp),
            painter = painterResource(R.drawable.intro_logo),
            contentDescription = "logo of intro",
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(52.dp))

        Text(
            modifier = Modifier
                .padding(top = 36.dp),
            text =  stringResource(intro_title),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(intro_sub_title),
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Button(
            onClick = onclick,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.purple_500)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(letgo),
                color = Color.White,
                fontSize = 18.sp,

            )
        }

        Text(
            modifier = Modifier
                .padding(top = 32.dp),
            text = stringResource(sign),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroScreenPreview() {
    IntroScreen(){}
}
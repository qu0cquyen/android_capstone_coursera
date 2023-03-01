package com.example.littlelemon.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.NavigationDirections
import com.example.littlelemon.R

@Composable
fun HomeViewHeader(navController : NavHostController){
    Image(
        painter = painterResource(id = R.drawable.profile),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
              println("Image Clicked")
                navController.navigate("Profile")
            },
        alignment = Alignment.CenterEnd,
        contentScale = ContentScale.Fit,
        contentDescription = stringResource(id = R.string.app_profile)
    )

    Image(
        painter = painterResource(id = R.drawable.logo),
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .height(50.dp),


        contentScale = ContentScale.Fit,
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

@Composable
fun HomeView(navController : NavHostController) {
    HomeViewHeader(navController)
}
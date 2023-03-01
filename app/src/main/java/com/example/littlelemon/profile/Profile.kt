package com.example.littlelemon.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.NavigationDirections
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.highlightColor1
import com.example.littlelemon.ui.theme.highlightColor2
import com.example.littlelemon.ui.theme.primaryColor2
import com.example.littlelemon.ui.theme.secondaryColor1

@Composable
fun ProfileHeader() {
    val imgModifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .height(50.dp)

    Image(
        painter = painterResource(id = R.drawable.logo),
        modifier = imgModifier,
        contentScale = ContentScale.Fit,
        contentDescription = stringResource(id = R.string.app_logo)
    )
}

@Composable
fun ProfileLabel() {
    Text(
        modifier = Modifier.padding(vertical = 60.dp),
        text = "Personal Information",
        style = MaterialTheme.typography.h1,

        )
}

@Composable
fun ProfileFirstName() {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)
    val firstName = sharedPrefs.getString("first-name", "")



    Text(text = "First name")
    BasicTextField(
        value = firstName ?: "",
        onValueChange = { },
        readOnly = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .border(width = 2.dp, color = highlightColor1, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)

            ) {
                innerTextField()
            }
        })
}

@Composable
fun ProfileLastName() {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)
    val lastName = sharedPrefs.getString("last-name", "")

    Text("Last name")
    BasicTextField(
        value = lastName ?: "",
        onValueChange = {},
        readOnly = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .border(width = 2.dp, color = highlightColor1, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                innerTextField()
            }
        }
    )
}

@Composable
fun ProfileEmail() {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)
    val email = sharedPrefs.getString("email-address", "")

    Text("Email")
    BasicTextField(
        value = email ?: "",
        onValueChange = {},
        readOnly = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .border(width = 2.dp, color = highlightColor1, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                innerTextField()
            }
        }
    )
}

@Composable
fun ProfileLogOutButton(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)


    Button(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(width = 2.dp, color = secondaryColor1),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor2),
        onClick = {
            println("Logout Button Clicked")
            // Clear data in SharedPrefs
            sharedPrefs.edit().clear().commit()
            navController.navigate(
                NavigationDirections.OnBoarding.destination
            ){
                popUpTo(NavigationDirections.OnBoarding.destination)
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true

                // Restore state when selecting a previous selected item
                restoreState = false

            }
//
        }
    ) {
        Text("Log out")
    }
}


@Composable
fun ProfileView(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,


        ) {
        ProfileHeader()
        ProfileLabel()
        ProfileFirstName()
        ProfileLastName()
        ProfileEmail()
        Spacer(modifier = Modifier.weight(0.1F))
        ProfileLogOutButton(navController)


    }

}
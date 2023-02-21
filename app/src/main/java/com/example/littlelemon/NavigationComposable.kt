package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.home.HomeView
import com.example.littlelemon.on_boarding.OnboardingView
import com.example.littlelemon.profile.ProfileView

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context,
) {
    /// Get data from SharedPreferences
    val sharedPreference = context.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)
    val isFirstTime = sharedPreference.getBoolean("on-board", true)
    println(isFirstTime)
    var startDestination : String = if(isFirstTime){
        "Onboarding"
    } else {
        "Home"
    }

    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
        composable("Onboarding") {
            OnboardingView(navController)
        }

        composable("Home") {
            HomeView()
        }

        composable("Profile") {
            ProfileView()
        }
    }
}

//class NavigationComposable {
//    lateinit var navHost: NavHost
//
//
//    constructor(controller: NavController) {
//        navHost = NavHost(controller, )
//    }
//}
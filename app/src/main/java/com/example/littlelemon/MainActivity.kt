package com.example.littlelemon

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController

import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
////                    Greeting("Android")
//                    OnboardingView()
//                }
                val navController = rememberNavController()
                AppNavigation(navController = navController, context = applicationContext)

                val db = MenuDatabase.getInstance(applicationContext)
                val menuDao = db.menuDao()



                runBlocking {

                    val httpClient = HttpClient() {
                        install(ContentNegotiation)
                    }

                    val response: HttpResponse =
                        httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")

                    val stringBody: String = response.body()

                    println(stringBody)


                    val menuObj: MenuNetworkdata =
                        Json.decodeFromString(MenuNetworkdata.serializer(), stringBody)


                    // Save to Database
                    lifecycleScope.launch {
                        // Calling db

                        if(menuDao.getAllMenu().value?.isEmpty() == true){
                            for (menuItem: MenuItemNetwork in menuObj.itemNetwork) {
                                menuDao.insert(
                                    Menu(
                                        menuItem.id,
                                        menuItem.title,
                                        menuItem.description,
                                        menuItem.price,
                                        menuItem.image,
                                        menuItem.category,
                                    )
                                )
                            }
                        }


                    }

                }


            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}
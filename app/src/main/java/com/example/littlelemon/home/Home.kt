package com.example.littlelemon.home

import android.view.MenuItem
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.*
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.highlightColor1
import com.example.littlelemon.ui.theme.primaryColor1
import com.example.littlelemon.ui.theme.primaryColor2
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeViewHeader(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box() {}
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.logo),
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(id = R.string.app_logo)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            modifier = Modifier
                .size(50.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    println("Image Clicked")
                    navController.navigate("Profile")
                },
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(id = R.string.app_profile)
        )
    }
}

@Composable
fun HomeViewHero(
    value: String?,
    valueChangedCallback: (valueChanged: String) -> Unit
) {
    var txt by remember { mutableStateOf(value ?: "") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryColor1)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(
                modifier = Modifier.padding(0.dp),
                text = "Little Lemon",
                color = primaryColor2,
                style = MaterialTheme.typography.h1,
                fontSize = 50.sp,
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {
                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "Chicago",
                        color = Color.White,
                        style = MaterialTheme.typography.h1,
                        fontSize = 25.sp
                    )
                    Text(
                        modifier = Modifier.width(200.dp),
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        color = Color.White,
                        style = MaterialTheme.typography.body1,
                        fontSize = 15.sp,
                        maxLines = 5,
                    )
                }

                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    contentScale = ContentScale.FillWidth,
                )

            }


            TextField(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(highlightColor1),
                value = txt,
                onValueChange = { it ->
                    txt = it
                    valueChangedCallback(it)
                },
                placeholder = { Text("Enter Search Phrase", style = MaterialTheme.typography.h1) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(listItem: List<Menu>, categoryClicked: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 5.dp)
    ) {
        item {
            Text(text = "ORDER FOR DELIVERY!", style = MaterialTheme.typography.h1)
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(highlightColor1)
                        .padding(5.dp)
                        .clickable {
                            ->
                            categoryClicked("Starters")
                        }
                ) {
                    Text(text = "Starters", style = MaterialTheme.typography.h1)
                }
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(highlightColor1)
                        .padding(5.dp)
                        .clickable {
                            ->
                            categoryClicked("Mains")
                        }

                ) {
                    Text(text = "Mains", style = MaterialTheme.typography.h1)
                }
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(highlightColor1)
                        .padding(5.dp)
                        .clickable {
                            ->
                            categoryClicked("Desserts")
                        }
                ) {
                    Text(text = "Desserts", style = MaterialTheme.typography.h1)
                }
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(highlightColor1)
                        .padding(5.dp)
                        .clickable {
                            ->
                            categoryClicked("Drinks")
                        }
                ) {
                    Text(text = "Drinks", style = MaterialTheme.typography.h1)
                }
            }
        }

        items(listItem.size) { index ->
            Column(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Divider(
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(text = listItem[index].title, style = MaterialTheme.typography.h1)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(bottom = 10.dp),
                            text = listItem[index].description,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = "\$${listItem[index].price.toString()}")
                    }
                    GlideImage(
                        modifier = Modifier.width(120.dp),
                        model = listItem[index].image,
                        contentDescription = listItem[index].image.toString()


                    )
                }

            }
        }
    }
}

@Composable
fun HomeView(navController: NavHostController) {

    val context = LocalContext.current

    val db = MenuDatabase.getInstance(context)
    val menuDao = db.menuDao()


    var searchPhrase by remember { mutableStateOf("") }

    var groupBy by remember { mutableStateOf("") }

    val listItem = menuDao.getAllMenu().observeAsState(arrayListOf())

    var menuItems = listItem.value;

    if(searchPhrase.isNotBlank()) {
        menuItems = listItem.value.filter { menu -> menu.title.contains(searchPhrase) }
        groupBy = ""
    }

//    val menu = listItem.value
    if(groupBy.isNotBlank()){
        println("IF CONDITION: ${listItem.value}")
        menuItems = listItem.value.filter { menu -> menu.category.lowercase() == groupBy.lowercase() }

    }




//    var menuItems by remember { mutableStateOf(menu) }
    println("HOME VIEW: ${listItem.value.size}")

    println(menuItems)



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeViewHeader(navController)
        HomeViewHero(searchPhrase, valueChangedCallback = { valueChanged ->
            searchPhrase = valueChanged
        })
        MenuItems(menuItems, categoryClicked = { category ->
            searchPhrase = ""


            groupBy = category

//            val testItem = listItem.value.filter { menu -> menu.category == category.lowercase() }
//
//            println(testItem);
//            menuItems = testItem
        })
    }

}
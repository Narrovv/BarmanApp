package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.DataProvider
import com.example.myapplication.data.Drinki
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.background
import android.content.Context
import kotlin.math.sqrt
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme(darkTheme = true) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun Context.isTablet(): Boolean {
    val metrics = resources.displayMetrics
    val screenWidthInch = metrics.widthPixels / metrics.xdpi
    val screenHeightInch = metrics.heightPixels / metrics.ydpi
    val diagonalInch = sqrt(screenWidthInch.pow(2) + screenHeightInch.pow(2))
    return diagonalInch >= 7.0
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    val isTablet = context.isTablet()
    val selectedDrink = remember { mutableStateOf<Drinki?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (isTablet) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    DrinkiContent(navigateTo = { selectedDrink.value = it })
                }
                Column(modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .padding(16.dp)) {

                    selectedDrink.value?.let {
                        DrinkDetailsScreen(
                            drinkName = it.name,
                            drinkRecipe = it.recipe,
                            drinkImage = it.imageId,
                            drinkSteps = it.steps
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Timer(drinkId = it.name)
                    } ?: Box(Modifier.fillMaxSize()) {
                        Text("Wybierz drink z listy", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        } else {
            DrinkiContent(navigateTo = { drink ->
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("drink_id", drink.id)
                    putExtra("drink_name", drink.name)
                    putExtra("drink_recipe", drink.recipe)
                    putExtra("drink_image", drink.imageId)
                    putExtra("drink_steps", drink.steps.toTypedArray())
                }
                context.startActivity(intent)
            })
        }
    }
}

@Composable
fun DrinkiContent(navigateTo: (Drinki) -> Unit, modifier: Modifier = Modifier) {
    val drinki = remember { DataProvider.drinkList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(drinki) { drink ->
            DrinkiListItem(drink = drink, navigateTo)
        }
    }
}

@Composable
fun DrinkiListItem(drink: Drinki, navigateTo: (Drinki) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { navigateTo(drink) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row {
            DrinkImage(drink = drink)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = drink.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Zobacz szczegóły",
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
private fun DrinkImage(drink: Drinki) {
    Image(
        painter = painterResource(id = drink.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
    )
}

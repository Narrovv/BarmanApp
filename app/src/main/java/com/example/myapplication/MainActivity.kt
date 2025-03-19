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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.DataProvider
import com.example.myapplication.data.Drinki
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp { drink ->
                    navigateToDetails(drink)
                }
            }
        }
    }

    private fun navigateToDetails(drink: Drinki) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("drink_id", drink.id)
            putExtra("drink_name", drink.name)
            putExtra("drink_recipe", drink.recipe)
            putExtra("drink_image", drink.imageId) // Dodajemy obrazek
        }
        startActivity(intent)
    }
}

@Composable
fun MyApp(navigateTo: (Drinki) -> Unit) {
    Scaffold(
        content = { paddingValues ->
            DrinkiContent(navigateTo = navigateTo, modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun DrinkiContent(navigateTo: (Drinki) -> Unit, modifier: Modifier = Modifier) {
    val drinki = remember { DataProvider.drinkList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = drinki,
            itemContent = { drink ->
                DrinkiListItem(drink = drink, navigateTo)
            }
        )
    }
}

@Composable
fun DrinkiListItem(drink: Drinki, navigateTo: (Drinki) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { navigateTo(drink) }, // Kliknięcie przenosi do szczegółów
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
                    color = Color.DarkGray
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

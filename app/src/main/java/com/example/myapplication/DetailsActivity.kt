package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drinkName = intent.getStringExtra("drink_name") ?: "Brak nazwy"
        val drinkRecipe = intent.getStringExtra("drink_recipe") ?: "Brak przepisu"
        val drinkImage = intent.getIntExtra("drink_image", 0) // Pobranie ID obrazka

        setContent {
            MyApplicationTheme {
                DrinkDetailsScreen(drinkName, drinkRecipe, drinkImage)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailsScreen(drinkName: String, drinkRecipe: String, drinkImage: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Szczegóły Drinku", fontSize = 18.sp) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = drinkImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = drinkName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = drinkRecipe, fontSize = 18.sp)
        }
    }
}

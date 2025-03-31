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
import androidx.compose.foundation.isSystemInDarkTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemDarkTheme = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(systemDarkTheme) }
            val filterState = remember { mutableStateOf(Filter.ALL) }
            val pagerState = rememberPagerState(initialPage = 0)
            val coroutineScope = rememberCoroutineScope()

            MyApplicationTheme(darkTheme = darkTheme) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .statusBarsPadding()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Switch(
                            checked = darkTheme,
                            onCheckedChange = { darkTheme = it }
                        )
                        Row {
                            Button(
                                onClick = {
                                    filterState.value = Filter.ALL
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(0)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (filterState.value == Filter.ALL) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )

                            ) {
                                Text(
                                    "Wszystkie",
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Button(
                                onClick = {
                                    filterState.value = Filter.ALCOHOLIC
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(1)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (filterState.value == Filter.ALCOHOLIC) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                            ) {
                                Text(
                                    "Alkoholowe",
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Button(
                                onClick = {
                                    filterState.value = Filter.NON_ALCOHOLIC
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(2)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (filterState.value == Filter.NON_ALCOHOLIC) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                ),
                                modifier = Modifier.width(IntrinsicSize.Max)
                            ) {
                                Text(
                                    "Bezalk.",
                                    fontSize = 12.sp,
                                    softWrap = false
                                )
                            }
                        }
                    }
                    HorizontalPager(
                        count = 3,
                        state = pagerState,
                        modifier = Modifier.weight(1f),
                    ) { page ->
                        when (page) {
                            0 -> {
                                LaunchedEffect(pagerState.currentPage) {
                                    if (pagerState.currentPage == page) {
                                        filterState.value = Filter.ALL
                                    }
                                }
                                MyApp(filter = filterState.value, darkTheme = darkTheme)
                            }
                            1 -> {
                                LaunchedEffect(pagerState.currentPage) {
                                    if (pagerState.currentPage == page) {
                                        filterState.value = Filter.ALCOHOLIC
                                    }
                                }
                                MyApp(filter = filterState.value, darkTheme = darkTheme)
                            }
                            2 -> {
                                LaunchedEffect(pagerState.currentPage) {
                                    if (pagerState.currentPage == page) {
                                        filterState.value = Filter.NON_ALCOHOLIC
                                    }
                                }
                                MyApp(filter = filterState.value, darkTheme = darkTheme)
                            }
                        }
                    }
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

enum class Filter {
    ALL, ALCOHOLIC, NON_ALCOHOLIC
}

@Composable
fun MyApp(modifier: Modifier = Modifier, filter: Filter, darkTheme: Boolean) {
    val context = LocalContext.current
    val isTablet = context.isTablet()
    val selectedDrink = remember { mutableStateOf<Drinki?>(null) }

    Scaffold(
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (isTablet) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        DrinkiContent(filter = filter, navigateTo = { selectedDrink.value = it })
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
                DrinkiContent(filter = filter, navigateTo = { drink ->
                    val intent = Intent(context, DetailsActivity::class.java).apply {
                        putExtra("drink_id", drink.id)
                        putExtra("drink_name", drink.name)
                        putExtra("drink_recipe", drink.recipe)
                        putExtra("drink_image", drink.imageId)
                        putExtra("drink_steps", drink.steps.toTypedArray())
                        putExtra("dark_theme", darkTheme)
                    }
                    context.startActivity(intent)
                })
            }
        }
    }
}


@Composable
fun DrinkiContent(filter: Filter, navigateTo: (Drinki) -> Unit, modifier: Modifier = Modifier) {
    val drinki = remember { DataProvider.drinkList }
    val filteredDrinki = remember(filter) {
        when (filter) {
            Filter.ALL -> drinki
            Filter.ALCOHOLIC -> drinki.filter { it.isAlcoholic }
            Filter.NON_ALCOHOLIC -> drinki.filter { !it.isAlcoholic }
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        item {
            MainCard(filter = filter)
        }
        items(filteredDrinki) { drink ->
            DrinkiListItem(drink = drink, navigateTo = navigateTo)
        }
    }
}

@Composable
fun MainCard(filter: Filter) {
    val mainCardText = when (filter) {
        Filter.ALL -> "Witaj w BarmanApp!"
        Filter.ALCOHOLIC -> "Koktajle Alkoholowe"
        Filter.NON_ALCOHOLIC -> "Koktajle Bezalkoholowe"
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = mainCardText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (filter == Filter.ALL) {
                Text(
                    text = "Ta aplikacja pomoże Ci odkryć i przygotować pyszne koktajle. Wybierz swój ulubiony przepis!",
                    fontSize = 16.sp
                )
            }
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

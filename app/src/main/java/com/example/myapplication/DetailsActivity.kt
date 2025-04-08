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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Text


class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drinkName = intent.getStringExtra("drink_name") ?: "Brak nazwy"
        val drinkRecipe = intent.getStringExtra("drink_recipe") ?: "Brak przepisu"
        val drinkImage = intent.getIntExtra("drink_image", 0)
        val drinkSteps = intent.getStringArrayExtra("drink_steps")?.toList() ?: emptyList()
        val darkTheme = intent.getBooleanExtra("dark_theme", true)

        setContent {
            MyApplicationTheme(darkTheme = darkTheme) {
                DrinkDetailsScreen(drinkName, drinkRecipe, drinkImage, drinkSteps)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailsScreen(
    drinkName: String,
    drinkRecipe: String,
    drinkImage: Int,
    drinkSteps: List<String>
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Szczegóły Drinku") },
                navigationIcon = {
                    IconButton(onClick = {
                        (context as? DetailsActivity)?.finish()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "Strona Główna"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { sendSms(context, drinkName, drinkRecipe) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sms_icon),
                    contentDescription = "Wyślij SMS",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Kroki przygotowania:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            drinkSteps.forEachIndexed { index, step ->
                Text(text = "${index + 1}. $step", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Timer(drinkId = drinkName)
        }
    }
}

fun sendSms(context: Context, drinkName: String, drinkRecipe: String) {
    val smsBody = "Składniki na drink '$drinkName':\n$drinkRecipe"

    val phoneNumber = "123456789"

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = if (phoneNumber.isNotEmpty()) {
            "smsto:$phoneNumber".toUri()
        } else {
            "smsto:".toUri()
        }
        putExtra("sms_body", smsBody)
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

class TimerViewModel : ViewModel() {
    var time by mutableStateOf(0L)
    var isRunning by mutableStateOf(false)
    var startTime by mutableStateOf(0L)
    var elapsedTime by mutableStateOf(0L)

    fun startTimer() {
        startTime = System.currentTimeMillis() - elapsedTime
        isRunning = true
    }

    fun stopTimer() {
        isRunning = false
        elapsedTime = time
    }

    fun resetTimer() {
        isRunning = false
        startTime = System.currentTimeMillis()
        elapsedTime = 0L
        time = 0L
    }

    fun updateTime() {
        if (isRunning) {
            time = System.currentTimeMillis() - startTime
        }
    }
}

@Composable
fun Timer(drinkId: String?, timerViewModel: TimerViewModel = viewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(drinkId) {
        // Reset state logic can be managed within the ViewModel or removed
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatTime(timeMi = timerViewModel.time),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(9.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row {
            Button(
                onClick = {
                    if (timerViewModel.isRunning) {
                        timerViewModel.stopTimer()
                    } else {
                        timerViewModel.startTimer()
                        keyboardController?.hide()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                if (timerViewModel.isRunning) {
                    Image(
                        painter = painterResource(id = R.drawable.pause_icon),
                        contentDescription = "Stop",
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.play_icon),
                        contentDescription = "Start",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    timerViewModel.resetTimer()
                },
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reset_icon),
                    contentDescription = "Reset",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

    LaunchedEffect(timerViewModel.isRunning) {
        while (timerViewModel.isRunning) {
            delay(10L)
            timerViewModel.updateTime()
        }
    }
}

private fun formatTime(timeMi: Long): String {
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val hours = TimeUnit.MILLISECONDS.toHours(timeMi) % 24

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

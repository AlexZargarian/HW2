package com.example.hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hw2.ui.theme.HW2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HW2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(showWelcome = true)
                }
            }
        }
    }
}

data class CityInfo(val description: String, val imageUrl: String)

@Composable
fun AppNavigator(showWelcome: Boolean) {
    var showCityList by remember { mutableStateOf(!showWelcome) }
    var selectedCity by remember { mutableStateOf("") }

    if (showCityList) {
        CityListScreen(
            onCitySelected = { city ->
                selectedCity = city
                showCityList = false
            },
            onBack = {
                showCityList = false
            }
        )
    } else {
        if (selectedCity.isNotEmpty()) {
            CityDescriptionScreen(
                cityInfo = getCityInfo(selectedCity),
                onBack = {
                    selectedCity = ""
                    showCityList = true
                }
            )
        } else if (showWelcome) {
            WelcomeScreen(onButtonClick = { showCityList = true })
        }
    }
}

@Composable
fun WelcomeScreen(onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Welcome to my App!",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onButtonClick) {
            Text(text = "Explore Cities")
        }
    }
}

@Composable
fun CityListScreen(onCitySelected: (String) -> Unit, onBack: () -> Unit) {
    val cities = listOf("Yerevan", "Tehran")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "List of Cities",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            cities.forEach { city ->
                Text(
                    text = city,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black),
                    modifier = Modifier.clickable {
                        onCitySelected(city)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Back to Welcome Screen")
        }
    }
}

@Composable
fun CityDescriptionScreen(cityInfo: CityInfo, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = cityInfo.imageUrl,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = cityInfo.description,
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Back to City List")
        }
    }
}



fun getCityInfo(city: String): CityInfo {
    return when (city) {
        "Yerevan" -> CityInfo(
            description = "Yerevan is the capital and largest city of Armenia, and one of the world's oldest continuously inhabited cities. Situated along the Hrazdan River, Yerevan is the administrative, cultural, and industrial center of the country.",
            imageUrl = "https://www.yerevan.am/uploads/media/page_gallery/0002/17/ebe33ce89f819fef4a1089d51c38b273feccd893.jpeg"
        )
        "Tehran" -> {
            CityInfo(
                description = "Tehran is the capital of Iran, in the north of the country. Its central Golestan Palace complex, with its ornate rooms and marble throne, was the seat of power of the Qajar dynasty. The National Jewelry Museum holds many of the Qajar monarchs’ jewels, while the National Museum of Iran has artifacts dating back to Paleolithic times.",
                imageUrl = "https://www.nationsonline.org/gallery/Iran/Azadi-Monument-Tehran2.jpg"
            )
        }
        else -> CityInfo("", "")
    }
}




@Preview(showBackground = true)
@Composable
fun AppNavigatorPreview() {
    HW2Theme {
        AppNavigator(showWelcome = true)
    }
}
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun WeatherInfoScreen(city: String, onBack: () -> Unit) {
    var weatherData by remember(city) { mutableStateOf<String?>(null) }

    LaunchedEffect(city) {
        // Fetch weather data when the selected city changes
        weatherData = fetchWeatherData(city)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = getCityInfo(city).imageUrl,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Temperature: ${weatherData ?: "N/A"}",
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = getCityInfo(city).description,
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Back to City List")
        }
    }
}

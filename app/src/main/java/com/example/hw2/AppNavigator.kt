import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
            WeatherInfoScreen(
                city = selectedCity,
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
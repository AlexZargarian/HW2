import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.IOException
import java.net.URL

suspend fun fetchWeatherData(city: String): String {
    return withContext(Dispatchers.IO) {
        try {
            val apiKey = "24e36c76cec24915aae153838231011"
            val url = "https://api.weatherapi.com/v1/current.json?key=$apiKey&q=$city&aqi=no"
            val response = URL(url).readText()
            Log.d("Weather API", "Response: $response")

            val jsonObject = JSONObject(response)
            if (jsonObject.has("error")) {
                Log.e("Weather API", "Error in weather API response: ${jsonObject.getString("error")}")
                return@withContext "N/A"
            }

            val current = jsonObject.getJSONObject("current")
            val tempC = current.getDouble("temp_c")
            return@withContext "${tempC}°C"
        } catch (e: IOException) {
            Log.e("Weather API", "Error fetching weather data", e)
            return@withContext "N/A"
        }
    }
}

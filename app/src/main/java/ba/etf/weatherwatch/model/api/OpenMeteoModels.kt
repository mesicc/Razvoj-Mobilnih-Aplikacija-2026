package ba.etf.weatherwatch.model.api

import com.google.gson.annotations.SerializedName

data class OpenMeteoResponse(
    @SerializedName("current") val current: CurrentWeather,
    @SerializedName("hourly")  val hourly: HourlyData,
    @SerializedName("daily")   val daily: DailyData
)

data class CurrentWeather(
    @SerializedName("temperature_2m")        val temperatura: Float,
    @SerializedName("apparent_temperature")  val osjecajTemperature: Float,
    @SerializedName("precipitation")         val padavine: Float,
    @SerializedName("weather_code")          val weatherCode: Int,
    @SerializedName("wind_speed_10m")        val brzinaVjetra: Float,
    @SerializedName("wind_direction_10m")    val smjerVjetraStupnjevi: Int,
    @SerializedName("relative_humidity_2m")  val vlaznost: Int,
    @SerializedName("pressure_msl")          val pritisak: Float,
    @SerializedName("cloud_cover")           val oblacnost: Int,
    @SerializedName("uv_index")              val uvIndeks: Float
)

data class HourlyData(
    @SerializedName("time")                       val time: List<String>,
    @SerializedName("temperature_2m")             val temperatura: List<Float>,
    @SerializedName("weather_code")               val weatherCode: List<Int>,
    @SerializedName("precipitation_probability")  val padavinePosto: List<Int>
)

data class DailyData(
    @SerializedName("time")                          val time: List<String>,
    @SerializedName("weather_code")                  val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max")            val maxTemp: List<Float>,
    @SerializedName("temperature_2m_min")            val minTemp: List<Float>,
    @SerializedName("precipitation_probability_max") val padavinePosto: List<Int>
)


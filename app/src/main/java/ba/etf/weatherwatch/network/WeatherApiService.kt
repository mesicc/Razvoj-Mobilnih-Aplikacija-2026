package ba.etf.weatherwatch.network

import ba.etf.weatherwatch.model.api.OpenMeteoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherApiService {

    @GET("v1/forecast")
    suspend fun getForecast(
        @Query("latitude")        latitude: Double,
        @Query("longitude")       longitude: Double,
        @Query("current")         current: String,
        @Query("hourly")          hourly: String,
        @Query("daily")           daily: String,
        @Query("wind_speed_unit") windSpeedUnit: String = "kmh",
        @Query("timezone")        timezone: String = "Europe/Sarajevo",
        @Query("forecast_days")   forecastDays: Int = 7
    ): Response<OpenMeteoResponse>
}

object RetrofitClient {

    private const val BASE_URL = "https://api.open-meteo.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (ba.etf.weatherwatch.BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val weatherApiService: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}

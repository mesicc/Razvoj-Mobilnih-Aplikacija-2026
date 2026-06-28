package ba.etf.weatherwatch

import android.app.Application
import ba.etf.weatherwatch.data.local.WeatherDatabase
import ba.etf.weatherwatch.network.RetrofitClient
import ba.etf.weatherwatch.repository.WeatherRepository

class WeatherWatchApplication : Application() {

    lateinit var repository: WeatherRepository
        private set

    override fun onCreate() {
        super.onCreate()

        val db = WeatherDatabase.getDatabase(this)

        repository = WeatherRepository(
            apiService  = RetrofitClient.weatherApiService,
            prognozaDao = db.prognozaDao(),
            lokacijaDao = db.lokacijaDao()
        )

        instance = this
    }

    companion object {
        lateinit var instance: WeatherWatchApplication
            private set
    }
}
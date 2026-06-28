package ba.etf.weatherwatch.repository

import ba.etf.weatherwatch.data.local.LokacijaDao
import ba.etf.weatherwatch.data.local.LokacijaEntity
import ba.etf.weatherwatch.data.local.PrognozaDao
import ba.etf.weatherwatch.data.local.PrognozaEntityMapper
import ba.etf.weatherwatch.model.Lokacija
import ba.etf.weatherwatch.model.Prognoza
import ba.etf.weatherwatch.network.WeatherApiService
import ba.etf.weatherwatch.network.WeatherMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class WeatherRepository(
    private val apiService: WeatherApiService,
    private val prognozaDao: PrognozaDao,
    private val lokacijaDao: LokacijaDao
) {

    companion object {
        const val CURRENT_PARAMS =
            "temperature_2m,apparent_temperature,precipitation," +
                    "weather_code,wind_speed_10m,wind_direction_10m," +
                    "relative_humidity_2m,pressure_msl,cloud_cover,uv_index"

        const val HOURLY_PARAMS =
            "temperature_2m,weather_code,precipitation_probability"

        const val DAILY_PARAMS =
            "weather_code,temperature_2m_max,temperature_2m_min," +
                    "precipitation_probability_max"
    }

    suspend fun dohvatiPrognozu(lokacija: Lokacija): Prognoza? {
        return try {
            val response = apiService.getForecast(
                latitude  = lokacija.latitude,
                longitude = lokacija.longitude,
                current   = CURRENT_PARAMS,
                hourly    = HOURLY_PARAMS,
                daily     = DAILY_PARAMS
            )
            if (response.isSuccessful) {
                val body = response.body()!!
                val prognoza = WeatherMapper.mapirajResponse(lokacija.naziv, body)
                prognozaDao.spremi(PrognozaEntityMapper.prognozaUEntity(prognoza))
                prognoza
            } else {
                fallbackNaKes(lokacija.naziv)
            }
        } catch (e: IOException) {
            fallbackNaKes(lokacija.naziv)
        } catch (e: Exception) {
            fallbackNaKes(lokacija.naziv)
        }
    }

    private suspend fun fallbackNaKes(naziv: String): Prognoza? {
        val entity = prognozaDao.getByNaziv(naziv) ?: return null
        return PrognozaEntityMapper.entityUPrognoza(entity)
    }

    suspend fun osvjeziSveLokacije(lokacije: List<Lokacija>) {
        coroutineScope {
            lokacije.map { lokacija ->
                async { dohvatiPrognozu(lokacija) }
            }.map { it.await() }
        }
    }

    suspend fun salvaLokaciju(lokacija: Lokacija) {
        lokacijaDao.salva(
            LokacijaEntity(
                naziv          = lokacija.naziv,
                drzava         = lokacija.drzava,
                latitude       = lokacija.latitude,
                longitude      = lokacija.longitude,
                tipPrikaza     = lokacija.tipPrikaza,
                korisnikUpisan = true
            )
        )
    }

    suspend fun getSacuvaneLokacije(): List<Lokacija> {
        return lokacijaDao.getAll().map { entity ->
            Lokacija(
                naziv          = entity.naziv,
                drzava         = entity.drzava,
                latitude       = entity.latitude,
                longitude      = entity.longitude,
                tipPrikaza     = entity.tipPrikaza,
                korisnikUpisan = entity.korisnikUpisan
            )
        }
    }

    fun getKeširanePrognoze(): Flow<List<ba.etf.weatherwatch.data.local.PrognozaEntity>> =
        prognozaDao.getAll()

    suspend fun obrisiKes() = prognozaDao.obrisiSve()

    fun getBrojKesiranih(): Flow<Int> = prognozaDao.getBrojKesiranih()
}

package ba.etf.weatherwatch.network

import ba.etf.weatherwatch.model.DnevnaPrognoza
import ba.etf.weatherwatch.model.Prognoza
import ba.etf.weatherwatch.model.SatnaPrognoza
import ba.etf.weatherwatch.model.api.DailyData
import ba.etf.weatherwatch.model.api.HourlyData
import ba.etf.weatherwatch.model.api.OpenMeteoResponse
import java.util.Calendar

object WeatherMapper {

    fun mapirajResponse(nazivLokacije: String, response: OpenMeteoResponse): Prognoza {
        val current = response.current
        val padavine = if (current.padavine == 0f) null else current.padavine

        return Prognoza(
            nazivLokacije      = nazivLokacije,
            temperatura        = current.temperatura,
            osjecajTemperature = current.osjecajTemperature,
            opisVremena        = wmoUOpis(current.weatherCode),
            brzinaVjetra       = current.brzinaVjetra,
            smjerVjetra        = stupnjeviUSmjer(current.smjerVjetraStupnjevi),
            uvIndeks           = current.uvIndeks,
            padavine           = padavine,
            vlaznost           = current.vlaznost,
            pritisak           = current.pritisak.toInt(),
            vidljivost         = 10,
            oblacnost          = current.oblacnost,
            minTemp            = response.daily.minTemp.firstOrNull() ?: 0f,
            maxTemp            = response.daily.maxTemp.firstOrNull() ?: 0f,
            vrijemeTipa        = wmoUVrijemeTip(current.weatherCode),
            prognozaPoSatima   = mapirajSatnuPrognozu(response.hourly),
            prognozaDani       = mapirajDnevnuPrognozu(response.daily)
        )
    }

    fun wmoUVrijemeTip(kod: Int): String = when (kod) {
        0, 1           -> "sunny"
        2              -> "partly_cloudy"
        3              -> "cloudy"
        45, 48         -> "foggy"
        51, 53, 55,
        61, 63, 65,
        80, 81, 82     -> "rainy"
        71, 73, 75,
        77, 85, 86     -> "snowy"
        95, 96, 99     -> "stormy"
        else           -> "cloudy"
    }

    fun wmoUOpis(kod: Int): String = when (kod) {
        0              -> "Vedro nebo"
        1              -> "Pretežno vedro"
        2              -> "Djelimično oblačno"
        3              -> "Oblačno"
        45             -> "Magla"
        48             -> "Mraz magla"
        51             -> "Lagana rosulja"
        53             -> "Umjerena rosulja"
        55             -> "Gusta rosulja"
        61             -> "Lagana kiša"
        63             -> "Umjerena kiša"
        65             -> "Jaka kiša"
        71             -> "Lagani snijeg"
        73             -> "Umjeren snijeg"
        75             -> "Jaki snijeg"
        77             -> "Zrna snijega"
        80             -> "Lagani pljuskovi"
        81             -> "Umjereni pljuskovi"
        82             -> "Jaki pljuskovi"
        85             -> "Lagani snježni pljuskovi"
        86             -> "Jaki snježni pljuskovi"
        95             -> "Grmljavinska oluja"
        96             -> "Grmljavina s laganim gradom"
        99             -> "Grmljavina s jakim gradom"
        else           -> "Oblačno"
    }

    fun stupnjeviUSmjer(stepeni: Int): String {
        val s = ((stepeni % 360) + 360) % 360
        return when {
            s <= 22 || s >= 338 -> "S"
            s <= 67             -> "SI"
            s <= 112            -> "I"
            s <= 157            -> "JI"
            s <= 202            -> "J"
            s <= 247            -> "JZ"
            s <= 292            -> "Z"
            else                -> "SZ"
        }
    }

    fun mapirajSatnuPrognozu(hourly: HourlyData): List<SatnaPrognoza> {
        val count = minOf(24, hourly.time.size)
        return (0 until count).map { i ->
            val satString = hourly.time[i]
            val sat = if (satString.contains('T')) satString.substringAfter('T') else satString
            SatnaPrognoza(
                sat              = sat,
                temperatura      = hourly.temperatura[i],
                vrijemeTipa      = wmoUVrijemeTip(hourly.weatherCode[i]),
                padavinePostotak = hourly.padavinePosto[i]
            )
        }
    }

    fun mapirajDnevnuPrognozu(daily: DailyData): List<DnevnaPrognoza> {
        val count = minOf(7, daily.time.size)
        return (0 until count).map { i ->
            val dan = datumUDanSedmice(daily.time[i])
            DnevnaPrognoza(
                dan              = dan,
                minTemp          = daily.minTemp[i],
                maxTemp          = daily.maxTemp[i],
                vrijemeTipa      = wmoUVrijemeTip(daily.weatherCode[i]),
                padavinePostotak = daily.padavinePosto[i]
            )
        }
    }

    private fun datumUDanSedmice(datum: String): String {
        // datum format: "2026-06-13"
        val dijelovi = datum.split("-")
        val godina = dijelovi[0].toInt()
        val mjesec = dijelovi[1].toInt()
        val dan    = dijelovi[2].toInt()

        val cal = Calendar.getInstance()
        cal.set(godina, mjesec - 1, dan)

        return when (cal.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY    -> "Pon"
            Calendar.TUESDAY   -> "Uto"
            Calendar.WEDNESDAY -> "Sri"
            Calendar.THURSDAY  -> "Cet"
            Calendar.FRIDAY    -> "Pet"
            Calendar.SATURDAY  -> "Sub"
            Calendar.SUNDAY    -> "Ned"
            else               -> "Pon"
        }
    }
}
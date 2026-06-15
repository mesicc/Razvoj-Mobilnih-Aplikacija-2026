package ba.etf.weatherwatch.data

import ba.etf.weatherwatch.model.DnevnaPrognoza
import ba.etf.weatherwatch.model.Lokacija
import ba.etf.weatherwatch.model.Prognoza
import ba.etf.weatherwatch.model.SatnaPrognoza

object WeatherStaticData {

    private val sveLokacije: MutableList<Lokacija> = mutableListOf(
        Lokacija("Sarajevo", "Bosna i Hercegovina", 43.85, 18.39, "Po danu", true),
        Lokacija("Mostar", "Bosna i Hercegovina", 43.34, 17.81, "Po satu", true),
        Lokacija("Banja Luka", "Bosna i Hercegovina", 44.77, 17.19, "Sedmično", true),
        Lokacija("Tuzla", "Bosna i Hercegovina", 44.53, 18.67, "Po satu", false),
        Lokacija("Zenica", "Bosna i Hercegovina", 44.20, 17.91, "Po danu", false),
        Lokacija("Beograd", "Srbija", 44.82, 20.46, "Po danu", false),
        Lokacija("Zagreb", "Hrvatska", 45.81, 15.97, "Sedmično", false),
        Lokacija("Ljubljana", "Slovenija", 46.05, 14.51, "Po satu", false),
        Lokacija("Podgorica", "Crna Gora", 42.44, 19.26, "Po danu", false),
        Lokacija("Skoplje", "Sjeverna Makedonija", 41.99, 21.43, "Po satu", false)
    )

    private val prognoze: Map<String, Prognoza> = mapOf(
        "Sarajevo" to Prognoza(
            nazivLokacije = "Sarajevo",
            temperatura = 8f,
            osjecajTemperature = 5f,
            opisVremena = "Oblačno",
            brzinaVjetra = 15f,
            smjerVjetra = "SZ",
            uvIndeks = 2f,
            padavine = null,
            vlaznost = 70,
            pritisak = 1012,
            vidljivost = 10,
            oblacnost = 80,
            minTemp = 3f,
            maxTemp = 11f,
            vrijemeTipa = "cloudy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("07:00", 5f, "cloudy", 10),
                SatnaPrognoza("08:00", 6f, "cloudy", 10),
                SatnaPrognoza("09:00", 7f, "cloudy", 15),
                SatnaPrognoza("10:00", 8f, "cloudy", 20),
                SatnaPrognoza("11:00", 9f, "partly_cloudy", 15),
                SatnaPrognoza("12:00", 10f, "partly_cloudy", 10),
                SatnaPrognoza("13:00", 11f, "partly_cloudy", 10),
                SatnaPrognoza("14:00", 11f, "cloudy", 20),
                SatnaPrognoza("15:00", 10f, "cloudy", 25),
                SatnaPrognoza("16:00", 8f, "cloudy", 30)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 3f, 11f, "cloudy", 20),
                DnevnaPrognoza("Uto", 4f, 12f, "partly_cloudy", 15),
                DnevnaPrognoza("Sri", 2f, 10f, "rainy", 60),
                DnevnaPrognoza("Cet", 1f, 8f, "rainy", 70),
                DnevnaPrognoza("Pet", 3f, 9f, "cloudy", 30),
                DnevnaPrognoza("Sub", 5f, 13f, "partly_cloudy", 10),
                DnevnaPrognoza("Ned", 6f, 14f, "sunny", 5)
            )
        ),
        "Mostar" to Prognoza(
            nazivLokacije = "Mostar",
            temperatura = 32f,
            osjecajTemperature = 36f,
            opisVremena = "Sunčano i vruće",
            brzinaVjetra = 10f,
            smjerVjetra = "J",
            uvIndeks = 9f,
            padavine = null,
            vlaznost = 40,
            pritisak = 1018,
            vidljivost = 25,
            oblacnost = 5,
            minTemp = 24f,
            maxTemp = 35f,
            vrijemeTipa = "sunny",
            prognozaPoSatima = listOf(
                SatnaPrognoza("09:00", 26f, "sunny", 0),
                SatnaPrognoza("10:00", 28f, "sunny", 0),
                SatnaPrognoza("11:00", 30f, "sunny", 0),
                SatnaPrognoza("12:00", 33f, "sunny", 0),
                SatnaPrognoza("13:00", 35f, "sunny", 0),
                SatnaPrognoza("14:00", 35f, "sunny", 0),
                SatnaPrognoza("15:00", 34f, "sunny", 5),
                SatnaPrognoza("16:00", 32f, "partly_cloudy", 5),
                SatnaPrognoza("17:00", 30f, "partly_cloudy", 10),
                SatnaPrognoza("18:00", 28f, "partly_cloudy", 10)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Uto", 23f, 34f, "sunny", 0),
                DnevnaPrognoza("Sri", 24f, 36f, "sunny", 0),
                DnevnaPrognoza("Cet", 22f, 33f, "partly_cloudy", 10),
                DnevnaPrognoza("Pet", 20f, 28f, "cloudy", 25),
                DnevnaPrognoza("Sub", 18f, 26f, "rainy", 55),
                DnevnaPrognoza("Ned", 21f, 30f, "partly_cloudy", 15),
                DnevnaPrognoza("Pon", 23f, 33f, "sunny", 5)
            )
        ),
        "Banja Luka" to Prognoza(
            nazivLokacije = "Banja Luka",
            temperatura = 18f,
            osjecajTemperature = 16f,
            opisVremena = "Djelimično oblačno",
            brzinaVjetra = 20f,
            smjerVjetra = "S",
            uvIndeks = 4f,
            padavine = null,
            vlaznost = 55,
            pritisak = 1015,
            vidljivost = 20,
            oblacnost = 40,
            minTemp = 12f,
            maxTemp = 20f,
            vrijemeTipa = "partly_cloudy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("07:00", 12f, "partly_cloudy", 5),
                SatnaPrognoza("08:00", 14f, "partly_cloudy", 5),
                SatnaPrognoza("09:00", 15f, "sunny", 0),
                SatnaPrognoza("10:00", 17f, "sunny", 0),
                SatnaPrognoza("11:00", 18f, "partly_cloudy", 5),
                SatnaPrognoza("12:00", 20f, "partly_cloudy", 10),
                SatnaPrognoza("13:00", 20f, "cloudy", 20),
                SatnaPrognoza("14:00", 19f, "cloudy", 25),
                SatnaPrognoza("15:00", 18f, "partly_cloudy", 15),
                SatnaPrognoza("16:00", 16f, "partly_cloudy", 10)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 10f, 20f, "partly_cloudy", 10),
                DnevnaPrognoza("Uto", 11f, 21f, "sunny", 5),
                DnevnaPrognoza("Sri", 9f, 18f, "cloudy", 30),
                DnevnaPrognoza("Cet", 8f, 16f, "rainy", 60),
                DnevnaPrognoza("Pet", 10f, 19f, "partly_cloudy", 20),
                DnevnaPrognoza("Sub", 12f, 22f, "sunny", 5),
                DnevnaPrognoza("Ned", 13f, 23f, "sunny", 0)
            )
        ),
        "Tuzla" to Prognoza(
            nazivLokacije = "Tuzla",
            temperatura = -3f,
            osjecajTemperature = -7f,
            opisVremena = "Snijeg",
            brzinaVjetra = 25f,
            smjerVjetra = "SZ",
            uvIndeks = 1f,
            padavine = 5f,
            vlaznost = 90,
            pritisak = 1008,
            vidljivost = 3,
            oblacnost = 95,
            minTemp = -5f,
            maxTemp = 0f,
            vrijemeTipa = "snowy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("07:00", -5f, "snowy", 80),
                SatnaPrognoza("08:00", -4f, "snowy", 75),
                SatnaPrognoza("09:00", -3f, "snowy", 70),
                SatnaPrognoza("10:00", -2f, "cloudy", 40),
                SatnaPrognoza("11:00", -1f, "cloudy", 30),
                SatnaPrognoza("12:00", 0f, "cloudy", 25),
                SatnaPrognoza("13:00", 0f, "cloudy", 20),
                SatnaPrognoza("14:00", -1f, "snowy", 60),
                SatnaPrognoza("15:00", -2f, "snowy", 70),
                SatnaPrognoza("16:00", -3f, "snowy", 80)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", -5f, 0f, "snowy", 70),
                DnevnaPrognoza("Uto", -3f, 2f, "cloudy", 30),
                DnevnaPrognoza("Sri", -1f, 4f, "partly_cloudy", 15),
                DnevnaPrognoza("Cet", 0f, 5f, "partly_cloudy", 10),
                DnevnaPrognoza("Pet", 2f, 7f, "sunny", 5),
                DnevnaPrognoza("Sub", 3f, 8f, "sunny", 0),
                DnevnaPrognoza("Ned", 1f, 6f, "cloudy", 20)
            )
        ),
        "Zenica" to Prognoza(
            nazivLokacije = "Zenica",
            temperatura = 10f,
            osjecajTemperature = 8f,
            opisVremena = "Magla",
            brzinaVjetra = 5f,
            smjerVjetra = "J",
            uvIndeks = 1f,
            padavine = null,
            vlaznost = 85,
            pritisak = 1010,
            vidljivost = 2,
            oblacnost = 90,
            minTemp = 6f,
            maxTemp = 12f,
            vrijemeTipa = "foggy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("07:00", 6f, "foggy", 5),
                SatnaPrognoza("08:00", 7f, "foggy", 5),
                SatnaPrognoza("09:00", 8f, "foggy", 5),
                SatnaPrognoza("10:00", 9f, "cloudy", 10),
                SatnaPrognoza("11:00", 10f, "cloudy", 10),
                SatnaPrognoza("12:00", 12f, "partly_cloudy", 5),
                SatnaPrognoza("13:00", 12f, "partly_cloudy", 5),
                SatnaPrognoza("14:00", 11f, "cloudy", 10),
                SatnaPrognoza("15:00", 10f, "foggy", 5),
                SatnaPrognoza("16:00", 8f, "foggy", 5)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 6f, 12f, "foggy", 5),
                DnevnaPrognoza("Uto", 7f, 14f, "partly_cloudy", 10),
                DnevnaPrognoza("Sri", 8f, 15f, "sunny", 5),
                DnevnaPrognoza("Cet", 6f, 13f, "cloudy", 20),
                DnevnaPrognoza("Pet", 5f, 11f, "rainy", 50),
                DnevnaPrognoza("Sub", 7f, 14f, "partly_cloudy", 15),
                DnevnaPrognoza("Ned", 9f, 16f, "sunny", 5)
            )
        ),
        "Beograd" to Prognoza(
            nazivLokacije = "Beograd",
            temperatura = 24f,
            osjecajTemperature = 23f,
            opisVremena = "Sunčano",
            brzinaVjetra = 12f,
            smjerVjetra = "I",
            uvIndeks = 6f,
            padavine = null,
            vlaznost = 45,
            pritisak = 1016,
            vidljivost = 20,
            oblacnost = 10,
            minTemp = 16f,
            maxTemp = 26f,
            vrijemeTipa = "sunny",
            prognozaPoSatima = listOf(
                SatnaPrognoza("08:00", 17f, "sunny", 0),
                SatnaPrognoza("09:00", 19f, "sunny", 0),
                SatnaPrognoza("10:00", 21f, "sunny", 0),
                SatnaPrognoza("11:00", 23f, "sunny", 0),
                SatnaPrognoza("12:00", 25f, "sunny", 0),
                SatnaPrognoza("13:00", 26f, "sunny", 0),
                SatnaPrognoza("14:00", 26f, "partly_cloudy", 5),
                SatnaPrognoza("15:00", 25f, "partly_cloudy", 5),
                SatnaPrognoza("16:00", 23f, "partly_cloudy", 10),
                SatnaPrognoza("17:00", 21f, "sunny", 0)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 16f, 26f, "sunny", 0),
                DnevnaPrognoza("Uto", 17f, 27f, "sunny", 0),
                DnevnaPrognoza("Sri", 15f, 24f, "partly_cloudy", 10),
                DnevnaPrognoza("Cet", 14f, 22f, "cloudy", 25),
                DnevnaPrognoza("Pet", 13f, 20f, "rainy", 55),
                DnevnaPrognoza("Sub", 15f, 23f, "partly_cloudy", 15),
                DnevnaPrognoza("Ned", 17f, 25f, "sunny", 5)
            )
        ),
        "Zagreb" to Prognoza(
            nazivLokacije = "Zagreb",
            temperatura = 15f,
            osjecajTemperature = 13f,
            opisVremena = "Oblačno",
            brzinaVjetra = 18f,
            smjerVjetra = "Z",
            uvIndeks = 3f,
            padavine = 2f,
            vlaznost = 75,
            pritisak = 1011,
            vidljivost = 8,
            oblacnost = 70,
            minTemp = 10f,
            maxTemp = 17f,
            vrijemeTipa = "rainy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("07:00", 10f, "rainy", 60),
                SatnaPrognoza("08:00", 11f, "rainy", 55),
                SatnaPrognoza("09:00", 12f, "cloudy", 40),
                SatnaPrognoza("10:00", 13f, "cloudy", 35),
                SatnaPrognoza("11:00", 14f, "cloudy", 30),
                SatnaPrognoza("12:00", 15f, "partly_cloudy", 20),
                SatnaPrognoza("13:00", 17f, "partly_cloudy", 15),
                SatnaPrognoza("14:00", 16f, "cloudy", 25),
                SatnaPrognoza("15:00", 15f, "rainy", 50),
                SatnaPrognoza("16:00", 13f, "rainy", 60)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 10f, 17f, "rainy", 55),
                DnevnaPrognoza("Uto", 11f, 18f, "cloudy", 30),
                DnevnaPrognoza("Sri", 12f, 20f, "partly_cloudy", 15),
                DnevnaPrognoza("Cet", 13f, 21f, "sunny", 5),
                DnevnaPrognoza("Pet", 12f, 19f, "partly_cloudy", 20),
                DnevnaPrognoza("Sub", 10f, 16f, "rainy", 60),
                DnevnaPrognoza("Ned", 9f, 15f, "rainy", 65)
            )
        ),
        "Ljubljana" to Prognoza(
            nazivLokacije = "Ljubljana",
            temperatura = 10f,
            osjecajTemperature = 8f,
            opisVremena = "Magla",
            brzinaVjetra = 8f,
            smjerVjetra = "S",
            uvIndeks = 2f,
            padavine = null,
            vlaznost = 88,
            pritisak = 1013,
            vidljivost = 2,
            oblacnost = 85,
            minTemp = 6f,
            maxTemp = 12f,
            vrijemeTipa = "foggy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("07:00", 6f, "foggy", 5),
                SatnaPrognoza("08:00", 7f, "foggy", 5),
                SatnaPrognoza("09:00", 8f, "foggy", 5),
                SatnaPrognoza("10:00", 9f, "cloudy", 10),
                SatnaPrognoza("11:00", 10f, "partly_cloudy", 5),
                SatnaPrognoza("12:00", 12f, "partly_cloudy", 5),
                SatnaPrognoza("13:00", 12f, "sunny", 0),
                SatnaPrognoza("14:00", 11f, "partly_cloudy", 5),
                SatnaPrognoza("15:00", 10f, "cloudy", 15),
                SatnaPrognoza("16:00", 8f, "foggy", 10)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 6f, 12f, "foggy", 5),
                DnevnaPrognoza("Uto", 7f, 14f, "partly_cloudy", 10),
                DnevnaPrognoza("Sri", 9f, 16f, "sunny", 0),
                DnevnaPrognoza("Cet", 8f, 15f, "partly_cloudy", 15),
                DnevnaPrognoza("Pet", 6f, 13f, "rainy", 45),
                DnevnaPrognoza("Sub", 5f, 11f, "cloudy", 30),
                DnevnaPrognoza("Ned", 7f, 14f, "partly_cloudy", 10)
            )
        ),
        "Podgorica" to Prognoza(
            nazivLokacije = "Podgorica",
            temperatura = 28f,
            osjecajTemperature = 30f,
            opisVremena = "Sunčano",
            brzinaVjetra = 14f,
            smjerVjetra = "JI",
            uvIndeks = 8f,
            padavine = null,
            vlaznost = 38,
            pritisak = 1017,
            vidljivost = 25,
            oblacnost = 5,
            minTemp = 20f,
            maxTemp = 31f,
            vrijemeTipa = "sunny",
            prognozaPoSatima = listOf(
                SatnaPrognoza("08:00", 21f, "sunny", 0),
                SatnaPrognoza("09:00", 23f, "sunny", 0),
                SatnaPrognoza("10:00", 25f, "sunny", 0),
                SatnaPrognoza("11:00", 27f, "sunny", 0),
                SatnaPrognoza("12:00", 29f, "sunny", 0),
                SatnaPrognoza("13:00", 31f, "sunny", 0),
                SatnaPrognoza("14:00", 31f, "sunny", 5),
                SatnaPrognoza("15:00", 30f, "partly_cloudy", 5),
                SatnaPrognoza("16:00", 28f, "partly_cloudy", 10),
                SatnaPrognoza("17:00", 26f, "sunny", 0)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 20f, 31f, "sunny", 0),
                DnevnaPrognoza("Uto", 21f, 32f, "sunny", 0),
                DnevnaPrognoza("Sri", 19f, 29f, "partly_cloudy", 10),
                DnevnaPrognoza("Cet", 18f, 27f, "cloudy", 20),
                DnevnaPrognoza("Pet", 17f, 25f, "rainy", 45),
                DnevnaPrognoza("Sub", 19f, 28f, "partly_cloudy", 15),
                DnevnaPrognoza("Ned", 21f, 30f, "sunny", 5)
            )
        ),
        "Skoplje" to Prognoza(
            nazivLokacije = "Skoplje",
            temperatura = 22f,
            osjecajTemperature = 21f,
            opisVremena = "Djelimično oblačno",
            brzinaVjetra = 16f,
            smjerVjetra = "SZ",
            uvIndeks = 5f,
            padavine = null,
            vlaznost = 50,
            pritisak = 1014,
            vidljivost = 15,
            oblacnost = 35,
            minTemp = 14f,
            maxTemp = 24f,
            vrijemeTipa = "partly_cloudy",
            prognozaPoSatima = listOf(
                SatnaPrognoza("08:00", 15f, "partly_cloudy", 5),
                SatnaPrognoza("09:00", 17f, "sunny", 0),
                SatnaPrognoza("10:00", 19f, "sunny", 0),
                SatnaPrognoza("11:00", 21f, "partly_cloudy", 5),
                SatnaPrognoza("12:00", 23f, "partly_cloudy", 10),
                SatnaPrognoza("13:00", 24f, "partly_cloudy", 10),
                SatnaPrognoza("14:00", 24f, "cloudy", 20),
                SatnaPrognoza("15:00", 22f, "cloudy", 25),
                SatnaPrognoza("16:00", 20f, "partly_cloudy", 15),
                SatnaPrognoza("17:00", 18f, "sunny", 5)
            ),
            prognozaDani = listOf(
                DnevnaPrognoza("Pon", 14f, 24f, "partly_cloudy", 10),
                DnevnaPrognoza("Uto", 15f, 25f, "sunny", 0),
                DnevnaPrognoza("Sri", 13f, 23f, "partly_cloudy", 15),
                DnevnaPrognoza("Cet", 12f, 21f, "cloudy", 30),
                DnevnaPrognoza("Pet", 11f, 19f, "rainy", 50),
                DnevnaPrognoza("Sub", 13f, 22f, "partly_cloudy", 20),
                DnevnaPrognoza("Ned", 15f, 24f, "sunny", 5)
            )
        )
    )

    fun getLokacijeKorisnika(): List<Lokacija> =
        sveLokacije.filter { it.korisnikUpisan }.sortedBy { it.naziv }

    fun getSveLokacije(): List<Lokacija> =
        sveLokacije.sortedBy { it.naziv }

    fun getLokacijePoStatusu(status: String): List<Lokacija> =
        getLokacijeKorisnika().filter { getStatus(it.naziv) == status }

    fun getPrognozu(nazivLokacije: String): Prognoza? =
        prognoze[nazivLokacije]

    fun getStatus(nazivLokacije: String): String {
        val p = prognoze[nazivLokacije] ?: return "Vedro"
        return when {
            p.padavine != null && p.brzinaVjetra > 50 -> "Oluja"
            p.padavine != null -> "Padavine"
            p.temperatura > 30 || p.uvIndeks > 7 -> "Vruce"
            p.temperatura in 20f..30f -> "Toplo"
            p.temperatura < 0 -> "Mraz"
            else -> "Vedro"
        }
    }

    fun dodajLokaciju(lokacija: Lokacija) {
        val index = sveLokacije.indexOfFirst {
            it.naziv == lokacija.naziv && it.drzava == lokacija.drzava
        }
        if (index >= 0) {
            sveLokacije[index] = sveLokacije[index].copy(korisnikUpisan = true)
        } else {
            sveLokacije.add(lokacija.copy(korisnikUpisan = true))
        }
    }
}

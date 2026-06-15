package ba.etf.weatherwatch.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.weatherwatch.R
import ba.etf.weatherwatch.data.WeatherStaticData

class DetaljiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.apply(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalji)

        val toolbar = findViewById<Toolbar>(R.id.toolbarDetalji)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val naziv = intent.getStringExtra("LOKACIJA") ?: return
        val prognoza = WeatherStaticData.getPrognozu(naziv) ?: return

        supportActionBar?.title = naziv

        val prefs = getSharedPreferences("ww_prefs", MODE_PRIVATE)
        val fahrenheit = prefs.getString("jedinice", "celsius") == "fahrenheit"

        fun formatTemp(c: Float): String = if (fahrenheit)
            "${(c * 9f / 5f + 32f).toInt()}F"
        else
            "${c.toInt()}C"

        // Pozadinska slika
        val ivBg = findViewById<ImageView>(R.id.ivBgGradient)
        val bgRes = when (prognoza.vrijemeTipa) {
            "sunny" -> R.drawable.bg_sunny
            "partly_cloudy" -> R.drawable.bg_partly_cloudy
            "cloudy" -> R.drawable.bg_cloudy
            "rainy" -> R.drawable.bg_rainy
            "snowy" -> R.drawable.bg_snowy
            "stormy" -> R.drawable.bg_stormy
            "foggy" -> R.drawable.bg_foggy
            else -> R.drawable.bg_cloudy
        }
        ivBg.setImageResource(bgRes)

        // Glavna ikona
        val ivIcon = findViewById<ImageView>(R.id.ivMainWeatherIcon)
        val iconRes = when (prognoza.vrijemeTipa) {
            "sunny" -> R.drawable.ic_weather_sunny
            "partly_cloudy" -> R.drawable.ic_weather_partly_cloudy
            "cloudy" -> R.drawable.ic_weather_cloudy
            "rainy" -> R.drawable.ic_weather_rainy
            "snowy" -> R.drawable.ic_weather_snowy
            "stormy" -> R.drawable.ic_weather_stormy
            "foggy" -> R.drawable.ic_weather_foggy
            else -> R.drawable.ic_weather_cloudy
        }
        ivIcon.setImageResource(iconRes)

        // Temperatura i opis
        findViewById<TextView>(R.id.tvGlavnaTemp).text = formatTemp(prognoza.temperatura)
        findViewById<TextView>(R.id.tvGlavniOpis).text = prognoza.opisVremena
        findViewById<TextView>(R.id.tvOsjecaj).text = "Osjeća se kao ${formatTemp(prognoza.osjecajTemperature)}"
        findViewById<TextView>(R.id.tvMinMaxGlavni).text = "${formatTemp(prognoza.minTemp)} / ${formatTemp(prognoza.maxTemp)}"

        // Detalji
        findViewById<TextView>(R.id.tvVjetar).text = "${prognoza.brzinaVjetra.toInt()} km/h ${prognoza.smjerVjetra}"
        findViewById<TextView>(R.id.tvVlaznost).text = "${prognoza.vlaznost}%"
        findViewById<TextView>(R.id.tvUv).text = "${prognoza.uvIndeks.toInt()} — ${
            when {
                prognoza.uvIndeks <= 2 -> "Nizak"
                prognoza.uvIndeks <= 5 -> "Umjeren"
                prognoza.uvIndeks <= 7 -> "Visok"
                prognoza.uvIndeks <= 10 -> "Veoma visok"
                else -> "Ekstremno visok"
            }
        }"
        findViewById<TextView>(R.id.tvPritisak).text = "${prognoza.pritisak} hPa"

        // Satna prognoza
        val rvSatna = findViewById<RecyclerView>(R.id.rvSatnaPrognoza)
        rvSatna.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvSatna.adapter = SatnaPrognozeAdapter(prognoza.prognozaPoSatima, fahrenheit)

        // Dnevna prognoza
        val rvDnevna = findViewById<RecyclerView>(R.id.rvDnevnaPrognoza)
        rvDnevna.layoutManager = LinearLayoutManager(this)
        rvDnevna.adapter = DnevnaPrognozeAdapter(prognoza.prognozaDani, fahrenheit)
    }
}
package ba.etf.weatherwatch.ui

import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.switchmaterial.SwitchMaterial
import ba.etf.weatherwatch.R
import ba.etf.weatherwatch.viewmodel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {

    private val vm: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.apply(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<Toolbar>(R.id.toolbarSettings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val prefs = getSharedPreferences("ww_prefs", MODE_PRIVATE)

        val rgTema = findViewById<RadioGroup>(R.id.rgTema)
        val rgJezik = findViewById<RadioGroup>(R.id.rgJezik)
        val rgJedinice = findViewById<RadioGroup>(R.id.rgJedinice)
        val swNotifikacije = findViewById<SwitchMaterial>(R.id.swNotifikacije)
        val swOluja = findViewById<SwitchMaterial>(R.id.swOluja)

        // Učitaj trenutne postavke
        when (prefs.getString("tema", "auto")) {
            "light" -> rgTema.check(R.id.rbTemaLight)
            "dark" -> rgTema.check(R.id.rbTemaDark)
            else -> rgTema.check(R.id.rbTemaAuto)
        }

        when (prefs.getString("jezik", "bs")) {
            "en" -> rgJezik.check(R.id.rbJezikEn)
            else -> rgJezik.check(R.id.rbJezikBs)
        }

        when (prefs.getString("jedinice", "celsius")) {
            "fahrenheit" -> rgJedinice.check(R.id.rbFahrenheit)
            else -> rgJedinice.check(R.id.rbCelsius)
        }

        swNotifikacije.isChecked = prefs.getBoolean("notifikacije", true)
        swOluja.isChecked = prefs.getBoolean("notifikacije_oluja", true)

        // Slušaj promjene
        rgTema.setOnCheckedChangeListener { _, checkedId ->
            val tema = when (checkedId) {
                R.id.rbTemaLight -> "light"
                R.id.rbTemaDark -> "dark"
                else -> "auto"
            }
            prefs.edit().putString("tema", tema).apply()
            vm.postaviTemu(tema)
            ThemeHelper.apply(this)
        }

        rgJezik.setOnCheckedChangeListener { _, checkedId ->
            val jezik = when (checkedId) {
                R.id.rbJezikEn -> "en"
                else -> "bs"
            }
            prefs.edit().putString("jezik", jezik).apply()
            vm.postaviJezik(jezik)
        }

        rgJedinice.setOnCheckedChangeListener { _, checkedId ->
            val jedinice = when (checkedId) {
                R.id.rbFahrenheit -> "fahrenheit"
                else -> "celsius"
            }
            prefs.edit().putString("jedinice", jedinice).apply()
            vm.postaviJedinice(jedinice)
        }

        swNotifikacije.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notifikacije", isChecked).apply()
            vm.postaviNotifikacije(isChecked)
        }

        swOluja.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notifikacije_oluja", isChecked).apply()
            vm.postaviNotifikacijeOluja(isChecked)
        }
    }
}
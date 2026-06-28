package ba.etf.weatherwatch

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import ba.etf.weatherwatch.viewmodel.MainViewModel
import ba.etf.weatherwatch.ui.LokacijaAdapter
import ba.etf.weatherwatch.ui.ThemeHelper
import ba.etf.weatherwatch.ui.DetaljiActivity
import ba.etf.weatherwatch.ui.PrognozaActivity
import ba.etf.weatherwatch.ui.SettingsActivity

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels()
    private lateinit var adapter: LokacijaAdapter
    private lateinit var progressBarMain: ProgressBar
    private lateinit var scrollViewMain: View
    private lateinit var formContainer: View
    private lateinit var tvBrojKesiranih: TextView
    private lateinit var recyclerLokacije: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.apply(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        progressBarMain   = findViewById(R.id.progressBarMain)
        scrollViewMain    = findViewById(R.id.scrollViewMain)
        formContainer     = findViewById(R.id.formContainer)
        tvBrojKesiranih   = findViewById(R.id.tvBrojKesiranih)
        recyclerLokacije  = findViewById(R.id.recyclerLokacije)

        adapter = LokacijaAdapter { lok ->
            val intent = Intent(this, DetaljiActivity::class.java)
            intent.putExtra("LOKACIJA", lok.naziv)
            startActivity(intent)
        }
        recyclerLokacije.layoutManager = LinearLayoutManager(this)
        recyclerLokacije.adapter = adapter

        val brojLokacija = findViewById<TextView>(R.id.brojLokacija)
        val dodajDugme   = findViewById<MaterialButton>(R.id.dodajLokacijuDugme)
        val prikaziDugme = findViewById<MaterialButton>(R.id.prikaziPrognozuDugme)

        // Filter spinner
        val filterSpinner = findViewById<Spinner>(R.id.filterLokacija)
        val filterAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, vm.filterOpcije)
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = filterAdapter
        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>, v: View?, pos: Int, id: Long) {
                vm.postaviFilter(vm.filterOpcije[pos])
            }
            override fun onNothingSelected(p: AdapterView<*>) {}
        }

        // Drzava spinner
        val drzavaSpinner = findViewById<Spinner>(R.id.odabirDrzave)
        val drzaveItems   = listOf("Odaberi državu") + vm.sveDrzave.map { it.naziv }
        val drzavaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, drzaveItems)
        drzavaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        drzavaSpinner.adapter = drzavaAdapter
        drzavaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>, v: View?, pos: Int, id: Long) {
                if (pos == 0) vm.odaberiDrzavu(null)
                else vm.odaberiDrzavu(vm.sveDrzave[pos - 1])
            }
            override fun onNothingSelected(p: AdapterView<*>) {}
        }

        // Grad spinner
        val gradSpinner = findViewById<Spinner>(R.id.odabirGrada)
        vm.gradoviZaDrzavu.observe(this) { gradovi ->
            val gradItems   = listOf("Odaberi grad") + gradovi.map { it.naziv }
            val gradAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gradItems)
            gradAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gradSpinner.adapter = gradAdapter
        }
        gradSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>, v: View?, pos: Int, id: Long) {
                val gradovi = vm.gradoviZaDrzavu.value ?: emptyList()
                if (pos == 0) vm.odaberiGrad(null)
                else if (pos - 1 < gradovi.size) vm.odaberiGrad(gradovi[pos - 1])
            }
            override fun onNothingSelected(p: AdapterView<*>) {}
        }

        // Tip spinner
        val tipSpinner = findViewById<Spinner>(R.id.odabirTipaPrikaza)
        val tipItems   = listOf("Odaberi tip") + vm.tipOpcije
        val tipAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipItems)
        tipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tipSpinner.adapter = tipAdapter
        tipSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>, v: View?, pos: Int, id: Long) {
                if (pos == 0) vm.odaberiTip(null)
                else vm.odaberiTip(vm.tipOpcije[pos - 1])
            }
            override fun onNothingSelected(p: AdapterView<*>) {}
        }

        vm.dugmeEnabled.observe(this) { enabled -> dodajDugme.isEnabled = enabled }

        dodajDugme.setOnClickListener {
            vm.dodajLokaciju()
            drzavaSpinner.setSelection(0)
            gradSpinner.setSelection(0)
            tipSpinner.setSelection(0)
            Toast.makeText(this, "Lokacija dodana!", Toast.LENGTH_SHORT).show()
        }

        prikaziDugme.setOnClickListener {
            val intent = Intent(this, PrognozaActivity::class.java)
            intent.putExtra("FILTER", vm.odabraniFilter.value)
            startActivity(intent)
        }

        vm.filterovaneLokacije.observe(this) { lokacije ->
            adapter.submitList(lokacije)
            brojLokacija.text = "Pronađeno je ${lokacije.size} lokacija"
        }

        // Spirala 2: Loading
        vm.isLoading.observe(this) { loading ->
            progressBarMain.visibility = if (loading) View.VISIBLE else View.GONE
            scrollViewMain.visibility  = if (loading) View.GONE   else View.VISIBLE
        }

        // Spirala 2: Greška Snackbar
        vm.greska.observe(this) { poruka ->
            if (poruka != null) {
                Snackbar.make(recyclerLokacije, poruka, Snackbar.LENGTH_LONG)
                    .setAction("Pokušaj ponovo") { vm.osvjeziSveLokacije() }
                    .show()
                vm.ocistiGresku()
            }
        }

        // Spirala 2: Broj keširanih
        WeatherWatchApplication.instance.repository.getBrojKesiranih()
            .asLiveData().observe(this) { broj ->
                tvBrojKesiranih.text = "Keširano: $broj"
            }

        // Spirala 2: Učitaj sačuvane lokacije
        vm.ucitajSacuvaneLokacije()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_refresh -> {
                vm.osvjeziSveLokacije()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs        = getSharedPreferences("ww_prefs", MODE_PRIVATE)
        val isFahrenheit = prefs.getString("jedinice", "celsius") == "fahrenheit"
        if (isFahrenheit != adapter.fahrenheit) {
            adapter.fahrenheit = isFahrenheit
            adapter.notifyDataSetChanged()
        }
        vm.osvjeziSveLokacije()
    }
}
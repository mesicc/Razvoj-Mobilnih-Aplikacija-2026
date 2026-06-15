package ba.etf.weatherwatch.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.weatherwatch.R
import ba.etf.weatherwatch.viewmodel.PrognozaViewModel

class PrognozaActivity : AppCompatActivity() {

    private val vm: PrognozaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.apply(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prognoza)

        val toolbar = findViewById<Toolbar>(R.id.toolbarPrognoza)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val filter = intent.getStringExtra("FILTER") ?: "Sve moje lokacije"

        val adapter = LokacijaAdapter { lok ->
            val intent = Intent(this, DetaljiActivity::class.java)
            intent.putExtra("LOKACIJA", lok.naziv)
            startActivity(intent)
        }

        val recycler = findViewById<RecyclerView>(R.id.recyclerLokacije)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        vm.lokacije.observe(this) { lokacije ->
            adapter.submitList(lokacije)
        }

        vm.ucitajLokacije(filter)
    }
}
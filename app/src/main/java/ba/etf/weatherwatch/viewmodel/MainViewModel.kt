package ba.etf.weatherwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ba.etf.weatherwatch.data.DrzavaStaticData
import ba.etf.weatherwatch.data.GradStaticData
import ba.etf.weatherwatch.data.WeatherStaticData
import ba.etf.weatherwatch.model.Drzava
import ba.etf.weatherwatch.model.Grad
import ba.etf.weatherwatch.model.Lokacija

class MainViewModel : ViewModel() {

    val filterOpcije = listOf("Sve moje lokacije", "Sve lokacije", "Vedro", "Padavine", "Ekstremne temperature")
    val tipOpcije = listOf("Po satu", "Po danu", "Sedmicno")
    val sveDrzave: List<Drzava> = DrzavaStaticData.getAll()

    private val _filterovaneLokacije = MutableLiveData<List<Lokacija>>()
    val filterovaneLokacije: LiveData<List<Lokacija>> = _filterovaneLokacije

    private val _gradoviZaDrzavu = MutableLiveData<List<Grad>>()
    val gradoviZaDrzavu: LiveData<List<Grad>> = _gradoviZaDrzavu

    private val _dugmeEnabled = MutableLiveData<Boolean>(false)
    val dugmeEnabled: LiveData<Boolean> = _dugmeEnabled

    private val _odabranaDrzava = MutableLiveData<Drzava?>(null)
    val odabranaDrzava: LiveData<Drzava?> = _odabranaDrzava

    private val _odabraniGrad = MutableLiveData<Grad?>(null)
    val odabraniGrad: LiveData<Grad?> = _odabraniGrad

    private val _odabraniTip = MutableLiveData<String?>(null)
    val odabraniTip: LiveData<String?> = _odabraniTip

    val odabraniFilter = MutableLiveData<String>("Sve moje lokacije")

    init {
        _filterovaneLokacije.value = WeatherStaticData.getLokacijeKorisnika()
    }

    fun postaviFilter(filter: String) {
        odabraniFilter.value = filter
        _filterovaneLokacije.value = when (filter) {
            "Sve moje lokacije" -> WeatherStaticData.getLokacijeKorisnika()
            "Sve lokacije" -> WeatherStaticData.getSveLokacije()
            "Vedro" -> WeatherStaticData.getLokacijeKorisnika().filter {
                WeatherStaticData.getStatus(it.naziv) in listOf("Vedro", "Toplo")
            }
            "Padavine" -> WeatherStaticData.getLokacijeKorisnika().filter {
                WeatherStaticData.getStatus(it.naziv) in listOf("Padavine", "Oluja")
            }
            "Ekstremne temperature" -> WeatherStaticData.getLokacijeKorisnika().filter {
                val p = WeatherStaticData.getPrognozu(it.naziv)
                p != null && (p.temperatura < 0 || p.temperatura > 35)
            }
            else -> WeatherStaticData.getLokacijeKorisnika()
        }
    }

    fun odaberiDrzavu(drzava: Drzava?) {
        _odabranaDrzava.value = drzava
        _odabraniGrad.value = null
        _gradoviZaDrzavu.value = if (drzava != null)
            GradStaticData.getGradoviZaDodavanje(drzava.naziv)
        else
            emptyList()
        provjeriDugme()
    }

    fun odaberiGrad(grad: Grad?) {
        _odabraniGrad.value = grad
        provjeriDugme()
    }

    fun odaberiTip(tip: String?) {
        _odabraniTip.value = tip
        provjeriDugme()
    }

    fun dodajLokaciju() {
        val grad = _odabraniGrad.value ?: return
        val tip = _odabraniTip.value ?: return

        val lokacija = Lokacija(
            naziv = grad.naziv,
            drzava = grad.nazivDrzave,
            latitude = grad.lat,
            longitude = grad.lon,
            tipPrikaza = tip,
            korisnikUpisan = true
        )
        WeatherStaticData.dodajLokaciju(lokacija)

        _odabranaDrzava.value = null
        _odabraniGrad.value = null
        _odabraniTip.value = null
        _gradoviZaDrzavu.value = emptyList()
        _dugmeEnabled.value = false

        postaviFilter(odabraniFilter.value ?: "Sve moje lokacije")
    }

    private fun provjeriDugme() {
        _dugmeEnabled.value = _odabranaDrzava.value != null &&
                _odabraniGrad.value != null &&
                _odabraniTip.value != null
    }
}
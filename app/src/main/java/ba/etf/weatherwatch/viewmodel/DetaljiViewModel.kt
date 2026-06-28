package ba.etf.weatherwatch.viewmodel

import androidx.lifecycle.*
import ba.etf.weatherwatch.WeatherWatchApplication
import ba.etf.weatherwatch.model.Lokacija
import ba.etf.weatherwatch.model.Prognoza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetaljiViewModel : ViewModel() {

    private val repository = WeatherWatchApplication.instance.repository

    private val _prognoza = MutableLiveData<Prognoza?>()
    val prognoza: LiveData<Prognoza?> = _prognoza

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _greska = MutableLiveData<String?>(null)
    val greska: LiveData<String?> = _greska

    fun ucitajPrognozu(lokacija: Lokacija) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val rezultat = repository.dohvatiPrognozu(lokacija)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (rezultat != null) {
                        _prognoza.value = rezultat
                    } else {
                        _greska.value = "Nema dostupnih podataka"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    _greska.value = "Prikazuju se keširani podaci"
                }
            }
        }
    }
}
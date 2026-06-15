package ba.etf.weatherwatch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ba.etf.weatherwatch.R
import ba.etf.weatherwatch.data.WeatherStaticData
import ba.etf.weatherwatch.model.Lokacija

class LokacijaAdapter(
    private val onClick: (Lokacija) -> Unit
) : ListAdapter<Lokacija, LokacijaAdapter.ViewHolder>(DiffCallback()) {

    var fahrenheit: Boolean = false

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val statusIndikator: ImageView = view.findViewById(R.id.statusIndikator)
        val ivWeatherIcon: ImageView = view.findViewById(R.id.ivWeatherIcon)
        val tvNaziv: TextView = view.findViewById(R.id.tvNaziv)
        val tvDrzava: TextView = view.findViewById(R.id.tvDrzava)
        val tvOpis: TextView = view.findViewById(R.id.tvOpis)
        val tvTip: TextView = view.findViewById(R.id.tvTip)
        val tvTemperatura: TextView = view.findViewById(R.id.tvTemperatura)
        val tvMinMax: TextView = view.findViewById(R.id.tvMinMax)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lokacija, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lok = getItem(position)
        val prognoza = WeatherStaticData.getPrognozu(lok.naziv)
        val status = WeatherStaticData.getStatus(lok.naziv)

        holder.tvNaziv.text = lok.naziv
        holder.tvDrzava.text = lok.drzava
        holder.tvTip.text = lok.tipPrikaza

        // Status indikator
        holder.statusIndikator.contentDescription = status
        val dotRes = when (status) {
            "Vedro" -> R.drawable.ic_dot_green
            "Toplo" -> R.drawable.ic_dot_yellow
            "Vruce" -> R.drawable.ic_dot_orange
            "Padavine" -> R.drawable.ic_dot_blue
            "Mraz" -> R.drawable.ic_dot_blue
            "Oluja" -> R.drawable.ic_dot_red
            else -> R.drawable.ic_dot_green
        }
        holder.statusIndikator.setImageResource(dotRes)

        if (prognoza != null) {
            val temp = if (fahrenheit)
                "${(prognoza.temperatura * 9f / 5f + 32f).toInt()}°F"
            else
                "${prognoza.temperatura.toInt()}°"

            val minMax = if (fahrenheit)
                "${(prognoza.maxTemp * 9f / 5f + 32f).toInt()}°F / ${(prognoza.minTemp * 9f / 5f + 32f).toInt()}°F"
            else
                "${prognoza.maxTemp.toInt()}° / ${prognoza.minTemp.toInt()}°"

            holder.tvTemperatura.text = temp
            holder.tvOpis.text = prognoza.opisVremena
            holder.tvMinMax.text = minMax

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
            holder.ivWeatherIcon.setImageResource(iconRes)
        } else {
            holder.tvTemperatura.text = if (fahrenheit) "--°F" else "--°"
            holder.tvOpis.text = ""
            holder.tvMinMax.text = ""
            holder.ivWeatherIcon.setImageResource(R.drawable.ic_weather_cloudy)
        }

        holder.itemView.setOnClickListener { onClick(lok) }
    }

    class DiffCallback : DiffUtil.ItemCallback<Lokacija>() {
        override fun areItemsTheSame(oldItem: Lokacija, newItem: Lokacija) =
            oldItem.naziv == newItem.naziv && oldItem.drzava == newItem.drzava

        override fun areContentsTheSame(oldItem: Lokacija, newItem: Lokacija) =
            oldItem == newItem
    }
}
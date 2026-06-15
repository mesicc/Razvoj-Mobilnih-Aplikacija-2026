package ba.etf.weatherwatch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.weatherwatch.R
import ba.etf.weatherwatch.model.DnevnaPrognoza

class DnevnaPrognozeAdapter(
    private val stavke: List<DnevnaPrognoza>,
    private val fahrenheit: Boolean
) : RecyclerView.Adapter<DnevnaPrognozeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDan: TextView = view.findViewById(R.id.tvDan)
        val ivDnevnaIkona: ImageView = view.findViewById(R.id.ivDnevnaIkona)
        val tvDnevnaPadavine: TextView = view.findViewById(R.id.tvDnevnaPadavine)
        val tvDnevnaMinTemp: TextView = view.findViewById(R.id.tvDnevnaMinTemp)
        val tvDnevnaMaxTemp: TextView = view.findViewById(R.id.tvDnevnaMaxTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dnevna_prognoza, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stavka = stavke[position]
        holder.tvDan.text = stavka.dan

        val minTemp = if (fahrenheit)
            "${(stavka.minTemp * 9f / 5f + 32f).toInt()}°"
        else
            "${stavka.minTemp.toInt()}°"

        val maxTemp = if (fahrenheit)
            "${(stavka.maxTemp * 9f / 5f + 32f).toInt()}°"
        else
            "${stavka.maxTemp.toInt()}°"

        holder.tvDnevnaMinTemp.text = minTemp
        holder.tvDnevnaMaxTemp.text = maxTemp

        if (stavka.padavinePostotak > 0)
            holder.tvDnevnaPadavine.text = "${stavka.padavinePostotak}%"
        else
            holder.tvDnevnaPadavine.text = ""

        val iconRes = when (stavka.vrijemeTipa) {
            "sunny" -> R.drawable.ic_weather_sunny
            "partly_cloudy" -> R.drawable.ic_weather_partly_cloudy
            "cloudy" -> R.drawable.ic_weather_cloudy
            "rainy" -> R.drawable.ic_weather_rainy
            "snowy" -> R.drawable.ic_weather_snowy
            "stormy" -> R.drawable.ic_weather_stormy
            "foggy" -> R.drawable.ic_weather_foggy
            else -> R.drawable.ic_weather_cloudy
        }
        holder.ivDnevnaIkona.setImageResource(iconRes)
    }

    override fun getItemCount() = stavke.size
}
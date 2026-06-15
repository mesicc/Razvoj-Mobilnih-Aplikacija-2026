package ba.etf.weatherwatch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.weatherwatch.R
import ba.etf.weatherwatch.model.SatnaPrognoza

class SatnaPrognozeAdapter(
    private val stavke: List<SatnaPrognoza>,
    private val fahrenheit: Boolean
) : RecyclerView.Adapter<SatnaPrognozeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSat: TextView = view.findViewById(R.id.tvSat)
        val ivSatnaIkona: ImageView = view.findViewById(R.id.ivSatnaIkona)
        val tvSatnaTemp: TextView = view.findViewById(R.id.tvSatnaTemp)
        val tvSatnaPadavine: TextView = view.findViewById(R.id.tvSatnaPadavine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_satna_prognoza, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stavka = stavke[position]
        holder.tvSat.text = stavka.sat

        val temp = if (fahrenheit)
            "${(stavka.temperatura * 9f / 5f + 32f).toInt()}°F"
        else
            "${stavka.temperatura.toInt()}°"
        holder.tvSatnaTemp.text = temp

        if (stavka.padavinePostotak > 0)
            holder.tvSatnaPadavine.text = "${stavka.padavinePostotak}%"
        else
            holder.tvSatnaPadavine.text = ""

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
        holder.ivSatnaIkona.setImageResource(iconRes)
    }

    override fun getItemCount() = stavke.size
}
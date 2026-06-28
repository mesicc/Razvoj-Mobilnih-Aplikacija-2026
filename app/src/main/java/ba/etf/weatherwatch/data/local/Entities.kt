package ba.etf.weatherwatch.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prognoze")
data class PrognozaEntity(
    @PrimaryKey
    @ColumnInfo(name = "nazivLokacije")      val nazivLokacije: String,
    @ColumnInfo(name = "temperatura")        val temperatura: Float,
    @ColumnInfo(name = "osjecajTemperature") val osjecajTemperature: Float,
    @ColumnInfo(name = "opisVremena")        val opisVremena: String,
    @ColumnInfo(name = "brzinaVjetra")       val brzinaVjetra: Float,
    @ColumnInfo(name = "smjerVjetra")        val smjerVjetra: String,
    @ColumnInfo(name = "uvIndeks")           val uvIndeks: Float,
    @ColumnInfo(name = "padavine")           val padavine: Float?,
    @ColumnInfo(name = "vlaznost")           val vlaznost: Int,
    @ColumnInfo(name = "pritisak")           val pritisak: Int,
    @ColumnInfo(name = "vidljivost")         val vidljivost: Int,
    @ColumnInfo(name = "oblacnost")          val oblacnost: Int,
    @ColumnInfo(name = "minTemp")            val minTemp: Float,
    @ColumnInfo(name = "maxTemp")            val maxTemp: Float,
    @ColumnInfo(name = "vrijemeTipa")        val vrijemeTipa: String,
    @ColumnInfo(name = "prognozaPoSatimaJson") val prognozaPoSatimaJson: String,
    @ColumnInfo(name = "prognozaDaniJson")   val prognozaDaniJson: String,
    @ColumnInfo(name = "vrijemeAzuriranja")  val vrijemeAzuriranja: Long = System.currentTimeMillis()
)

@Entity(tableName = "lokacije")
data class LokacijaEntity(
    @PrimaryKey
    @ColumnInfo(name = "naziv")          val naziv: String,
    @ColumnInfo(name = "drzava")         val drzava: String,
    @ColumnInfo(name = "latitude")       val latitude: Double,
    @ColumnInfo(name = "longitude")      val longitude: Double,
    @ColumnInfo(name = "tipPrikaza")     val tipPrikaza: String,
    @ColumnInfo(name = "korisnikUpisan") val korisnikUpisan: Boolean = true
)
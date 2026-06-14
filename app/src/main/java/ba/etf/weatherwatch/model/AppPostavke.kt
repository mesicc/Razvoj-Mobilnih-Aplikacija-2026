package ba.etf.weatherwatch.model

data class AppPostavke(
    val tema: String = "auto",
    val jezik: String = "bs",
    val jedinice: String = "celsius",
    val notifikacije: Boolean = true,
    val notifikacijeOluja: Boolean = true
)
package ba.etf.weatherwatch.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PrognozaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun spremi(prognoza: PrognozaEntity)

    @Query("SELECT * FROM prognoze WHERE nazivLokacije = :naziv LIMIT 1")
    suspend fun getByNaziv(naziv: String): PrognozaEntity?

    @Query("SELECT * FROM prognoze")
    fun getAll(): Flow<List<PrognozaEntity>>

    @Query("DELETE FROM prognoze WHERE nazivLokacije = :naziv")
    suspend fun obrisi(naziv: String)

    @Query("DELETE FROM prognoze")
    suspend fun obrisiSve()

    @Query("SELECT COUNT(*) FROM prognoze")
    fun getBrojKesiranih(): Flow<Int>
}

@Dao
interface LokacijaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(lokacija: LokacijaEntity)

    @Query("SELECT * FROM lokacije ORDER BY naziv ASC")
    suspend fun getAll(): List<LokacijaEntity>

    @Query("DELETE FROM lokacije WHERE naziv = :naziv")
    suspend fun obrisi(naziv: String)
}


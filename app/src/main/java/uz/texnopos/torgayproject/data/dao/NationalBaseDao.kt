package uz.texnopos.torgayproject.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import uz.texnopos.torgayproject.data.model.Arxeologiya
import uz.texnopos.torgayproject.data.model.Muzeyler
import uz.texnopos.torgayproject.data.model.National
import uz.texnopos.torgayproject.data.model.Tabiyat

@Dao
interface NationalBaseDao {

    //Arxeologiya
    @Query("SELECT * FROM Arxeologiya")
    fun getArxeologiya() : List<Arxeologiya>

    @Query("SELECT * FROM Arxeologiya WHERE id = :id")
    fun getArxeologiyaById(id: Int) : Arxeologiya

    @Query("SELECT * FROM Arxeologiya WHERE name like :word")
    fun searchArxeologiyaByName(word: String) : List<Arxeologiya>

    @Query("SELECT * FROM Arxeologiya WHERE isFavorite = 1")
    fun getFavoriteArxeologiya() : List<Arxeologiya>

    @Update
    fun updateMilliy(arxeologiya: Arxeologiya)

    //Milliy
    @Query("SELECT * FROM Milliy")
    fun getNational() : List<National>

    @Query("SELECT * FROM Milliy WHERE id = :id")
    fun getNationalById(id: Int) : National

    @Query("SELECT * FROM Milliy WHERE name like :word")
    fun searchNationalByName(word: String) : List<National>

    @Query("SELECT * FROM Milliy WHERE isFavorite = 1")
    fun getFavoriteNational() : List<National>

    @Update
    fun updateMilliy(milliy: National)

    //Muzey
    @Query("SELECT * FROM Muzeyler")
    fun getMuzeyler() : List<Muzeyler>

    @Query("SELECT * FROM Muzeyler WHERE id = :id")
    fun getMuzeylerById(id: Int): Muzeyler

    @Query("SELECT * FROM Muzeyler WHERE name like :word")
    fun searchMuzeyByName(word: String) : List<Muzeyler>

    //Tábiyat
    @Query("SELECT * FROM Tabiyat")
    fun getTabiyat() : List<Tabiyat>

    @Query("SELECT * FROM Tabiyat WHERE id = :id")
    fun getTabiyatById(id: Int): Tabiyat

    @Query("SELECT * FROM Tabiyat WHERE name like :word")
    fun searchTabiyatByName(word: String) : List<Tabiyat>
}
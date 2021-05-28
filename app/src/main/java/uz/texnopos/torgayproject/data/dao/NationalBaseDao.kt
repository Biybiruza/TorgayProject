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
    fun getFavorite() : List<Arxeologiya>

    @Update
    fun updateArxeologiya(arxeologiya: Arxeologiya)

    //Milliy
    @Query("SELECT * FROM Milliy")
    fun getNational() : List<National>

    @Query("SELECT * FROM Milliy WHERE id = :id")
    fun getNationalById(id: Int) : National

    @Query("SELECT * FROM Milliy WHERE name like :word")
    fun searchNationalByName(word: String) : List<National>

    //Muzey
    @Query("SELECT * FROM Muzeyler")
    fun getMuzeyler() : List<Muzeyler>

    @Query("SELECT * FROM Muzeyler WHERE id = :id")
    fun getMuzeylerById(id: Int): Muzeyler

    //Tábiyat
    @Query("SELECT * FROM Tabiyat")
    fun getTabiyat() : List<Tabiyat>

    @Query("SELECT * FROM Tabiyat WHERE id = :id")
    fun getTabiyatById(id: Int): Tabiyat
}
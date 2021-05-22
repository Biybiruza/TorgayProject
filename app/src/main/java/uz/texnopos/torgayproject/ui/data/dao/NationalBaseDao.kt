package uz.texnopos.torgayproject.ui.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.torgayproject.ui.data.model.Muzeyler
import uz.texnopos.torgayproject.ui.data.model.Tabiyat

@Dao
interface NationalBaseDao {

    @Query("SELECT * FROM Muzeyler")
    fun getMuzeyler() : List<Muzeyler>

    @Query("SELECT * FROM Muzeyler WHERE id = :id")
    fun getMuzeylerById(id: Int): Muzeyler

    @Query("SELECT * FROM Tabiyat")
    fun getTabiyat() : List<Tabiyat>

    @Query("SELECT * FROM Tabiyat WHERE id = :id")
    fun getTabiyatById(id: Int): Tabiyat

}
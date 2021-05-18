package uz.texnopos.torgayproject.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.torgayproject.data.model.Arxeologiya
import uz.texnopos.torgayproject.data.model.National

@Dao
interface NationalBaseDao {

    @Query("SELECT * FROM Arxeologiya")
    fun getArxeologiya() : List<Arxeologiya>

    @Query("SELECT * FROM Arxeologiya WHERE id = :id")
    fun getArxeologiyaById(id: Int) : Arxeologiya

    @Query("SELECT * FROM Milliy")
    fun getNational() : List<National>

    @Query("SELECT * FROM Milliy WHERE id = :id")
    fun getNationalById(id: Int) : National


}
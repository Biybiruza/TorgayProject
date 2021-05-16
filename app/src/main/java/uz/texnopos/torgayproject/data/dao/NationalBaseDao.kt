package uz.texnopos.torgayproject.data.dao

import androidx.room.Dao
import androidx.room.Query
import uz.texnopos.torgayproject.data.model.Arxeologiya
import uz.texnopos.torgayproject.data.model.National

@Dao
interface NationalBaseDao {

    @Query("SELECT * FROM Arxeologiya")
    fun getArxeologiya() : List<Arxeologiya>

    @Query("SELECT * FROM Milliy")
    fun getNational() : List<National>


}
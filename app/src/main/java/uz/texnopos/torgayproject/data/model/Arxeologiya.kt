package uz.texnopos.torgayproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Arxeologiya")
data class Arxeologiya(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "text") val text:String
)
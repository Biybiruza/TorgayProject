package uz.texnopos.torgayproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Milliy")
data class National (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "text") val text:String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Int
)
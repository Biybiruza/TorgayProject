package uz.texnopos.torgayproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.torgayproject.data.dao.NationalBaseDao
import uz.texnopos.torgayproject.data.model.Arxeologiya
import uz.texnopos.torgayproject.data.model.Muzeyler
import uz.texnopos.torgayproject.data.model.National
import uz.texnopos.torgayproject.data.model.Tabiyat

@Database(entities = [Arxeologiya::class, National::class,Muzeyler::class,Tabiyat::class],version = 2)
abstract class TorgayDataBase : RoomDatabase(){

    companion object{
        lateinit var INSTANCE : TorgayDataBase
        fun getInstance(context:Context) : TorgayDataBase{
            if(!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(
                    context, TorgayDataBase::class.java,
                    "Book.db"
                )
                    .createFromAsset("Book.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
        abstract fun dao():NationalBaseDao
}
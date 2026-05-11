package com.example.wefit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wefit.model.Vezba
import com.example.wefit.model.Trening
import com.example.wefit.model.TreningVezba

@Database(
    entities = [Vezba::class, Trening::class, TreningVezba::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WefiDatabase : RoomDatabase() {

    abstract fun vezbaDao(): VezbaDao
    abstract fun treningDao(): TreningDao
    abstract fun treningVezbaDao(): TreningVezbaDao

    companion object {
        @Volatile
        private var Instance: WefiDatabase? = null

        fun getDatabase(context: Context): WefiDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    WefiDatabase::class.java,
                    "wefi_database"
                ).build().also { Instance = it }
            }
        }
    }
}
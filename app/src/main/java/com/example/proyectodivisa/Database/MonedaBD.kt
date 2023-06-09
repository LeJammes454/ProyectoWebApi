package com.example.proyectodivisa.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Moneda::class], version = 1, exportSchema = false)
abstract class MonedaBD  : RoomDatabase(){
    abstract fun MonedaDao(): MonedaDao

    companion object {
        private var INSTANCE: MonedaBD? = null
        fun getDatabase(context: Context, scope: CoroutineScope): MonedaBD {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,MonedaBD::class.java, "moneda")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
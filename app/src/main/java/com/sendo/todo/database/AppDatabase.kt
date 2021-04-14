package com.sendo.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // TodoDAO
    abstract fun todoDao() : TodoDAO

    companion object {
        private var instance : AppDatabase? = null
        fun getInstance(context : Context) : AppDatabase{
            if (instance == null) {
                // CREATE DATABASE
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Todo.db")
                    // kalo mau query harus berjalan di background,
                    // tapi karena ini cmn demo jadiin foreground
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDatabase
        }
    }
}
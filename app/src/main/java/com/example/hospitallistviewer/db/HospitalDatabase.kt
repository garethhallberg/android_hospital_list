package com.example.hospitallistviewer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Hospital::class), version = 1, exportSchema = true)

abstract class HospitalDatabase : RoomDatabase() {

    abstract fun hospitalDao(): HospitalDao


    companion object {
        @Volatile
        private var INSTANCE: HospitalDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): HospitalDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HospitalDatabase::class.java,
                    "hospital_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
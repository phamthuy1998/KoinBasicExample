package com.thuypham.ptithcm.simplebaseapp.data.local

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [],
    version = DatabaseMigrations.DB_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "App_Database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val temp = INSTANCE
            if (temp != null) return temp
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
//                    .addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .fallbackToDestructiveMigration() // ignore migrate
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
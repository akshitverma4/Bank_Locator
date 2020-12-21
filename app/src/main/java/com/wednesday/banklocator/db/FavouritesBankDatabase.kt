package com.wednesday.banklocator.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wednesday.banklocator.model.IfscResponse


@Database(
    entities = [IfscResponse::class],
    version = 1
)

abstract class FavouritesBankDatabase : RoomDatabase() {

    abstract fun getBanksDao(): IfscDao

    companion object {
        @Volatile
        private var instance: FavouritesBankDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FavouritesBankDatabase::class.java,
                "favourites_bank_db.db"
            ).build()
    }
}
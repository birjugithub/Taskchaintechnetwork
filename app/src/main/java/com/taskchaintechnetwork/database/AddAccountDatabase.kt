package com.taskchaintechnetwork.database

import androidx.room.Database
import com.taskchaintechnetwork.dao.AddAccountDao
import com.taskchaintechnetwork.model.AddAccount
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AddAccount::class], version = 1, exportSchema = false)
abstract class AddAccountDatabase : RoomDatabase() {

    abstract fun accountDao(): AddAccountDao

    companion object {
        @Volatile
        private var INSTANCE: AddAccountDatabase? = null

        fun getDatabase(context: Context): AddAccountDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AddAccountDatabase::class.java,
                    "account_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

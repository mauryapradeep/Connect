package com.example.connect

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.connect.roomdb.Database
import com.example.connect.roomdb.dbName

object DbBuilder {

    private  var database: Database? = null

    fun getdb(context: Context): Database?{

        if(database == null) {
            database = Room.databaseBuilder(
                context,
                Database::class.java,
                dbName
            ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
//        val contactDao = database.contactDao()
        return database
    }
}
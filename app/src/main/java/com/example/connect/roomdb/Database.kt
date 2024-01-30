package com.example.connect.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.connect.roomdb.dao.ContactDao
import com.example.connect.roomdb.entity2.Contact

@Database(entities = [Contact::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract  fun contactDao(): ContactDao

}
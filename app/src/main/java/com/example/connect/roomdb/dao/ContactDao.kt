package com.example.connect.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.connect.roomdb.entity2.Contact

@Dao
interface ContactDao {

//  Create
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createContact(contact: Contact):Long

// update
    @Update
    fun updateContact(contact: Contact)

// read
//    for all the data
    @Query("SELECT * FROM CONTACT")
    fun readContact(): List<Contact>

    // for the specific data
    @Query("SELECT * FROM CONTACT WHERE id= :id1 ")
    fun readContact(id1 : Int): Contact

// delete
    @Delete
     fun deleteContact(contact: Contact)

}
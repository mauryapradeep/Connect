package com.example.connect.mvvmarch

import android.content.Context
import android.provider.ContactsContract.Contacts
import com.example.connect.DbBuilder
import com.example.connect.roomdb.Database
import com.example.connect.roomdb.entity2.Contact

class Repo(var context : Context) {

    var database : Database? = null

    init {
        database = DbBuilder.getdb(context)
    }


    fun getData(): List<Contact>?{
       return  database?.contactDao()!!?.readContact()
    }

    fun insert(contact: Contact){
        database?.contactDao()?.createContact(contact)
    }

    fun delete(contact: Contact){
        database?.contactDao()?.deleteContact(contact)
    }

    fun update(contact: Contact){
        database?.contactDao()?.updateContact(contact)
    }



}
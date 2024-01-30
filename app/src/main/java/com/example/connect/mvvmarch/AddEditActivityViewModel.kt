package com.example.connect.mvvmarch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.connect.DbBuilder
import com.example.connect.roomdb.Database
import com.example.connect.roomdb.entity2.Contact

class AddEditActivityViewModel(application: Application)  : AndroidViewModel(application){

    var  db : Database? = null
    init {
        var db = DbBuilder.getdb(application)
    }

    fun storeData(contact: Contact){
        db?.contactDao()!!?.createContact(contact)
    }
    
}
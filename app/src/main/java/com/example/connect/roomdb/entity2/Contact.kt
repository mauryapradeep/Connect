package com.example.connect.roomdb.entity2


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact (

    @PrimaryKey var id: Int? = null,
    var profile: ByteArray? = null,
    var name: String?= null,
    var phoneNumber: String? = null,
    var email: String ? = null

)
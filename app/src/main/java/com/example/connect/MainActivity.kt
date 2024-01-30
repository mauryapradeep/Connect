package com.example.connect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connect.databinding.ActivityMainBinding
import com.example.connect.roomdb.entity2.Contact

class MainActivity : AppCompatActivity() {

    private val binding :  ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var contactList =  ArrayList<Contact>()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


//        DbBuilder.getdb(this)?.contactDao()?.createContact(Contact(name = "Pradeep Kumar", phoneNumber = "1234567890"))



        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, AddEditActivity::class.java) )
        }


        DbBuilder.getdb(this)?.contactDao()?.let {
            contactList.addAll(it.readContact())
        }

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = ContactAdapter(contactList,this)



    }


}
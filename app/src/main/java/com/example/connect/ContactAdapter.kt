package com.example.connect

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ActivityAddEditBinding
import com.example.connect.databinding.ContactItemBinding
import com.example.connect.roomdb.entity2.Contact

class ContactAdapter(var contactList: List<Contact>, var  context: Context):RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    inner class  MyViewHolder(val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ContactItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var contact = contactList[position]

        if(contact.profile != null){

            var imageByte = contact.profile

            if(imageByte != null){
                var image = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                holder.binding.profile.setImageBitmap(image)
                holder.binding.profile.visibility = View.VISIBLE
                holder.binding.sign.visibility = View.GONE

            }else{
                var splitName =  contact.name?.split(" ")
                var sign = ""
//        var flag = 0
                splitName?.forEach{
                    if(it.isNotEmpty() ){
                        sign += it[0]
//                flag++
                    }
                }

                holder.binding.sign.text = sign
                holder.binding.profile.visibility = View.GONE
                holder.binding.sign.visibility = View.VISIBLE
            }

        }else{
            var splitName =  contact.name?.split(" ")
            var sign = ""
//        var flag = 0
            splitName?.forEach{
                if(it.isNotEmpty() ){
                    sign += it[0]
//                flag++
                }
            }

            holder.binding.sign.text = sign
            holder.binding.profile.visibility = View.GONE
            holder.binding.sign.visibility = View.VISIBLE
        }





        holder.binding.name.text = contact.name
        holder.binding.phone.text = contact.phoneNumber
        holder.binding.email.text = contact.email
    }


}
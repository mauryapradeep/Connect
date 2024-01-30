package com.example.connect

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.connect.databinding.ActivityAddEditBinding
import com.example.connect.roomdb.entity2.Contact
import com.github.dhaval2404.imagepicker.ImagePicker

class AddEditActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }

    var contact = Contact()

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!


                binding.imageView.setImageURI(fileUri)
                val imageBytes = contentResolver.openInputStream(fileUri)?.readBytes()
                contact.profile = imageBytes


            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageView.setOnLongClickListener{

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.image_dialog)

            var image = findViewById<ImageView>(R.id.image)
            var imageObject =  binding.imageView.drawable
            image?.setImageDrawable(imageObject)

            //  for transparent the background of dialog
//            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//
//            val lp = WindowManager.LayoutParams()
////            lp.width = WndowManager.LayoutParams.WRAP_CONTENT
////            lp.blurBehindRadius = 20
//            lp.flags =  WindowManager.LayoutParams.FLAG_BLUR_BEHIND
//            dialog.window?.attributes = lp
            dialog.show()
            true
        }

        binding.saveContact.setOnClickListener{
            contact.name = binding.name.editText?.text.toString()
            contact.phoneNumber = binding.phone.editText?.text.toString()
            contact.email = binding.email.editText?.text.toString()

            var db = DbBuilder.getdb(this)
            var i = db?.contactDao()?.createContact(contact)
            if(i != null){
                if(i>0){
                    Toast.makeText(this@AddEditActivity, i.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.imageView.setOnClickListener{
            ImagePicker.with(this)
                .compress(1)
//                crop()//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

    }
}
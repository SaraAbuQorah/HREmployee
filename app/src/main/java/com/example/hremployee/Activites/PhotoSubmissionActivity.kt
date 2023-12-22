package com.example.hremployee.Activites

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hremployee.R

import android.content.Intent

import android.graphics.drawable.Drawable
import android.view.Window

import android.widget.*
import com.example.hremployee.Module.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class PhotoSubmissionActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var imageview: ImageView
    private lateinit var originalImage: Drawable
    private lateinit var nameTextView: TextView
    private lateinit var dialog: Dialog

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_submission)
        nameTextView =findViewById(R.id.title)

        showProgressBar()
        val user = Firebase.auth.currentUser
        val uid = user?.uid.toString()
        val database = FirebaseDatabase.getInstance().reference.child("Users").child(uid)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(User::class.java)
                userData?.let {
                    val username = it.username
                    nameTextView.text = username
                    hideProgressBar()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()

            }
        })
        val imageView = findViewById<ImageView>(R.id.uploadimg)
        originalImage = imageView.drawable
        button = findViewById(R.id.uploadbut)
        button2 = findViewById(R.id.submitimg)
        imageview = findViewById(R.id.uploadimg)
        button.setOnClickListener {


            pickImageGallery()
        }
        button2.setOnClickListener {
            imageView.setImageDrawable(originalImage)
            showToast("Your image has been submitted successfully")
        }
        val arrowBack=findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    private fun showProgressBar() {
        dialog = Dialog(this@PhotoSubmissionActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    private fun hideProgressBar() {
        dialog.dismiss()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageview.setImageURI(data?.data)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
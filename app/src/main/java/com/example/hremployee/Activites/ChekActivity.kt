package com.example.hremployee.Activites

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ChekActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chek)

        val myButton = findViewById<Button>(R.id.employeename)
        val myButton1 = findViewById<Button>(R.id.Cheko)
        val myButton2 = findViewById<Button>(R.id.Cheki)
        val myTextView1 = findViewById<TextView>(R.id.checedin)
        val myTextView2 = findViewById<TextView>(R.id.checedOut)
        val idintificationNumberTF = findViewById<EditText>(R.id.identificationnumber)
        val myTextView = findViewById<TextView>(R.id.inv)

        auth = Firebase.auth
        uid = auth.currentUser?.uid.toString()

        myButton.setOnClickListener {
            val idnum = idintificationNumberTF.text.toString()
            if (idnum.isNotEmpty()) {
                database = FirebaseDatabase.getInstance().getReference("Users")
                database.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var username: String? = null
                        for (userSnapshot in dataSnapshot.children) {
                            val user = userSnapshot.getValue(User::class.java)
                            if (user != null && user.idintificationNumber == idnum) {
                                username = user.username
                                break
                            }
                        }
                        if (username != null) {
                            idintificationNumberTF.text.clear()
                            myTextView.text = username
                        } else {
                            Toast.makeText(
                                this@ChekActivity,
                                "User does not exist",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(
                            this@ChekActivity,
                            "Failed to retrieve user information",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(
                    this,
                    "Please fill the Identification Number of the employee",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        myButton2.setOnClickListener {
            myTextView1.visibility = View.VISIBLE
            myTextView2.visibility = View.INVISIBLE
        }

        myButton1.setOnClickListener {
            myTextView2.visibility = View.VISIBLE
            myTextView1.visibility = View.INVISIBLE
        }

        val arrowBack = findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }
    }

}

package com.example.hremployee.Activites

import android.content.ContentValues.TAG
import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.hremployee.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String

    val admin = "Admin1@gmail.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val edittext = findViewById<EditText>(R.id.email)
        val editTextPass=findViewById<EditText>(R.id.pass)
        val buttonClick = findViewById<Button>(R.id.login)


        buttonClick.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)
            var email = edittext.text.toString()
            var pass = editTextPass.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == admin) {
                intent.putExtra("logInAsEmployee", "false")
                startActivity(intent)
                finish()
            }else {
                uid = auth.currentUser?.uid.toString()
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            intent.putExtra("logInAsEmployee", "true")
                            startActivity(intent)
                            finish()

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }
            }
        }

    }

}
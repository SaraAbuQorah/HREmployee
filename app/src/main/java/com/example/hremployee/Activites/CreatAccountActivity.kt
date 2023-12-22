package com.example.hremployee.Activites


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class CreatAccountActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    val myCalendar: Calendar = Calendar.getInstance()

    private lateinit var upload: Button
    private lateinit var fromDate: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var database :DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri : Uri

    private lateinit var userNameEd: EditText
    private lateinit var emailEd: EditText
    private lateinit var passwordEd: EditText
    private lateinit var phoneNumberEd: EditText
    private lateinit var identificationNumberEd: EditText
    private lateinit var positionEd: EditText
    private lateinit var departmentEd: Spinner
    private lateinit var creataccsubmit: Button



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creat_account)
        auth = Firebase.auth
        userNameEd = findViewById(R.id.Usern)
        emailEd = findViewById(R.id.email)
        passwordEd= findViewById(R.id.pass)
        phoneNumberEd=findViewById(R.id.phonenum)
        identificationNumberEd=findViewById(R.id.Num)
        positionEd=findViewById(R.id.position)
        departmentEd=findViewById(R.id.positionName)
        creataccsubmit=findViewById(R.id.creataccsbumit)
        fromDate= findViewById(R.id.fromDate)




        creataccsubmit.setOnClickListener {

            val userName = userNameEd.text.toString().trim()
            val email = emailEd.text.toString().trim()
            val password = passwordEd.text.toString().trim()
            val phoneNumber = phoneNumberEd.text.toString().trim()
            val identificationNumber = identificationNumberEd.text.toString().trim()
            val position = positionEd.text.toString().trim()
            val department = departmentEd.selectedItem.toString()
            val fromDate2 = fromDate.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val newUserId = task.result?.user?.uid

                        database = FirebaseDatabase.getInstance().getReference("Users")
                        val user = User(
                            userName,
                            email,
                            phoneNumber,
                            identificationNumber,
                            department,
                            position,
                            fromDate2
                        )
                        if (newUserId != null) {
                            database.child(newUserId).setValue(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { exception ->
                                    Toast.makeText(
                                        this,
                                        "Sign up failed: ${exception.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    } else {
                        Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }


            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(this, "Please enter phone Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(identificationNumber)) {
                Toast.makeText(this, "Please enter identification Number", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(position)) {
                Toast.makeText(this, "Please enter position", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }


        upload= findViewById(R.id.uplod)
        upload.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
        val fromDateDialog =
            DatePickerDialog.OnDateSetListener { fromDate, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateFrom()
            }
        fromDate.setOnClickListener{
            DatePickerDialog(
                this, fromDateDialog,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        val arrowBack=findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }

        val items = arrayOf("Development", "Sales", "Accounting", "marketing","Human Resources","Quality Assurance")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        departmentEd.adapter = adapter
        val departmentSpinner: Spinner = findViewById(R.id.positionName)
        departmentSpinner.adapter = adapter

        departmentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateFrom() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        fromDate.setText(dateFormat.format(myCalendar.time))
    }

}
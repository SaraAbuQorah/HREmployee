package com.example.hremployee.Activites

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hremployee.Adapters.SubmisionsAdapter
import com.example.hremployee.Module.SubmissionsDataClass
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class SubmissionsActivity : AppCompatActivity() {

    lateinit var mylist: java.util.ArrayList<SubmissionsDataClass>
    lateinit var listRecyclerView2: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submissions)

        mylist= arrayListOf<SubmissionsDataClass>()
        listRecyclerView2=findViewById(R.id.listRecyclerView2)
        listRecyclerView2.layoutManager = GridLayoutManager(this, 1)
        listRecyclerView2.setHasFixedSize(true)
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("hind Abu Qorah ","Download Here? ","13-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        mylist.add(SubmissionsDataClass("Sara Abu Qorah ","Download Here ","11-Mar-2023",
            R.drawable.ic_person
        ))
        listRecyclerView2.adapter = SubmisionsAdapter(mylist)

        var arrowBack=findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }

    }


}
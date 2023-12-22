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
import com.example.hremployee.Adapters.NameListAdapter
import com.example.hremployee.Module.NameListClass
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class NameListActivity : AppCompatActivity() {

    lateinit var mylist: java.util.ArrayList<NameListClass>
    lateinit var listRecyclerView2: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_list)

        mylist = arrayListOf<NameListClass>()
        listRecyclerView2 = findViewById(R.id.namelist)
        listRecyclerView2.layoutManager = GridLayoutManager(this, 1)
        listRecyclerView2.setHasFixedSize(true)
        mylist.add(NameListClass("Sara Abu Qorah ", R.drawable.ic_person, "now"))
        mylist.add(NameListClass("Yasmeen Ezzelddin Mesmar ", R.drawable.ic_person, "2 min ago"))
        mylist.add(NameListClass("dina mohamad salem ", R.drawable.ic_person, "5 min ago"))
        mylist.add(NameListClass("ahmad moath saad ", R.drawable.ic_person, "10 min ago"))
        mylist.add(NameListClass("Sara Abu Qorah ", R.drawable.ic_person, "12 min ago"))
        mylist.add(NameListClass("ahmad moath saad ", R.drawable.ic_person, "20 min ago"))
        mylist.add(NameListClass("dina mohamad salem ", R.drawable.ic_person, "30 min ago"))
        mylist.add(NameListClass("Sara Abu Qorah ", R.drawable.ic_person, "now"))
        mylist.add(NameListClass("Sara Abu Qorah ", R.drawable.ic_person, "now"))
        mylist.add(NameListClass("Yasmeen Ezzelddin Mesmar ", R.drawable.ic_person, "2 min ago"))
        mylist.add(NameListClass("dina mohamad salem ", R.drawable.ic_person, "5 min ago"))
        mylist.add(NameListClass("ahmad moath saad ", R.drawable.ic_person, "10 min ago"))

        listRecyclerView2.adapter = NameListAdapter(mylist)


        val arrowBack = findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }


    }

}
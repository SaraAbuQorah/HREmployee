package com.example.hremployee.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hremployee.Adapters.EmployeeListAdapter
import com.example.hremployee.Adapters.EmployeeListNewAdapter
import com.example.hremployee.Module.EmployeeListNewClass
import com.example.hremployee.R

class EmployeeListNewtActivity : AppCompatActivity() {
    lateinit var mylist: java.util.ArrayList<EmployeeListNewClass>
    lateinit var listRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list_newt)


        mylist= arrayListOf<EmployeeListNewClass>()
        listRecyclerView=findViewById(R.id.listRecyclerView)
        listRecyclerView.layoutManager = GridLayoutManager(this, 1)
        listRecyclerView.setHasFixedSize(true)
        mylist.add(EmployeeListNewClass("Sara Abu Qorah ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Sami Sami ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Hind Abu Qorah ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("khaled saad ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Sami Sami ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Hind Abu Qorah ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("khaled saad ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Sara Abu Qorah ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Sami Sami ", R.drawable.ic_person))
        mylist.add(EmployeeListNewClass("Hind Abu Qorah ", R.drawable.ic_person))

        listRecyclerView.adapter = EmployeeListNewAdapter(mylist)


        var arrowBack=findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }


    }
}
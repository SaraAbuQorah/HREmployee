package com.example.hremployee.Activites

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hremployee.Adapters.EmployeeListAdapter
import com.example.hremployee.Module.EmployeeListClass
import com.example.hremployee.R

class EmployeeListActivity : AppCompatActivity() {

    lateinit var mylist: java.util.ArrayList<EmployeeListClass>
    lateinit var listRecyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        mylist= arrayListOf<EmployeeListClass>()
        listRecyclerView=findViewById(R.id.listRecyclerView)
        listRecyclerView.layoutManager = GridLayoutManager(this, 1)
        listRecyclerView.setHasFixedSize(true)
        mylist.add(EmployeeListClass("Sara Abu Qorah ", R.drawable.ic_person,"for 25:02 Min "))
        mylist.add(EmployeeListClass("Sami Sami ", R.drawable.ic_person,"for 18:35 Min"))
        mylist.add(EmployeeListClass("Hind Abu Qorah ", R.drawable.ic_person,"for 10:55 Min"))
        mylist.add(EmployeeListClass("khaled saad ", R.drawable.ic_person,"for 16:04 Min"))
        mylist.add(EmployeeListClass("Sara Abu Qorah ", R.drawable.ic_person,"for 20:13 Min"))
        mylist.add(EmployeeListClass("Sami Sami ", R.drawable.ic_person,"for 25:58 Min"))
        mylist.add(EmployeeListClass("Hind Abu Qorah ", R.drawable.ic_person,"for 22:17 Min"))
        mylist.add(EmployeeListClass("khaled saad ", R.drawable.ic_person,"for 30:03 Min"))
        mylist.add(EmployeeListClass("Sara Abu Qorah ", R.drawable.ic_person,"for 32:00 Min"))
        mylist.add(EmployeeListClass("Sami Sami ", R.drawable.ic_person,"for 40:17 Min"))
        mylist.add(EmployeeListClass("Hind Abu Qorah ", R.drawable.ic_person,"for 08:17 Min"))
        mylist.add(EmployeeListClass("khaled saad ", R.drawable.ic_person,"for 26:28 Min"))
        mylist.add(EmployeeListClass("Sara Abu Qorah ", R.drawable.ic_person,"for 02:32 Min"))
        mylist.add(EmployeeListClass("Sami Sami ", R.drawable.ic_person,"for 05:45 Min"))
        mylist.add(EmployeeListClass("Hind Abu Qorah ", R.drawable.ic_person,"for 35:02 Min"))
        mylist.add(EmployeeListClass("khaled saad ", R.drawable.ic_person,"for 12:22 Min"))
        mylist.add(EmployeeListClass("Sara Abu Qorah ", R.drawable.ic_person,"for 16:10 Min"))
        mylist.add(EmployeeListClass("Sami Sami ", R.drawable.ic_person,"for 09:12 Min"))
        mylist.add(EmployeeListClass("Hind Abu Qorah ", R.drawable.ic_person,"for 27:02 Min"))
        mylist.add(EmployeeListClass("khaled saad ", R.drawable.ic_person,"for 37:08 Min"))
        listRecyclerView.adapter = EmployeeListAdapter(mylist)


        var arrowBack=findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }

    }


}
package com.example.hremployee.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hremployee.Fragment.*
import com.example.hremployee.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    private var logInAsEmployee = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        logInAsEmployee = intent.getStringExtra("logInAsEmployee") == "true"


        if (logInAsEmployee) {
            loadFragment(EmployeeForEmp())
        } else {
            loadFragment(EmployeeForHr())
        }
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Employee -> {
                    if (logInAsEmployee) {
                        loadFragment(EmployeeForEmp())
                    } else {
                        loadFragment(EmployeeForHr())
                    }

                    true
                }
                R.id.Schedule -> {
                    if (logInAsEmployee) {
                        loadFragment(SchedualforEmp())
                    } else {
                        loadFragment(SchedualForHr())
                    }
                    true
                }
                R.id.Leave -> {
                    if (logInAsEmployee) {
                        loadFragment(LeaveForEmp())
                    } else {
                        loadFragment(LeaveForHr())
                    }
                    true
                }
                R.id.Write -> {
                    if (logInAsEmployee) {
                        loadFragment(WriteForEmp())
                    } else {
                        loadFragment(WriteForHr())
                    }
                    true
                }
                R.id.Notification -> {
                    if (logInAsEmployee) {
                        loadFragment(NotificationForEmp())
                    } else {
                        loadFragment(NotificationForHr())
                    }
                    true
                }
                else -> {
                    if (logInAsEmployee) {
                        loadFragment(EmployeeForHr())
                    } else {
                        loadFragment(EmployeeForEmp())
                    }
                    true
                }

            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
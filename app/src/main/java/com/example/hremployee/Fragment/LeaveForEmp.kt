package com.example.hremployee.Fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeaveForEmp.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeaveForEmp : Fragment() {
    private lateinit var nameTextView: TextView
    private lateinit var dialog: Dialog

    private var counterRunning = false
    private var sec = 0
    private var min = 0
    private var hour = 0
    private var secText = ""
    private var minText = ""
    private var hourText = ""

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_leave_for_emp, container, false)
        nameTextView =rootView.findViewById(R.id.pageName)
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

        val button2 = rootView.findViewById<Button>(R.id.leaveb)
        val button = rootView.findViewById<Button>(R.id.breakb)

        button.setOnClickListener {
            if (counterRunning) {
                counterRunning = false
                hour =0
                min = 0
                sec = -1
                button.text = " take break"
            } else {

                counterRunning = true
                button.text = "stop break"
                startCounter()
            }
        }
        button2.setOnClickListener {
            Toast.makeText(context, "Your request to leave has been sent", Toast.LENGTH_SHORT).show()
        }
        return rootView
    }
    private fun showProgressBar() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    private fun hideProgressBar() {
        dialog.dismiss()
    }

    private fun startCounter() {
        CoroutineScope(Dispatchers.Main).launch {
            while (counterRunning) {
                delay(1000)
                sec++

                if (sec < 10) {
                    secText ="0${sec.toString()}"
                } else if (sec > 59) {
                    sec=0
                    min++
                    secText ="0${sec.toString()}"
                } else{
                    secText =  sec.toString()
                }
                if (min < 10) {
                    minText ="0${min.toString()}"
                } else if (min > 59) {
                    min=0
                    hour++
                    minText ="0${min.toString()}"
                } else{
                    minText =  min.toString()
                }

                if (hour < 24) {
                    hourText ="0${hour.toString()}"
                } else if (hour > 24) {
                    hour=0
                    hourText ="0${hour.toString()}"
                } else{
                    hourText =  hour.toString()
                }


                view?.findViewById<TextView>(R.id.counter)?.text =hourText+":"+minText+":"+secText
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeaveForEmp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeaveForEmp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
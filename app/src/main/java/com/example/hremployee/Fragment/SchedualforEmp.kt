package com.example.hremployee.Fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.hremployee.Activites.AttendanceActivity
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SchedualforEmp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SchedualforEmp : Fragment() {
    private lateinit var nameTextView: TextView
    private lateinit var dialog: Dialog

    private val messages = listOf("you are checked In", "you are checked Out")
    private var counter = 0
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
        val rootView = inflater.inflate(R.layout.fragment_schedualfor_emp, container, false)
        nameTextView = rootView.findViewById(R.id.pageName)
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
                hideProgressBar()            }
        })

        val myButton = rootView.findViewById<Button>(R.id.chek)
        val Remotlywork = rootView.findViewById<Button>(R.id.Remotely)

        rootView.findViewById<Button>(R.id.Attendance).setOnClickListener {
            val intent = Intent(activity, AttendanceActivity::class.java)
            activity?.startActivity(intent)
        }
        myButton.setOnClickListener {

            Toast.makeText(context, messages[counter], Toast.LENGTH_SHORT).show()
            if (counter==0){
                myButton.text = "Check Out"}
            else myButton.text = "Check In"
            counter = (counter + 1) % messages.size
        }
        Remotlywork.setOnClickListener {
            Toast.makeText(
                context,
                "Your request to work remotely has been sent",
                Toast.LENGTH_SHORT
            ).show()
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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SchedualforEmp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SchedualforEmp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
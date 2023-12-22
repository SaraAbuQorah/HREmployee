package com.example.hremployee.Fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import com.example.hremployee.Activites.EmployeeListActivity
import com.example.hremployee.Activites.EmployeeListNewtActivity
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
 * Use the [EmployeeForHr.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmployeeForHr : Fragment() {
    private lateinit var nameTextView: TextView
    private lateinit var dialog: Dialog

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

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_employee, container, false)
            rootView.findViewById<Button>(R.id.android).setOnClickListener {
                val intent = Intent(activity, EmployeeListNewtActivity::class.java)
                activity?.startActivity(intent)
            }
            rootView.findViewById<Button>(R.id.sales1).setOnClickListener {
                val intent = Intent(activity, EmployeeListNewtActivity::class.java)
                activity?.startActivity(intent)
            }
            rootView.findViewById<Button>(R.id.acc).setOnClickListener {
                val intent = Intent(activity, EmployeeListNewtActivity::class.java)
                activity?.startActivity(intent)
            }
            rootView.findViewById<Button>(R.id.marketing).setOnClickListener {
                val intent = Intent(activity, EmployeeListNewtActivity::class.java)
                activity?.startActivity(intent)
            }
            rootView.findViewById<Button>(R.id.hr).setOnClickListener {
                val intent = Intent(activity, EmployeeListNewtActivity::class.java)
                activity?.startActivity(intent)
            }
            rootView.findViewById<Button>(R.id.qa).setOnClickListener {
                val intent = Intent(activity, EmployeeListNewtActivity::class.java)
                activity?.startActivity(intent)
            }
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment employee.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmployeeForHr().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Employee Names")
        builder.setMessage("Sara Yousef Abu Qorah")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
package com.example.hremployee.Fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.hremployee.Module.User
import com.example.hremployee.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EmployeeForEmp.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmployeeForEmp : Fragment() {
    private lateinit var nameTextView: TextView
    private lateinit var birthTextView: TextView
    private lateinit var positionTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var departmentTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var dialog: Dialog
    private var progressBar: ProgressBar? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String




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

        val view = inflater.inflate(R.layout.fragment_employee_for_emp, container, false)
        auth = Firebase.auth

        nameTextView = view.findViewById(R.id.userNameTv)
        birthTextView = view.findViewById(R.id.birthdayTv)
        phoneTextView = view.findViewById(R.id.phoneNumberTv)
        emailTextView = view.findViewById(R.id.emailTv)
        positionTextView = view.findViewById(R.id.positionTv)
        departmentTextView = view.findViewById(R.id.departmentTv)
        idTextView = view.findViewById(R.id.identificationNumberTv)
       // progressBar = view.findViewById<ProgressBar>(R.id.progress_Bar)

        uid = auth.currentUser?.uid.toString()
        val database = FirebaseDatabase.getInstance().reference.child("Users").child(uid)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userData = snapshot.getValue(User::class.java)
                userData?.let {
                    val username = it.username
                    val birthday = it.birthday
                    val phoneNumber = it.phoneNumber
                    val email = it.email
                    val position = it.position
                    val department =it.department
                    val idintificationNumber = it.idintificationNumber

                    nameTextView.text = username
                    birthTextView.text = birthday
                    phoneTextView.text = phoneNumber
                    emailTextView.text = email
                    positionTextView.text = position
                    departmentTextView.text = department
                    idTextView.text = idintificationNumber

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return view
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
         * @return A new instance of fragment EmployeeForEmp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmployeeForEmp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
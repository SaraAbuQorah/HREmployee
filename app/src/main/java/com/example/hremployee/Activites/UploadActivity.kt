
package com.example.hremployee.Activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.hremployee.R

@Suppress("DEPRECATION")
class UploadActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var submitimg: Button
    private lateinit var documentNameTextView: TextView

    companion object {
        private const val DOCUMENT_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        documentNameTextView = findViewById(R.id.documentNameTextView)
        button = findViewById(R.id.uploadbut)
        button.setOnClickListener {
            documentNameTextView.visibility = View.VISIBLE
            pickDocument()
        }
        submitimg = findViewById(R.id.submitimg)
        submitimg.setOnClickListener {
            documentNameTextView.visibility = View.INVISIBLE
        }


        val arrowBack=findViewById<LinearLayout>(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish()
        }
    }
    private fun pickDocument() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/*"
        startActivityForResult(intent, DOCUMENT_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DOCUMENT_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedDocumentUri: Uri? = data?.data
            val documentName = getDocumentName(selectedDocumentUri)
            documentNameTextView.text = documentName
        }
    }
    private fun getDocumentName(uri: Uri?): String {
        val contentResolver = contentResolver
        val cursor = contentResolver.query(uri!!, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayName = it.getString(it.getColumnIndexOrThrow("_display_name"))
                return displayName
            }
        }
        return ""
    }
}
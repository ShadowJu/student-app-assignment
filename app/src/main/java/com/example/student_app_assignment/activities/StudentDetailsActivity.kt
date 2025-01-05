package com.example.student_app_assignment.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_app_assignment.R
import com.example.student_app_assignment.repositories.StudentRepository
import java.util.UUID

class StudentDetailsActivity : AppCompatActivity() {
    private var studentUUID: String = "null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        title = getString(R.string.edit_student_title)
        studentUUID = intent.getStringExtra("studentUUID").toString()
        if (studentUUID != "null") {
            val student = StudentRepository.getStudent(UUID.fromString(studentUUID))

            if (student != null) {
                findViewById<TextView>(R.id.detailsName).text = student.name
                findViewById<TextView>(R.id.detailsId).text = student.id
                findViewById<TextView>(R.id.detailsAddress).text = student.address
                findViewById<TextView>(R.id.detailsPhone).text = student.phone
                findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
                val studentImageView: ImageView = findViewById(R.id.detailsImage)
                studentImageView.setImageResource(R.drawable.student_img)
            }
        }

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentUUID", studentUUID.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        var student = StudentRepository.getStudent(UUID.fromString(studentUUID))

        if(student != null) {
            findViewById<TextView>(R.id.detailsName).text = student.name
            findViewById<TextView>(R.id.detailsId).text = student.id
            findViewById<TextView>(R.id.detailsAddress).text = student.address
            findViewById<TextView>(R.id.detailsPhone).text = student.phone
            findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            // Re-fetch updated data
            var student = StudentRepository.getStudent(UUID.fromString(studentUUID))

            if(student != null) {
                findViewById<TextView>(R.id.detailsName).text = student.name
                findViewById<TextView>(R.id.detailsId).text = student.id
                findViewById<TextView>(R.id.detailsAddress).text = student.address
                findViewById<TextView>(R.id.detailsPhone).text = student.phone
                findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
            }
        }
    }
}

package com.example.student_app_assignment.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.student_app_assignment.R
import com.example.student_app_assignment.repositories.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    private var studentIndex: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        title = getString(R.string.edit_student_title)
        // Get the student index from the Intent
        studentIndex = intent.getIntExtra("studentIndex", -1)

        if (studentIndex != -1) {
            val student = StudentRepository.getStudent(studentIndex)

            // Populate the UI with the student's details
            findViewById<TextView>(R.id.detailsName).text = student.name
            findViewById<TextView>(R.id.detailsId).text = student.id
            findViewById<TextView>(R.id.detailsAddress).text = student.address
            findViewById<TextView>(R.id.detailsPhone).text = student.phone
            findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
            val studentImageView: ImageView = findViewById(R.id.detailsImage)
            studentImageView.setImageResource(R.drawable.student_img)
        }

        // Handle Edit button click
        findViewById<Button>(R.id.editButton).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentIndex", studentIndex)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Re-fetch the student details from the centralized database
        var student = StudentRepository.getStudent(studentIndex)

        // Update UI with refreshed data
        findViewById<TextView>(R.id.detailsName).text = student.name
        findViewById<TextView>(R.id.detailsId).text = student.id
        findViewById<TextView>(R.id.detailsAddress).text = student.address
        findViewById<TextView>(R.id.detailsPhone).text = student.phone
        findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            // Re-fetch updated data
            var student = StudentRepository.getStudent(studentIndex)

            // Update UI
            findViewById<TextView>(R.id.detailsName).text = student.name
            findViewById<TextView>(R.id.detailsId).text = student.id
            findViewById<TextView>(R.id.detailsAddress).text = student.address
            findViewById<TextView>(R.id.detailsPhone).text = student.phone
            findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
        }
    }
}

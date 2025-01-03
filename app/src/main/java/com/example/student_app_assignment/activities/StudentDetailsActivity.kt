package com.example.student_app_assignment.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.student_app_assignment.R
import com.example.student_app_assignment.repositories.StudentRepository

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        title = getString(R.string.edit_student_title)
        // Get the student index from the Intent
        val studentIndex = intent.getIntExtra("studentIndex", -1)

        if (studentIndex != -1) {
            val student = StudentRepository.getStudents()[studentIndex]

            // Populate the UI with the student's details
            findViewById<TextView>(R.id.detailsName).text = student.name
            findViewById<TextView>(R.id.detailsId).text = student.id
            findViewById<TextView>(R.id.detailsAddress).text = student.address
            findViewById<TextView>(R.id.detailsPhone).text = student.phone
            //findViewById<TextView>(R.id.studentCheckBox).text = student.isChecked
            val studentImageView: ImageView = findViewById(R.id.detailsImage)
            studentImageView.setImageResource(R.drawable.student_img)
        }
    }
}

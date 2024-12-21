package com.example.student_app_assignment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.Student

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val id = findViewById<EditText>(R.id.idInput).text.toString()
            val name = findViewById<EditText>(R.id.nameInput).text.toString()
            val phone = findViewById<EditText>(R.id.phoneInput).text.toString()
            val address = findViewById<EditText>(R.id.addressInput).text.toString()
            val newStudent = Student(name, id, phone, address, false)
            Database.students.add(newStudent)
            Database.students.forEach { student -> Log.d("Database", "Student: ${student.name}, ID: ${student.id}, Phone: ${student.phone}, address: ${student.address}, Checked: ${student.isChecked}")}
            finish()
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            finish()
        }
    }
}

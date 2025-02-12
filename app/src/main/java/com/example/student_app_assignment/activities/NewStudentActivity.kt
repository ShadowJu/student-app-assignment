package com.example.student_app_assignment.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_app_assignment.R
import com.example.student_app_assignment.repositories.StudentRepository
import com.example.student_app_assignment.models.Student
import java.util.UUID

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        title = getString(R.string.create_student_title)
        findViewById<Button>(R.id.addButton).setOnClickListener {
            val id = findViewById<EditText>(R.id.idInput).text.toString()
            val name = findViewById<EditText>(R.id.nameInput).text.toString()
            val phone = findViewById<EditText>(R.id.phoneInput).text.toString()
            val address = findViewById<EditText>(R.id.addressInput).text.toString()
            val newStudent = Student(UUID.randomUUID(), name, id, phone, address, false)
            if (id.isNotBlank() && name.isNotBlank() && phone.isNotBlank() && address.isNotBlank()) {
                StudentRepository.addStudent(newStudent);
                val intent = Intent()
                intent.putExtra("activity_key", "ADD_STUDENT")
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            finish()
        }
    }
}
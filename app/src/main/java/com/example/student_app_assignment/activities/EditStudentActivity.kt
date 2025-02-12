package com.example.student_app_assignment.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_app_assignment.R
import com.example.student_app_assignment.repositories.StudentRepository
import java.util.UUID

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        title = getString(R.string.student_details_title)

        val studentUUID = intent.getStringExtra("studentUUID")

        if (studentUUID != "") {
            val student = StudentRepository.getStudent(UUID.fromString(studentUUID))

            if (student != null) {
                findViewById<EditText>(R.id.studentName).setText(student.name)
                findViewById<EditText>(R.id.studentId).setText(student.id)
                findViewById<EditText>(R.id.studentPhone).setText(student.phone)
                findViewById<EditText>(R.id.studentAddress).setText(student.address)

                // Update the student details when the update button is clicked
                findViewById<Button>(R.id.updateButton).setOnClickListener {
                    var name = findViewById<EditText>(R.id.studentName).text.toString()
                    var id = findViewById<EditText>(R.id.studentId).text.toString()
                    var phone = findViewById<EditText>(R.id.studentPhone).text.toString()
                    var address = findViewById<EditText>(R.id.studentAddress).text.toString()
                    var isChecked = findViewById<CheckBox>(R.id.studentCheckbox).isChecked

                    if (id.isNotBlank() && name.isNotBlank() && phone.isNotBlank() && address.isNotBlank()) {
                        student.name = name
                        student.id = id
                        student.address = address
                        student.phone = phone
                        student.isChecked = isChecked
                        StudentRepository.updateStudentByIndex(UUID.fromString(studentUUID), student)
                        Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT)
                            .show()
                        returnToList()
                    } else {
                        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                    }
                }

                findViewById<Button>(R.id.deleteButton).setOnClickListener {
                    StudentRepository.deleteStudent(UUID.fromString(studentUUID))
                    Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
                    returnToList()
                }

                findViewById<Button>(R.id.cancelButton).setOnClickListener {
                    finish()
                }
            }
        }
    }

    private fun returnToList(){
        val intent = Intent(this, StudentListActivity::class.java)
        intent.putExtra("activity_key", "UPDATE_STUDENT")
        setResult(RESULT_OK, intent)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}

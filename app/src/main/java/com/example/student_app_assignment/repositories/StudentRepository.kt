package com.example.student_app_assignment.repositories

import android.util.Log
import com.example.student_app_assignment.models.Student
import java.util.UUID

object StudentRepository {
    private val students = mutableListOf<Student>(
    )

    fun getStudents(): MutableList<Student> {
        return students
    }

    fun getStudent(studentUUID: UUID): Student? {
        return students.find { it.uuid == studentUUID }
    }

    fun addStudent(student: Student) {
        students.add((student))
        this.students.forEach { student ->
            Log.d(
                "StudentRepository",
                "Student: ${student.name}, ID: ${student.id}, Phone: ${student.phone}, address: ${student.address}, Checked: ${student.isChecked}"
            )
        }
    }


    fun updateStudentByIndex(studentUUID: UUID, updatedStudent: Student?) {
        val index = students.indexOfFirst { it.uuid == studentUUID }
        if (index != -1) {
            if (updatedStudent != null) {
                students[index] = updatedStudent
            }
        }
    }

    fun deleteStudent(studentUUID: UUID) {
        students.removeIf { it.uuid == studentUUID }
    }
}
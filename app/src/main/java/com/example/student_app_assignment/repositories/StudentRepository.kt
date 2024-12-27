package com.example.student_app_assignment.repositories

import android.util.Log
import com.example.student_app_assignment.models.Student

object StudentRepository {
    val students = mutableListOf<Student>()

    fun addStudent(student: Student){
        students.add((student))
        this.students.forEach { student -> Log.d("StudentRepository", "Student: ${student.name}, ID: ${student.id}, Phone: ${student.phone}, address: ${student.address}, Checked: ${student.isChecked}")}
    }
}
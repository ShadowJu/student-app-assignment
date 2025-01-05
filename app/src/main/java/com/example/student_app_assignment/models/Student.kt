package com.example.student_app_assignment.models
import java.util.UUID

data class Student(
    val uuid: UUID,
    var id: String,
    var name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean
)
package com.example.student_app_assignment.models

data class Student(var name: String, var id: String, var phone: String, var address: String, var isChecked: Boolean){
    override fun equals(other: Any?): Boolean {
        var flag = false
        if (this === other) {
            flag = true
        } else if (other != null && javaClass == other.javaClass) {
            other as Student
            flag = id == other.id
        }
        return flag
    }
}
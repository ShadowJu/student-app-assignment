package com.example.studentsapp

data class Student(var name: String, var id: String, var phone: String, var address: String, var isChecked: Boolean){
    override fun equals(other: Any?): Boolean {
        if (this === other) { return true }
        if (other == null || javaClass != other.javaClass) { return false }
        other as Student
        return id == other.id
    }
}
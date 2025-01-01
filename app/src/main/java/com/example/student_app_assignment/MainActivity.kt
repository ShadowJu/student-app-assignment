package com.example.student_app_assignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.student_app_assignment.activites.StudentListActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, StudentListActivity::class.java))
    }
}

package com.example.student_app_assignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.student_app_assignment.activites.NewStudentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<FloatingActionButton>(R.id.AddStudent).setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }
}

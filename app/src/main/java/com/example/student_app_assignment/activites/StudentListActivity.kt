package com.example.student_app_assignment.activites

import StudentAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.node.ViewAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_app_assignment.R
import com.example.student_app_assignment.repositories.StudentRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val extraValue = data?.getStringExtra("activity_key")

                if (extraValue == "ADD_STUDENT"){
                    adapter.notifyItemInserted(StudentRepository.getStudent().size - 1)
                }

            } else {
                println("Activity canceled or failed.")
            }
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(StudentRepository.getStudent())
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.AddStudent).setOnClickListener {
            activityResultLauncher.launch(Intent(this, NewStudentActivity::class.java))
        }
    }
}
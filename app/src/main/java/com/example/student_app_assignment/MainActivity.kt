package com.example.student_app_assignment

import StudentAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_app_assignment.repositories.StudentRepository.students
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : ComponentActivity() {
    private lateinit var recyclerViewAdapter: StudentAdapter
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getStringExtra("key")
                recyclerViewAdapter.notifyItemInserted(students.size - 1)
            } else {
                Toast.makeText(this, "error during adding student", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerViewAdapter = StudentAdapter(students)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter

        findViewById<FloatingActionButton>(R.id.AddStudent).setOnClickListener {
            activityResultLauncher.launch(Intent(this, NewStudentActivity::class.java))
        }
    }
}

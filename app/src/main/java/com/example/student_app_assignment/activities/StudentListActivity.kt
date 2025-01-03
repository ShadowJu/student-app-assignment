package com.example.student_app_assignment.activities

import StudentAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_app_assignment.R
import com.example.student_app_assignment.listeners.OnStudentClickListener
import com.example.student_app_assignment.repositories.StudentRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity(), OnStudentClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.student_list_title)

        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val extraValue = data?.getStringExtra("activity_key")

                if (extraValue == "ADD_STUDENT") {
                    adapter.notifyItemInserted(StudentRepository.getStudents().size - 1)
                }
                if (extraValue == "UPDATE_STUDENT") {
                    adapter.notifyItemChanged(StudentRepository.getStudents().size - 1)
                }

            } else {
                Toast.makeText(this, result.resultCode, Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(StudentRepository.getStudents(), this)
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.AddStudent).setOnClickListener {
            activityResultLauncher.launch(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onStudentClick(index: Int) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("studentIndex", index)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyItemRangeChanged(0, StudentRepository.getStudents().size)
    }
}
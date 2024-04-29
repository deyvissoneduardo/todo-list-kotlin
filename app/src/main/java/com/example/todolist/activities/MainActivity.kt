package com.example.todolist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todolist.database.TaskDAO
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.TaskModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listTask = emptyList<TaskModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val taskDAO = TaskDAO(this)
        listTask = taskDAO.getAll()

        listTask.forEach{task ->
            Log.i("info__db", "Task ${task.description.toString()} \n")
        }
    }
}
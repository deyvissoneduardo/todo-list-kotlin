package com.example.todolist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.TaskAdapter
import com.example.todolist.database.TaskDAO
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.TaskModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listTask = emptyList<TaskModel>()
    private var taskAdapter: TaskAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }

        taskAdapter = TaskAdapter()
        binding.rvTarefas.adapter = taskAdapter
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        loadNewTask()
    }

    private fun loadNewTask(){
        val taskDAO = TaskDAO(this)
        listTask = taskDAO.getAll()
        taskAdapter?.loadTask(listTask)
    }
}
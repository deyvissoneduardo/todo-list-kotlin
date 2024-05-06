package com.example.todolist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        taskAdapter = TaskAdapter(
            {id -> confirmDeleteTask(id)}
        )
        binding.rvTarefas.adapter = taskAdapter
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)
    }

    private fun confirmDeleteTask(id: Int) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar exclusão!")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")
        alertBuilder.setPositiveButton("Sim"){_, _ ->
            val taskDAO = TaskDAO(this)
            if( taskDAO.delete(id) ){
                Toast.makeText(this, "Excluido com sucesso", Toast.LENGTH_SHORT).show()
            }
            loadNewTask()
        }
        alertBuilder.setNegativeButton("Não"){_, _ -> }
        alertBuilder.create().show()
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
package com.example.todolist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolist.database.TaskDAO
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.model.TaskModel

class AddTask : AppCompatActivity() {

    private val binding by lazy {
        ActivityAddTaskBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var task: TaskModel? = null
        val bundle = intent.extras
        if( bundle != null ){
            task = bundle.getSerializable("task") as TaskModel
            binding.editTarefa.setText(task.description)
        }

        binding.btnSalvar.setOnClickListener {
         if(binding.editTarefa.text.isNotEmpty()){
             if(task != null){
                 editTask(task)
             }else{
                 saveTask()
             }

         }else{
             Toast.makeText(this, "required", Toast.LENGTH_SHORT).show()
         }
        }
    }

    private fun saveTask() {
        val task = TaskModel(
            -1,
            binding.editTarefa.text.toString(),
            "default"
        );
        val taskDAO = TaskDAO(this)
        if (taskDAO.save(task)) {
            Toast.makeText(this, "salvo", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun editTask(task: TaskModel) {
        val newTask = TaskModel(
            task.idTask,
            binding.editTarefa.text.toString(),
            "default"
        )
        val taskDAO = TaskDAO(this)
        if (taskDAO.update(newTask)) {
            Toast.makeText(this, "atualizado com sucesso!!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
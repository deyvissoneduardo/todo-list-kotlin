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

        binding.btnSalvar.setOnClickListener {
         if(binding.editTarefa.text.isNotEmpty()){
             val task = TaskModel(
                 -1,
                 description = "description" ,
                 "default"
             );
             val taskDAO = TaskDAO(this)
             if(taskDAO.save(task)){
                 Toast.makeText(this,"salvo",Toast.LENGTH_SHORT).show()
                 finish()
             }
         }else{
             Toast.makeText(this, "required", Toast.LENGTH_SHORT).show()
         }
        }
    }
}
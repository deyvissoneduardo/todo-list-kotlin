package com.example.todolist.database

import com.example.todolist.model.TaskModel

interface ITaskDAO {
    fun save(task: TaskModel): Boolean
    fun update(task: TaskModel): Boolean
    fun delete(idTask: Int): Boolean
//    fun getAll(): List<TaskModel>
}
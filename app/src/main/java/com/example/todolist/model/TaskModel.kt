package com.example.todolist.model

import java.io.Serializable

data class TaskModel(
    val idTask: Int,
    val description: String,
    val date: String,
) : Serializable
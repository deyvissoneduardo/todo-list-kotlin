package com.example.todolist.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.todolist.model.TaskModel
import java.sql.SQLException

class TaskDAO(context: Context): ITaskDAO {

    private val createDatabase = DatabaseHelper(context).writableDatabase
    private val readDatabase = DatabaseHelper(context).readableDatabase
    override fun save(task: TaskModel): Boolean {
        val content = ContentValues()
        content.put(DatabaseHelper.COLUMN_DESCRIPTION, task.description)
        try {
            createDatabase.insert(
                DatabaseHelper.TABLE_NAME,
                null,
                content
            )
            Log.i("Insert Table ${DatabaseHelper.TABLE_NAME}", "success")
        }catch (e: SQLException){
            e.printStackTrace()
            Log.e("error insert table", e.message.toString())
            return false
        }
        return true
    }

    override fun update(task: TaskModel) : Boolean{
        return false;
    }

    override fun delete(idTask: Int) : Boolean{
        return false;
    }

//    override fun getAll(): List<TaskModel> {
//        return [];
//    }
}
package com.example.todolist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class DatabaseHelper(context:Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
    ) {

    companion object{
        const val DATABASE_NAME = "tasks.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "tasks_table"
        const val COLUMN_ID = "id_tasks"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "$COLUMN_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_DESCRIPTION VARCHAR(100)," +
                "$COLUMN_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ");"

        try {
            db?.execSQL(sql)
        Log.i("Create Table $TABLE_NAME", "success")
        }catch (e: SQLException){
            e.printStackTrace()
            Log.e("error creating table", e.message.toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
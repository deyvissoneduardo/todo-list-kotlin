package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.TaskModel

class TaskAdapter(
    val onItemClickDelete: (Int) -> Unit,
):  RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var listTask: List<TaskModel> = emptyList()

    inner class TaskViewHolder(itemBinding: ItemTaskBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTaskBinding

        init {
            binding = itemBinding
        }

        fun bind(taskmodel: TaskModel){
            binding.textDescricao.text = taskmodel.description
            binding.textData.text = taskmodel.date
            binding.btnExcluir.setOnClickListener{
                onItemClickDelete(taskmodel.isTask)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemTarefaBinding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = listTask[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }

    fun loadTask(task: List<TaskModel>){
        this.listTask = task
        notifyDataSetChanged()
    }
}
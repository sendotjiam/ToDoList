package com.sendo.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sendo.todo.database.Todo
import com.sendo.todo.util.DateTimeUtil
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_todo_item.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private val todoList = arrayListOf<Todo>()
    private var listener : ((Todo) -> Unit)? = null
    private var deleteListener : ((Todo) -> Unit)? = null

    inner class TodoViewHolder(val v : View) : RecyclerView.ViewHolder(v) {
        fun bind(todo : Todo) {
            v.apply {
                tv_todo_title.text = todo.title
                tv_todo_date.text = DateTimeUtil.convertDateTime(todo.dateTime)
                tv_todo_description.text = todo.description
            }
            v.rootView.setOnClickListener {
                listener?.invoke(todo)
            }
            v.ic_delete.setOnClickListener {
                deleteListener?.invoke(todo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_todo_item, parent, false));
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int = todoList.size

    fun updateData(newList : List<Todo>){
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    // Anonymous Function
    fun setOnClickListener(listener: (Todo) -> Unit) {
        this.listener = listener
    }

    fun setOnDeleteListener(deleteListener: (Todo) -> Unit) {
        this.deleteListener = deleteListener
    }
}
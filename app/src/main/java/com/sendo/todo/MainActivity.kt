package com.sendo.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.sendo.todo.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val todoAdapter : TodoAdapter by lazy { TodoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
        setAdapter()
    }
    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun setListener() {
        fab_add.setOnClickListener {
            navigate(0)
        }
    }

    private fun setAdapter() {
        todoAdapter.setOnClickListener {
            navigate(it.id)
        }
        todoAdapter.setOnDeleteListener {
            AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") {
                        dialog, _ ->
                    AppDatabase.getInstance(this).todoDao().delete(it)
                    Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show()
                    loadData()
                    dialog.dismiss()
                }
                .setNegativeButton("No") {
                        dialog, _ -> dialog.dismiss()
                }
                .show()
        }
        rv_todo.layoutManager = LinearLayoutManager(this)
        rv_todo.adapter = todoAdapter
    }

    private fun loadData() {
        val todoList = AppDatabase.getInstance(this).todoDao().get()
        todoAdapter.updateData(todoList)
    }
    private fun navigate(id: Long) {
        val intent = Intent(this, InsertUpdateActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
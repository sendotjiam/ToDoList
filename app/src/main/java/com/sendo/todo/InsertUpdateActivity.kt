package com.sendo.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sendo.todo.database.AppDatabase
import com.sendo.todo.database.Todo
import com.sendo.todo.util.DateTimeUtil
import kotlinx.android.synthetic.main.activity_insert_update.*

class InsertUpdateActivity : AppCompatActivity() {

    private var id : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_update)

        id = intent.getLongExtra("id", 0)
        if (id == 0L) {
            tv_insert_update_title.text = "Insert TODO"
        } else {
            tv_insert_update_title.text = "Update TODO"

            val todo = AppDatabase.getInstance(this).todoDao().getById(id)
            et_insert_update_title.setText(todo.title)
            et_insert_update_description.setText(todo.description)
        }

        setListener()
    }

    private fun setListener() {
        fab_save.setOnClickListener {
            val message = validate()
            if (message == "") {
                // INSERT / UPDATE
                val title = et_insert_update_title.text.toString()
                val description = et_insert_update_description.text.toString()
                if (id == 0L) {
                    insert(title, description)
                } else {
                    update(id, title, description)
                }
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validate() : String {
        val title = et_insert_update_title.text.toString().trim()
        val description = et_insert_update_description.text.toString().trim()
        return when {
            title.isEmpty() -> "Please input title"
            description.isEmpty() -> "Please input description"
            else -> ""
        }
    }

    private fun insert(title : String, description : String) {
        val todo = Todo(0, title, description, DateTimeUtil.getCurrentDate())
        val id = AppDatabase.getInstance(this).todoDao().insert(todo)
        if (id > 0) {
            Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Insert Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun update(id : Long, title : String, description : String) {
        val todo = Todo(id, title, description, DateTimeUtil.getCurrentDate())
        val affectedRow = AppDatabase.getInstance(this).todoDao().update(todo)
        if (affectedRow > 0) {
            Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Update Error", Toast.LENGTH_SHORT).show()
        }
    }
}
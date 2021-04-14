package com.sendo.todo.database

import androidx.room.*

@Dao
interface TodoDAO {
    @Insert
    fun insert(todo: Todo) : Long // ID INSERTED

    @Update // auto update without specify the id
    fun update(todo: Todo) : Int

    @Delete
    fun delete(todo: Todo) : Int

    @Query("SELECT * FROM Todo ORDER BY dateTime DESC")
    fun get() : List<Todo>

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun getById(id : Long) : Todo
}

// Query
// 1. Get all data
// 2. Get data by id
// 3. Insert
// 4. Update
// 5. Delete
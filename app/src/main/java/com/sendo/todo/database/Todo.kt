package com.sendo.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey


// Entity and Model
@Entity
data class Todo (
    @PrimaryKey(autoGenerate = true)
    var id : Long,

    var title : String,

    var description : String,

    var dateTime : String
)
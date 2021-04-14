package com.sendo.todo.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {
    fun getCurrentDate() : String {
        // format: yyyy-MM-dd HH:mm:ss
        val date = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(date)
    }
    fun convertDateTime(raw : String) : String{
        // raw      : yyyy-MM-dd HH:mm:ss
        // MM       : 01 | 02 | 03 | 04
        // MMM      : Jan Feb Apr
        // MMMM     : January February
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())

        val date = input.parse(raw) ?: Date()
        return output.format(date)
    }
}
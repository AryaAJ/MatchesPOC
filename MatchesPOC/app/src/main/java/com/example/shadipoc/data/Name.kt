package com.example.shadipoc.data

import androidx.room.ColumnInfo

data class Name(
    @ColumnInfo(name = "first")
    val first: String,
    val last: String,
    val title: String
)
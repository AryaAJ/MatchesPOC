package com.example.shadipoc.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(

    @ColumnInfo(name = "cell")
    var cell: String,

    @ColumnInfo(name = "dob")
    var dob: String,

    @ColumnInfo(name = "age")
    var age: Int,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "fname")
    var fname: String,

    @ColumnInfo(name = "lname")
    var lname: String,

    @ColumnInfo(name = "nat")
    var nat: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "medpicture")
    var medpicture: String,

    @ColumnInfo(name = "largepicture")
    var largepicture: String,

    @ColumnInfo(name = "accepted")
    var accepted: Int = 0, //not acted

    @PrimaryKey(autoGenerate = true)
    val userid: Int = 0

)
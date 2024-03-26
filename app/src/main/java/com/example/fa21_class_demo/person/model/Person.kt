package com.example.fa21_class_demo.person.model

data class Person(
    val id:Int? = 0,
    val name:String?="",
    val email:String?="",
    val contactNumber:String?="",
    val dateOfBirth: String?="",
    val gender: String?="",
    val address:String?="",
    val cnic:String?="",
    val religion:String?=""
)

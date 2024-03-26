package com.example.fa21_class_demo


fun main() {

    val name: String? = "ali"

    val result:Boolean = name?.contains("ee")?:false

    println("result  = $result")




    val students: MutableList<Student> = arrayListOf(
        Student(name = "Kashif", 123),
        Student(name = "Shahzaib", 566),
        Student(name = "areeba", 5678),
        Student(name = "Barira", 4567)
    )

    println(
        "Filtered Data = ${
            students.filter { student ->
                (student.rollNo ?: 0) > 123 && student.name?.contains(
                    "ee"
                ) ?: false
            }
        }"
    )

}
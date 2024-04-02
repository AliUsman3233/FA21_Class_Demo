package com.example.fa21_class_demo

fun main() {

    val pattern = Regex("^a")
    val input:String  = "abc"
    println(pattern.containsMatchIn(input))
    println(pattern.containsMatchIn("bac"))

    val email = "a.li@gmail.com"
    val gmailRegex: Regex = "^[a-zA-Z0-9]+[\\.\\w]*[a-zA-Z0-9]+@gmail\\.com$".toRegex()
    println(gmailRegex.matches(email))


}
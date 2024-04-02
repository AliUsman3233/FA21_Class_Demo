package com.example.fa21_class_demo.corotines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

    val message = "I Love my Laptop"


    runBlocking {
        launch {
            println("Received Data - > ${getDataFromAPI()}")
        }
    }
}

suspend fun getDataFromAPI(): String {
    println("Data fetching started")
    delay(5000)
    println("Data fetched successfully")
    return "Dummy data"
}
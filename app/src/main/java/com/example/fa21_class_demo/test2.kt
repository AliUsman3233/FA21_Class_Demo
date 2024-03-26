package com.example.fa21_class_demo

fun main() {

    val testName: String = "ali"
    val testName2: String = ""

    if(validateInput(testName)) {
        println("input -> ($testName) is valid")
    } else {
        println("input -> ($testName) is not valid")
    }

    if(validateInput(name = testName2)) {
        println("input -> ($testName2) is valid")
    } else {
        println("input -> ($testName2) is not valid")
    }

    println("Sum  = ${sum(b = 20, a = 30)}")


    println("factorial -> ${factorial(6)}")

    println("series sum = ${findSum(50,100)}")
}

fun validateInput(name: String): Boolean {
    return if (name.length > 0) true else false
}

fun sum(a:Int = 0, b:Int=0): Int {
    return a+b
}

fun factorial(number: Int): Int {
    if(number <= 0) {
        return 1
    } else {
        return number * factorial(number - 1)
    }
}


fun findSum(startNumber: Int, endNumber:Int): Int {
    if (startNumber == endNumber) {
        return startNumber
    } else {
        return startNumber + findSum(startNumber + 1, endNumber)
    }
}
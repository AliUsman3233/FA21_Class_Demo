package com.example.fa21_class_demo


fun main() {

    val list: List<String> = arrayListOf("ali", "Bilal", "Tahir")
    for (i in 0..list.size-1){
        println("name $(i+1) = ${list.get(i)}")
    }

    val changeAbleList: MutableList<String> = arrayListOf()
    changeAbleList.add("bchjdsb")
}
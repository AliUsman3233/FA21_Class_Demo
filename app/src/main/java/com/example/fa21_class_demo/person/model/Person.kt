package com.example.fa21_class_demo.person.model

data class Person(
    var id: Int? = 0,
    var name: Name? = Name("", ""),
    var email: String? = "",
    var contactNumber: String? = "",
    var dateOfBirth: DateOfBirth? = DateOfBirth("", "", ""),
    var gender: String? = "",
    var address: String? = "",
    var cnic: String? = "",
    var religion: String? = ""
)

data class Name(
    var fname: String? = "",
    var lName: String? = ""
) {
    fun checkIsNotEmpty(): Boolean {
        return fname?.isNotEmpty() ?: false && lName?.isNotEmpty() ?: false
    }
}


data class DateOfBirth(
    var day: String? = "",
    var month: String? = "",
    var year: String? = ""
) {
    fun checkIsNotEmpty(): Boolean {
        return day?.isNotEmpty() ?: false && month?.isNotEmpty() ?: false && year?.isNotEmpty() ?: false
    }
}
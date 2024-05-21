package com.example.fa21_class_demo.person.model

data class Person(
    var id: Int? = 0,
    var name: Name? = Name("Sheryar", "Ali"),
    var email: String? = "sa@gmail.com",
    var contactNumber: String? = "34567890",
    var dateOfBirth: DateOfBirth? = DateOfBirth("23", "1", "1999"),
    var gender: String? = "Male",
    var address: String? = "Arifwala",
    var cnic: String? = "2345678",
    var religion: String? = "Islam"
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
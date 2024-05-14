package com.example.fa21_class_demo.authentication.model

data class LoginModel(
    var email: String? = "",
    var password: String? = ""
) {
    fun validate(): Boolean {
        return email?.isNotEmpty()?:false && (password?.length?:0) > 5
    }
}

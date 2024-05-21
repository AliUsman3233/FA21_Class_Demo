package com.example.fa21_class_demo.authentication.model

import com.example.fa21_class_demo.person.model.ErrorModel

data class AuthModel(
    var email: String? = "",
    var password: String? = ""
) {
    fun validate(): ErrorModel {
        if(email?.isNotEmpty() ?: false && email?.contains("@") ?: false && (password?.length
            ?: 0) > 5) {
            return ErrorModel(status = true, message = "All is good")
        } else{
            if(email?.isEmpty() ?: false && !(email?.contains("@")?: false) ) {
                return ErrorModel(status = false, message = "Email is not valid")

            } else if(!((password?.length
                    ?: 0) > 5)) {
                return ErrorModel(status = false, message = "Password is not valid")
            } else {
                return ErrorModel(status = false, message = "Unknown state")
            }
        }
    }
}
package com.example.fa21_class_demo.authentication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.authentication.model.LoginModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    val loginModel: LoginModel = LoginModel("ali@gmail.com", "123456")

    private val _loginResponseMLD: MutableLiveData<Response<String?>> = MutableLiveData()
    val loginResponseLD: LiveData<Response<String?>> = _loginResponseMLD


    private val _signUpResponseMLD: MutableLiveData<Response<String?>> = MutableLiveData()
    val signUpResponseLD: LiveData<Response<String?>> = _signUpResponseMLD

    fun signUp() {
        _signUpResponseMLD.value = Response.loading(s=Response.Status.Loading)
        Log.e("LoginViewModel", "login: --> ${loginModel}")
        if (loginModel.validate()) {
            auth.createUserWithEmailAndPassword(loginModel.email ?: "", loginModel.password ?: "")
                .addOnSuccessListener {
                    Log.e("LoginViewModel", "signUp: SignUp successfull")
                    _signUpResponseMLD.value = Response.success(s=Response.Status.Success, data = "${loginModel.email} is registered", m = "jsabcudhsio")
//                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification().addOnSuccessListener {  }
                }.addOnFailureListener {
                    Log.e("TAG", "signUp: error ->  ${it.message}", )
                    Log.e("LoginViewModel", "signUp: SignUp Failed")
                    _signUpResponseMLD.value = Response.failure(s=Response.Status.Failure, data = "${loginModel.email} is not registered", m = "Exception  -> ${it.message}")

                }
        } else {
            Log.e("LoginViewModel", "signUp: Not a valid model")
        }
    }

    fun login() {
        _loginResponseMLD.value = Response.loading(s=Response.Status.Loading)
        if (loginModel.validate()) {
            auth.signInWithEmailAndPassword(loginModel.email ?: "", loginModel.password ?: "")
                .addOnSuccessListener {
                    _loginResponseMLD.value = Response.success(s=Response.Status.Success, data = "${loginModel.email} is logged-in", m = "jsabcudhsio")
                }.addOnFailureListener {
                    _loginResponseMLD.value = Response.failure(s=Response.Status.Failure, data = "${loginModel.email} is not logged-in", m = "Exception  -> ${it.message}")

                }
        } else {
            Log.e("LoginViewModel", "signUp: Not a valid model")
        }
    }


}
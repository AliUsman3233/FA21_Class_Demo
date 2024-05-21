package com.example.fa21_class_demo.authentication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.authentication.model.AuthModel
import com.example.fa21_class_demo.authentication.model.LoginModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModelNew(application: Application) : AndroidViewModel(application) {
    val authModel: AuthModel = AuthModel()
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _loginResponseMLD: MutableLiveData<Response<String?>> = MutableLiveData()
    val loginResponseLD: LiveData<Response<String?>>  = _loginResponseMLD
    fun login() {
        _loginResponseMLD.value = Response.loading(m = "Loading.....")
        val validationResult = authModel.validate()
        if(validationResult.status) {
            firebaseAuth.signInWithEmailAndPassword(authModel.email?:"", authModel.password?:"").addOnSuccessListener {
                _loginResponseMLD.value = Response.success(data = "", m = "Login Successfully")
            }.addOnFailureListener {
                _loginResponseMLD.value = Response.failure(data = "", m = "Login Failed")

            }
        } else {
            _loginResponseMLD.value = Response.error(s = Response.Status.Error,data = null, m = validationResult.message)
        }
    }
}
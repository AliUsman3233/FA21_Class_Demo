package com.example.fa21_class_demo.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.authentication.viewmodel.AuthViewModelNew
import com.example.fa21_class_demo.databinding.ActivityLoginNewBinding

class LoginActivityNew : AppCompatActivity() {

    private lateinit var dataBinding: ActivityLoginNewBinding
    private lateinit var authViewModel: AuthViewModelNew
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_new)
        authViewModel = ViewModelProviders.of(this).get(AuthViewModelNew::class.java)
        setUpBinding()
        setUpObservers()

    }

    fun setUpBinding() {
        with(dataBinding) {
            viewmodel = authViewModel
            lifecycleOwner = this@LoginActivityNew
        }
    }

    fun setUpObservers() {
        authViewModel.loginResponseLD.observe(this@LoginActivityNew) { loginResult ->
            when(loginResult.status) {
                Response.Status.Loading -> {
                    Toast.makeText(this, loginResult.message, Toast.LENGTH_SHORT).show()
                }
                Response.Status.Success -> {
                    Toast.makeText(this, loginResult.message, Toast.LENGTH_SHORT).show()
                }
                Response.Status.Error -> {
                    Toast.makeText(this, loginResult.message, Toast.LENGTH_SHORT).show()

                }
                Response.Status.Failure -> {
                    Toast.makeText(this, loginResult.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
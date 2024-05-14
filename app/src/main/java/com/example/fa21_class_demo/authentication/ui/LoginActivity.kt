package com.example.fa21_class_demo.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.databinding.ActivityLoginBinding
import com.example.fa21_class_demo.authentication.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityLoginBinding
    private var authViewModel: AuthViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        setUpBinding()
        setupObservers()
    }
    fun setUpBinding() {
        with(dataBinding) {
            viewmodel = this@LoginActivity.authViewModel
            lifecycleOwner = this@LoginActivity
        }
        dataBinding.signupTransitionButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }
    }

    fun setupObservers() {
        authViewModel?.loginResponseLD?.observe(this@LoginActivity) {
            when(it.status) {
                Response.Status.Loading -> {
                    Toast.makeText(this@LoginActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Success -> {
                    Toast.makeText(this@LoginActivity,it.data?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Error -> {
                    Toast.makeText(this@LoginActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Failure -> {
                    Toast.makeText(this@LoginActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
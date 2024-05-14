package com.example.fa21_class_demo.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.databinding.ActivitySignupBinding
import com.example.fa21_class_demo.authentication.viewmodel.AuthViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivitySignupBinding
    private var loginViewModel: AuthViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        loginViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        setUpBinding()
        setupObservers()
    }

    fun setUpBinding() {
        with(dataBinding) {
            viewmodel = this@SignUpActivity.loginViewModel
            lifecycleOwner = this@SignUpActivity
        }
        dataBinding.loginTransitionButton.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }
    }

    fun setupObservers() {
        loginViewModel?.signUpResponseLD?.observe(this@SignUpActivity) {
            when(it.status) {
                Response.Status.Loading -> {
                    Toast.makeText(this@SignUpActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Success -> {
                    Toast.makeText(this@SignUpActivity,it.data?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Error -> {
                    Toast.makeText(this@SignUpActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Failure -> {
                    Toast.makeText(this@SignUpActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
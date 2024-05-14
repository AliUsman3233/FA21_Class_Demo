package com.example.fa21_class_demo.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.databinding.ActivitySignupBinding
import com.example.fa21_class_demo.authentication.viewmodel.AuthViewModel
import com.example.fa21_class_demo.authentication.viewmodel.PersonDetailsViewModel
import com.example.fa21_class_demo.databinding.RegisterPersonActivityBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var dataBinding: RegisterPersonActivityBinding
    private var personDetailsViewModel: PersonDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.register_person_activity)
        personDetailsViewModel = ViewModelProviders.of(this).get(PersonDetailsViewModel::class.java)

        setUpBinding()
        setupObservers()
    }

    fun setUpBinding() {
        with(dataBinding) {
            viewmodel = this@UserDetailActivity.personDetailsViewModel
            lifecycleOwner = this@UserDetailActivity
        }
//        dataBinding.loginTransitionButton.setOnClickListener {
//            startActivity(Intent(this@UserDetailActivity, LoginActivity::class.java))
//            finish()
//        }
    }

    private fun setupObservers() {
        personDetailsViewModel?.personUploadResponseMLD?.observe(this@UserDetailActivity) {
            when(it.status) {
                Response.Status.Loading -> {
                    Toast.makeText(this@UserDetailActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Success -> {
                    Toast.makeText(this@UserDetailActivity,it.data?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Error -> {
                    Toast.makeText(this@UserDetailActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Failure -> {
                    Toast.makeText(this@UserDetailActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
            }
        }

        personDetailsViewModel?.personDetailResponseLD?.observe(this@UserDetailActivity) {
            when(it.status) {
                Response.Status.Loading -> {
                    Toast.makeText(this@UserDetailActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Success -> {
                    Log.e("TAG", "setupObservers: received data  = ${it.data}", )
                }
                Response.Status.Error -> {
                    Toast.makeText(this@UserDetailActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Failure -> {
                    Toast.makeText(this@UserDetailActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
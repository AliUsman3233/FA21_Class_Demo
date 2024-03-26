package com.example.fa21_class_demo.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.MainActivity
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityLoginBinding
    private var extras: Bundle? = null
    private var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        extras = intent?.extras
        if(extras != null) {
            userName = extras?.getString("name")?:""
        }
        dataBinding.textContent.text = userName
        dataBinding.backButton.setOnClickListener {
            val gameIntent = Intent(this@LoginActivity, MainActivity::class.java)
            finish()
            startActivity(gameIntent)
        }

    }
}
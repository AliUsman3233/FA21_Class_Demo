package com.example.fa21_class_demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.databinding.ActivityLoginBinding
import com.example.fa21_class_demo.databinding.ActivityMainBinding

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
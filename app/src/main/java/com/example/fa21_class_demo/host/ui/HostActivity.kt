package com.example.fa21_class_demo.host.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.ActivityMainBinding
import com.example.fa21_class_demo.authentication.ui.SignUpActivity
import com.example.fa21_class_demo.databinding.ActivityHostBinding
import kotlinx.coroutines.*

class HostActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_host)


    }
}
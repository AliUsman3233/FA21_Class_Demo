package com.example.fa21_class_demo.data_passing_activities.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.SecondActivityBinding
import kotlinx.coroutines.*

class SecondActivity : AppCompatActivity() {

    private lateinit var dataBinding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.second_activity)
        val name: String? = intent.getStringExtra(FirstActivity.nameKey)

        dataBinding.name = name;

        dataBinding.appBar.backbtn.setOnClickListener {
            finish()
        }
    }
}
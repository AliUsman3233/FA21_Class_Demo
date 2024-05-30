package com.example.fa21_class_demo.host.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.ActivityMainBinding
import com.example.fa21_class_demo.authentication.ui.SignUpActivity
import com.example.fa21_class_demo.databinding.ActivityHostBinding
import kotlinx.android.synthetic.main.activity_host.view.bottom_nav_view
import kotlinx.coroutines.*

class HostActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityHostBinding

    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_host)
        navController = findNavController(R.id.nav_host_fragment_container)
        dataBinding.bottomNavView.setupWithNavController(navController)

        dataBinding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    Toast.makeText(this@HostActivity, "Home is selected", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this@HostActivity, "Unknown is selected", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}
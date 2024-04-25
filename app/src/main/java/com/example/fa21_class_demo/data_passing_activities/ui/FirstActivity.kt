package com.example.fa21_class_demo.data_passing_activities.ui

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
import com.example.fa21_class_demo.databinding.FirstActivityBinding
import com.example.fa21_class_demo.login.LoginActivity
import kotlinx.coroutines.*

class FirstActivity : AppCompatActivity() {

    companion object {
        val nameKey: String = "name"
    }
    private lateinit var dataBinding: FirstActivityBinding
    var closingFlag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.first_activity)

        dataBinding.goBtn.setOnClickListener {
            val name:String = dataBinding.nameET.text.toString()
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            intent.putExtra(FirstActivity.nameKey, name)
            startActivity(intent)
        }

        dataBinding.appBar.backbtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    dataBinding.appBar.backbtn.isEnabled = false
                    handelBackPress()
                }

                val job = launch {
                    delay(2000)
                }
                job.join()
                withContext(Dispatchers.Main) {
                    dataBinding.appBar.backbtn.isEnabled = true
                }
            }

        }
    }

    fun handelBackPress() {
        if(closingFlag) {
            finish()
        } else {
            Toast.makeText(this, "Press again to exit",Toast.LENGTH_LONG).show()
            closingFlag = !closingFlag
        }
    }
}
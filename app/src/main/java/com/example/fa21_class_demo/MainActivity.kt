package com.example.fa21_class_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding
    var player: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        dataBinding.first.setOnClickListener {
            if (player) {
                dataBinding.firstchild.background = getDrawable(R.drawable.ic_baseline_check_24)
            } else {
                dataBinding.firstchild.background = getDrawable(R.drawable.ic_baseline_clear_24)
            }
            switchPlayer()
        }

        dataBinding.second.setOnClickListener {
            if (player) {
                dataBinding.secondChild.background = getDrawable(R.drawable.ic_baseline_check_24)
            } else {
                dataBinding.secondChild.background = getDrawable(R.drawable.ic_baseline_clear_24)
            }
            switchPlayer()
        }

    }

    fun switchPlayer() {
        player = !player
    }
}
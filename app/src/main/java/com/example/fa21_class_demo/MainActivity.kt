package com.example.fa21_class_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private var player: Boolean = false

    private val entertainedComponents: MutableList<Int> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        dataBinding.center.setOnClickListener {
            if(!entertainedComponents.contains(R.id.center)) {
                if (player) {
                    dataBinding.centerchild
                        .background =
                        AppCompatResources.getDrawable(this, R.drawable.ic_baseline_check_24)
                } else {
                    dataBinding.centerchild.background =
                        AppCompatResources.getDrawable(this, R.drawable.ic_baseline_clear_24)
                }
                switchPlayer()
                entertainedComponents.add(R.id.center)
            }
        }

        dataBinding.middleLeft.setOnClickListener {
            if(!entertainedComponents.contains(R.id.middle_left)) {

                if (player) {
                    dataBinding.middleLeftChild.background =
                        AppCompatResources.getDrawable(this, R.drawable.ic_baseline_check_24)
                } else {
                    dataBinding.middleLeftChild.background =
                        AppCompatResources.getDrawable(this, R.drawable.ic_baseline_clear_24)
                }
                switchPlayer()
                entertainedComponents.add(R.id.middle_left)
            }

        }

        dataBinding.middleRight.setOnClickListener {
            if (player) {
                dataBinding.middleRightChild.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_check_24)
            } else {
                dataBinding.middleRightChild.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_clear_24)
            }
            switchPlayer()
            entertainedComponents.add(R.id.middle_right)

        }
        dataBinding.topLeft.setOnClickListener {
            if (player) {
                dataBinding.topLeftChild.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_check_24)
            } else {
                dataBinding.topLeftChild.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_clear_24)
            }
            switchPlayer()
            entertainedComponents.add(R.id.top_left)

        }

    }

    private fun switchPlayer() {
        player = !player
    }
}
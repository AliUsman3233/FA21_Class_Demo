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
import com.example.fa21_class_demo.databinding.ActivityMainBinding
import com.example.fa21_class_demo.authentication.ui.SignUpActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    data class PlayerTurnModel(
        val player: Boolean,
        val id: Int
    )

    private lateinit var dataBinding: ActivityMainBinding
    private var player: Boolean = true

    private val entertainedComponents: MutableList<PlayerTurnModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        GlobalScope.launch(Dispatchers.IO) { // Input output thread
            while (true) {
                delay(1000)
                withContext(Dispatchers.Main) {
                    dataBinding.topLeftChild
                        .background =
                        AppCompatResources.getDrawable(
                            this@MainActivity,
                            R.drawable.ic_baseline_check_24
                        )

                }
                delay(1000)
                withContext(Dispatchers.Main) {
                    dataBinding.topLeftChild
                        .background =
                        AppCompatResources.getDrawable(
                            this@MainActivity,
                            R.drawable.ic_baseline_clear_24
                        )
                }
            }


        }

        dataBinding.topLeft.setOnClickListener {
            handlePlayerInput(dataBinding.topLeftChild)
        }
        dataBinding.topMiddle.setOnClickListener {
            handlePlayerInput(dataBinding.topMiddleChild)
        }
        dataBinding.topRight.setOnClickListener {
            handlePlayerInput(dataBinding.topRightChild)
        }
        dataBinding.middleLeft.setOnClickListener {
            handlePlayerInput(dataBinding.middleLeftChild)
        }
        dataBinding.center.setOnClickListener {
            handlePlayerInput(dataBinding.centerchild)
        }
        dataBinding.middleRight.setOnClickListener {
            handlePlayerInput(dataBinding.middleRightChild)
        }
        dataBinding.bottomLeft.setOnClickListener {
            handlePlayerInput(dataBinding.bottomLeftChild)
        }
        dataBinding.bottomCenter.setOnClickListener {
            handlePlayerInput(dataBinding.bottomCenterChild)
        }
        dataBinding.bottomRight.setOnClickListener {
            handlePlayerInput(dataBinding.bottomRightChild)
        }
    }


    private fun handlePlayerInput(view: ConstraintLayout) {
        if (entertainedComponents.filter { it.id == view.id }.size == 0) {
            if (player) {
                view
                    .background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_check_24)
            } else {
                view.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_baseline_clear_24)
            }
            entertainedComponents.add(PlayerTurnModel(player = player, id = view.id))
            switchPlayer()
        }

        val winState = checkWinState()
        var winner = ""
        if (winState == true) {
            winner = "One"
        } else {
            winner = "Two"
        }
        if (winState == null) {
            Toast.makeText(this, "No win yet", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Player $winner Wins", Toast.LENGTH_SHORT).show()
            val loginIntent =
                Intent(this@MainActivity, SignUpActivity::class.java).putExtra("name", "Ali Usman")
            startActivity(loginIntent)
            finish()
        }
    }


    private fun switchPlayer() {
        player = !player
    }


    private fun checkWinState(): Boolean? {
        Log.e("TAG", "checkWinState: entertainedComponents ${entertainedComponents}")
        if (entertainedComponents.size > 4) {
            try {
                if (entertainedComponents.filter { it.id == R.id.top_left_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.top_middle_Child }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.top_left_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.top_right_Child }
                        .get(0).player) {
                    dataBinding.topLine.visibility = View.VISIBLE
                    return entertainedComponents.filter { it.id == R.id.top_left_Child }
                        .get(0).player
                }
            } catch (err: Exception) {

            }

            try {
                if (entertainedComponents.filter { it.id == R.id.middle_left_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.centerchild }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.centerchild }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.middle_right_Child }
                        .get(0).player) {
                    dataBinding.middleLine.visibility = View.VISIBLE

                    return entertainedComponents.filter { it.id == R.id.centerchild }.get(0).player
                }
            } catch (error: Exception) {

            }


            try {
                if (entertainedComponents.filter { it.id == R.id.bottom_left_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.bottom_centerChild }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.bottom_centerChild }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.bottom_right_Child }
                        .get(0).player) {
                    dataBinding.bottomLine.visibility = View.VISIBLE

                    return entertainedComponents.filter { it.id == R.id.bottom_left_Child }
                        .get(0).player
                }
            } catch (error: Exception) {

            }
            // Columns

            try {
                if (entertainedComponents.filter { it.id == R.id.top_left_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.middle_left_Child }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.middle_left_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.bottom_left_Child }
                        .get(0).player) {
                    return entertainedComponents.filter { it.id == R.id.middle_left_Child }
                        .get(0).player
                }
            } catch (err: Exception) {

            }

            try {
                if (entertainedComponents.filter { it.id == R.id.top_middle_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.centerchild }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.centerchild }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.bottom_centerChild }
                        .get(0).player) {
                    return entertainedComponents.filter { it.id == R.id.centerchild }.get(0).player
                }
            } catch (error: Exception) {

            }


            try {
                if (entertainedComponents.filter { it.id == R.id.top_right_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.middle_right_Child }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.middle_right_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.bottom_right_Child }
                        .get(0).player) {
                    return entertainedComponents.filter { it.id == R.id.middle_right_Child }
                        .get(0).player
                }
            } catch (error: Exception) {

            }

            // Diagnal


            try {
                if (entertainedComponents.filter { it.id == R.id.top_right_Child }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.centerchild }
                        .get(0).player && entertainedComponents.filter { it.id == R.id.centerchild }
                        .get(0).player == entertainedComponents.filter { it.id == R.id.bottom_left_Child }
                        .get(0).player) {
                    dataBinding.dRightToLeft.visibility = View.VISIBLE
                    return entertainedComponents.filter { it.id == R.id.centerchild }.get(0).player
                }
            } catch (error: Exception) {

            }

        }

        return null
    }
}
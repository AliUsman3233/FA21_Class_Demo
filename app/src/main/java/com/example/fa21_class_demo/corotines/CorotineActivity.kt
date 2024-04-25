package com.example.fa21_class_demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.databinding.ActivityCorotineBinding
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityCorotineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_corotine)



        GlobalScope.launch(Dispatchers.IO) {
            launch(Dispatchers.IO) {
                launch(Dispatchers.IO) {
                    Log.e("Corotines -- ", "onCreate: wait started")
                    val job = launch(Dispatchers.Main) {
                        var counter = 0
                        while (counter < 10) {
                            dataBinding.textContent.text = "Loading"
                            for (i in 1..10) {
                                dataBinding.textContent.text =
                                    dataBinding.textContent.text.toString() + "."
                                delay(50)
                            }
                            counter++
                        }
                    }
//                    job.cancel()
                    job.join()
                    Log.e("Corotines -- ", "onCreate: sum(2, 6) = ${sum(2, 6)}");
//                    delay(2000)
                    Log.e("Corotines -- ", "onCreate: wait finished")
                    withContext(Dispatchers.Main) {
                        dataBinding.textContent.text = "${sum(2, 6)}"
                    }
                }
            }

            launch {
                Log.e("Corotines -- ", "onCreate: multiply(5, null) = ${multiply(5, null)}");
            }
        }

    }


    suspend fun sum(a: Int, b: Int): Int {
        return a + b
    }

    suspend fun multiply(a: Int? = 1, b: Int? = 1) =
        (a ?: 1) * (b ?: 1)
}
package com.example.fa21_class_demo.viewmodel_working.counter.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.ActivityCounterBinding
import com.example.fa21_class_demo.databinding.RegisterPersonActivityBinding
import com.example.fa21_class_demo.person.model.ErrorModel
import com.example.fa21_class_demo.person.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class CounterActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityCounterBinding

    private val counterMLD: MutableLiveData<Int> = MutableLiveData(0)
    val counterLD: LiveData<Int> = counterMLD
    private var startStopFlag = false
    private lateinit var counterThread: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        dataBinding.lifecycleOwner = this@CounterActivity
        dataBinding.startStopState = startStopFlag
        this@CounterActivity.counterLD.observe(this) { updatedCounterValue ->
            Log.e("CounterActivity", "onCreate: counterMLD -> $updatedCounterValue")
            dataBinding.count = updatedCounterValue
        }


        dataBinding.startStopBtn.setOnClickListener {
            if (startStopFlag) {
                counterThread.cancel()
            } else {
                counterThread = GlobalScope.launch(Dispatchers.IO) {
                    while (true) {
                        counterMLD.postValue((counterMLD.value ?: 0) + 1)
                        delay(1000)
                    }
                }
            }
            startStopFlag = !startStopFlag
            dataBinding.startStopState = startStopFlag
        }


    }

}
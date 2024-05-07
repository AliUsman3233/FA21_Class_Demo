package com.example.fa21_class_demo.viewmodel_working.counter.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.ActivityCounterBinding
import com.example.fa21_class_demo.viewmodel_working.counter.viewmodel.CounterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityCounterBinding
    private var counterViewModel: CounterViewModel? = null

//    private val counterMLD: MutableLiveData<Int> = MutableLiveData(0)
//    val counterLD: LiveData<Int> = counterMLD
//    private lateinit var counterThread: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_counter)

        with(dataBinding) {
            lifecycleOwner = this@CounterActivity
            viewModel = this@CounterActivity.counterViewModel
        }
        dataBinding.lifecycleOwner = this@CounterActivity

//        this@CounterActivity.counterLD.observe(this) { updatedCounterValue ->
//            Log.e("CounterActivity", "onCreate: counterMLD -> $updatedCounterValue")
//            dataBinding.count = updatedCounterValue
//        }


//        dataBinding.startStopBtn.setOnClickListener {
//            if (startStopFlag) {
//                counterThread.cancel()
//            } else {
//                counterThread = GlobalScope.launch(Dispatchers.IO) {
//                    while (true) {
//                        counterMLD.postValue((counterMLD.value ?: 0) + 1)
//                        delay(1000)
//                    }
//                }
//            }
//            startStopFlag = !startStopFlag
//            dataBinding.startStopState = startStopFlag
//        }


    }


    private fun setUpObservers() {
        counterViewModel?.counterLD?.observe(this@CounterActivity) { count ->
            Log.e("CounterActivity", "setUpObservers: count -> ${count}", )
        }
    }

}
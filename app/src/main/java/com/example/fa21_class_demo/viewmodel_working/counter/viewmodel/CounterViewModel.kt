package com.example.fa21_class_demo.viewmodel_working.counter.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.fa21_class_demo.viewmodel_working.counter.model.CounterUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var counterThread: Job

    private val counterMLD: MutableLiveData<CounterUiModel> =
        MutableLiveData(CounterUiModel(count = 0))
    val counterLD: LiveData<CounterUiModel> = counterMLD


    private val startStopFlagMLD: MutableLiveData<Boolean> = MutableLiveData(false)
    val startStopFlagLD: LiveData<Boolean> = startStopFlagMLD

    fun startStopCounterEvent() {

        Log.e("CounterViewModel", "startStopCounterEvent: this function is called")
        if (startStopFlagLD.value ?: false) {
            counterThread.cancel()
        } else {
            counterThread = viewModelScope.launch {
                while (true) {
//                    val oldValue = counterMLD.value?.count ?: 0
//                    val newValue = oldValue + 1
//                    val newCounterUiModel = CounterUiModel(count = newValue)
//                    counterMLD.postValue(newCounterUiModel)
                    counterMLD.postValue(CounterUiModel(count = (counterLD.value?.count ?: 0) + 1))

                    delay(1000)
                }
            }
        }
        startStopFlagMLD.value = !(startStopFlagLD.value ?: false)
    }


}
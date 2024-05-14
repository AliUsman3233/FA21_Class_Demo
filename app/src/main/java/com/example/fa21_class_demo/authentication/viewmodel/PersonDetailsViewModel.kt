package com.example.fa21_class_demo.authentication.viewmodel

import android.app.Application
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.authentication.model.LoginModel
import com.example.fa21_class_demo.person.model.Person
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PersonDetailsViewModel(application: Application) : AndroidViewModel(application) {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var person: MutableLiveData<Person> = MutableLiveData()

    private val _personUploadResponseMLD: MutableLiveData<Response<String?>> = MutableLiveData()
    val personUploadResponseMLD: LiveData<Response<String?>> = _personUploadResponseMLD

    private val _personDetailResponseMLD: MutableLiveData<Response<Person?>> = MutableLiveData()
    val personDetailResponseLD: LiveData<Response<Person?>> = _personDetailResponseMLD

    init {
        fetchPersonDetails()
    }

    fun fetchPersonDetails() {
        _personDetailResponseMLD.value =
            Response.loading(s = Response.Status.Loading, m = "Loading.......")

        db.collection("person").document("ali@gmail.com").get().addOnSuccessListener {
            _personDetailResponseMLD.value = Response.success(
                s = Response.Status.Success,
                data = it.toObject(Person::class.java),
                m = "Failed to fetch data"
            )
            person.value = it.toObject(Person::class.java)?: Person()

        }.addOnFailureListener {
            _personDetailResponseMLD.value = Response.failure(
                s = Response.Status.Failure,
                data = null,
                m = "Failed to fetch data"
            )
        }
    }

    fun uploadPersonDetails() {
//        Log.e("", "uploadPersonDetails: Here we get person -> $person ", )

        _personUploadResponseMLD.value =
            Response.loading(s = Response.Status.Loading, m = "Loading.......")

        try {
            db.collection("person").document(person.value?.email ?: "undefined").set(person)
                .addOnSuccessListener {
                    Log.e("TAG", "uploadPersonDetails: uploaded")

                    _personUploadResponseMLD.value = Response.success(
                        s = Response.Status.Success,
                        data = "Person data is uploaded",
                        m = ""
                    )
                }.addOnFailureListener {
                    Log.e("TAG", "uploadPersonDetails: error -> ${it.message}")
                    _personUploadResponseMLD.value = Response.failure(
                        s = Response.Status.Failure,
                        data = "Person data is not uploaded",
                        m = it.message
                    )
                }
        } catch (ex: Exception) {
            Log.e("TAG", "uploadPersonDetails: error --- $ex")
        }

    }

}
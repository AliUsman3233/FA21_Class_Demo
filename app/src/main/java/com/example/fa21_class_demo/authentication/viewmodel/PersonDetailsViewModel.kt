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
    var person: MutableLiveData<Person> = MutableLiveData(Person())

    private val _personUploadResponseMLD: MutableLiveData<Response<String?>> = MutableLiveData()
    val personUploadResponseMLD: LiveData<Response<String?>> = _personUploadResponseMLD

    private val _personDetailResponseMLD: MutableLiveData<Response<Person?>> = MutableLiveData()
    val personDetailResponseLD: LiveData<Response<Person?>> = _personDetailResponseMLD


    private val _personsResponseMLD: MutableLiveData<Response<List<Person?>?>> = MutableLiveData()
    val personsResponseLD: LiveData<Response<List<Person?>?>> = _personsResponseMLD

    init {
//        fetchPersonDetails()
        fetchAllPersons()
    }

    fun fetchPersonDetails() {
        _personDetailResponseMLD.value =
            Response.loading(s = Response.Status.Loading, m = "Loading.......")

        db.collection("person").document("ali1@gmail.com").get().addOnSuccessListener {
            _personDetailResponseMLD.value = Response.success(
                s = Response.Status.Success,
                data = it.toObject(Person::class.java),
                m = "Failed to fetch data"
            )
            person.value = it.toObject(Person::class.java) ?: Person()

        }.addOnFailureListener {
            _personDetailResponseMLD.value = Response.failure(
                s = Response.Status.Failure,
                data = null,
                m = "Failed to fetch data"
            )
        }
    }


    fun fetchAllPersons() {
        _personsResponseMLD.value =
            Response.loading(s = Response.Status.Loading, m = "Loading.......")

        db.collection("person").get().addOnSuccessListener {
            _personsResponseMLD.value = Response.success(
                s = Response.Status.Success,
                data = it.toObjects(Person::class.java),
                m = "Persons list fetched successfully"
            )
        }.addOnFailureListener {
            _personsResponseMLD.value = Response.failure(
                s = Response.Status.Failure,
                data = null,
                m = "Failed to fetch persons list"
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

    fun tapAction() {
        Log.e("PersonDetailsViewModel", "tapAction: called", )
    }

}
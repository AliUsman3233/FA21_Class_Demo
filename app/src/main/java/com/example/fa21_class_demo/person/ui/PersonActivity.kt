package com.example.fa21_class_demo.person.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.RegisterPersonActivityBinding
import com.example.fa21_class_demo.person.model.ErrorModel
import com.example.fa21_class_demo.person.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PersonActivity : AppCompatActivity() {

    private lateinit var dataBinding: RegisterPersonActivityBinding
    private var person:Person = Person()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.register_person_activity)
        dataBinding.person = person
        dataBinding.lifecycleOwner = this@PersonActivity

        GlobalScope.launch(Dispatchers.IO) {
            while(true) {
                Log.e("Thread", "onCreate: person ->  ${person}")
                delay(2000)
            }
        }
        var personModel: Person
//        dataBinding.saveBtn.setOnClickListener {
//            personModel = Person(
//                id = 123,
//                name = dataBinding.pName.text.toString(),
//                email = dataBinding.pEmail.text.toString(),
//                contactNumber = dataBinding.pContactNumber.text.toString(),
//                dateOfBirth = dataBinding.pDateOfBirth.text.toString(),
//                gender = dataBinding.pGender.text.toString(),
//                address = dataBinding.pAddress.text.toString(),
//                cnic = dataBinding.pCnic.text.toString(),
//                religion = dataBinding.pReligion.text.toString()
//            )
//            val validationStatus = validateData(personModel)
//            if(validationStatus.status) {
//                Log.e("TAG", "onCreate: Data is ready to save in database", )
//            } else {
//                Toast.makeText(this@PersonActivity, validationStatus.message, Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "onCreate: Error is -> ${validationStatus.message}", )
//            }
//            Log.e("TAG", "onCreate: PersonData = $personModel", )
//        }
    }
    fun validateData(person: Person): ErrorModel {
        with(person) {
            if (name?.checkIsNotEmpty()?:false
                    && id != 0 && email?.isNotEmpty()?:false
                    && contactNumber?.isNotEmpty()?:false
                    && dateOfBirth?.checkIsNotEmpty()?:false
                    && gender?.isNotEmpty()?:false
                    && address?.isNotEmpty()?:false
                    && cnic?.isNotEmpty()?:false
                    && religion?.isNotEmpty()?:false) {
                return ErrorModel(status = true, message = "All data is validated")
            }
            else {
                if(name?.checkIsNotEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your name first")
                }
                if(id == 0) {
                    return ErrorModel(status = false, message = "Enter your Id first")
                }
                if(email?.isEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your email first")
                }
                if(contactNumber?.isEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your Contact Number first")
                }
                if(address?.isEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your address first")
                }
                if(cnic?.isEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your CNIC first")
                }
                if(dateOfBirth?.checkIsNotEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your Date Of Birth first")
                }
                if(gender?.isEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your Gender first")
                }
                if(religion?.isEmpty()?:false) {
                    return ErrorModel(status = false, message = "Enter your religion first")
                }

            }
            return ErrorModel(status = false, message = "Invalid State Flow")
        }
    }

}
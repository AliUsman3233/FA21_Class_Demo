package com.example.fa21_class_demo.person.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.databinding.RegisterPersonActivityBinding
import com.example.fa21_class_demo.person.model.Person

class PersonActivity : AppCompatActivity() {

    private lateinit var dataBinding: RegisterPersonActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.register_person_activity)
        var personModel: Person
        dataBinding.personForm.saveBtn.setOnClickListener {
            personModel = Person(
                id = 123,
                name = dataBinding.personForm.pName.text.toString(),
                email = dataBinding.personForm.pEmail.text.toString(),
                contactNumber = dataBinding.personForm.pContactNumber.text.toString(),
                dateOfBirth = dataBinding.personForm.pDateOfBirth.text.toString(),
                gender = dataBinding.personForm.pGender.text.toString(),
                address = dataBinding.personForm.pAddress.text.toString(),
                cnic = dataBinding.personForm.pCnic.text.toString(),
                religion = dataBinding.personForm.pReligion.text.toString()
            )

            Log.e("TAG", "onCreate: PersonData = $personModel", )
        }
    }
}
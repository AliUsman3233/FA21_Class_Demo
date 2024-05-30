package com.example.fa21_class_demo.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.fa21_class_demo.R
import com.example.fa21_class_demo.Response
import com.example.fa21_class_demo.authentication.adopter.PersonRecyclerAdopter
import com.example.fa21_class_demo.databinding.ActivitySignupBinding
import com.example.fa21_class_demo.authentication.viewmodel.AuthViewModel
import com.example.fa21_class_demo.authentication.viewmodel.PersonDetailsViewModel
import com.example.fa21_class_demo.databinding.RegisterPersonActivityBinding
import com.example.fa21_class_demo.databinding.UsersActivityBinding
import com.example.fa21_class_demo.person.model.Person
import com.example.fa21_class_demo.person.ui.PersonActivity

class UsersActivity : AppCompatActivity() {

    private lateinit var dataBinding: UsersActivityBinding
    private var personDetailsViewModel: PersonDetailsViewModel? = null
    private lateinit var personRecyclerAdopter: PersonRecyclerAdopter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.users_activity)
        personDetailsViewModel = ViewModelProviders.of(this).get(PersonDetailsViewModel::class.java)
        personRecyclerAdopter = PersonRecyclerAdopter(viewModel = personDetailsViewModel)
        setUpBinding()
        setupObservers()

        personDetailsViewModel?.person?.value = Person()
    }

    fun setUpBinding() {
        with(dataBinding) {
            lifecycleOwner = this@UsersActivity
            personRV.adapter = this@UsersActivity.personRecyclerAdopter
            personRV.layoutManager = LinearLayoutManager(this@UsersActivity)

            addNewPersonBtn.setOnClickListener {
                startActivity(Intent(this@UsersActivity, UserDetailActivity::class.java))
            }
        }



    }

    private fun setupObservers() {
        personDetailsViewModel?.personsResponseLD?.observe(this@UsersActivity) {
            when(it.status) {
                Response.Status.Loading -> {
                    Toast.makeText(this@UsersActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Success -> {
                    Log.e("TAG", "setupObservers: list of persons -> ${it.data}", )

                    personRecyclerAdopter.submitList(it.data)

                    personRecyclerAdopter.notifyDataSetChanged()
                }
                Response.Status.Error -> {
                    Toast.makeText(this@UsersActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
                Response.Status.Failure -> {
                    Toast.makeText(this@UsersActivity,it.message?:"No message", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
package com.example.fa21_class_demo.authentication.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fa21_class_demo.authentication.viewmodel.PersonDetailsViewModel
import com.example.fa21_class_demo.databinding.PersonRowBinding
import com.example.fa21_class_demo.person.model.Person

class PersonRecyclerAdopter(private val viewModel: PersonDetailsViewModel?) :
    ListAdapter<Person, PersonRecyclerAdopter.PersonViewHolder>(personDifferenceCallBack) {

    class PersonViewHolder(var binding: PersonRowBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(person: Person) {
//            binding.person = person
//
//        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PersonViewHolder {
        val binding = PersonRowBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return PersonViewHolder(binding = binding)
    }

    override fun onBindViewHolder(p0: PersonViewHolder, location: Int) {
//        p0.bind(getItem(location))
        p0.binding.person = getItem(location)
        p0.binding.viewModel = viewModel
        p0.binding.tapButton.setOnClickListener {
            viewModel?.tapAction()
        }
    }


    private object personDifferenceCallBack : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(p0: Person, p1: Person): Boolean {
            return p0.email == p1.email;
        }

        override fun areContentsTheSame(p0: Person, p1: Person): Boolean {
            return p0.email == p1.email;
        }
    }
}
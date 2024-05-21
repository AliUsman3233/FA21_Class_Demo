package com.example.fa21_class_demo

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fa21_class_demo.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToHomePage()
    }


    private fun goToHomePage() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}
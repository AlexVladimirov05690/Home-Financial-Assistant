package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.FragmentAuthorizationBinding
import com.example.homefinancialassistant.view.MainActivity

class AuthorizationFragment: Fragment() {
    private lateinit var binding: FragmentAuthorizationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signGuest.setOnClickListener {
            activity?.let {
                (activity as MainActivity).binding.bottomMainMenu.isVisible = true
                (activity as MainActivity).navController.navigate(R.id.homeFragment)
            }
        }
    }
}
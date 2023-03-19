package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homefinancialassistant.databinding.FragmentExchangeRatesBinding
import com.example.homefinancialassistant.viewmodels.CreditCalculatorFragmentViewModel
import com.example.homefinancialassistant.viewmodels.ExchangeRatesFragmentViewModel

class ExchangeRatesFragment : Fragment() {
    private lateinit var binding: FragmentExchangeRatesBinding
    private val viewModel: ExchangeRatesFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeRatesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.courseDollar.observe(viewLifecycleOwner) {
            binding.course.text = it.toString()
        }

        binding.refreshCourse.setOnClickListener {
            viewModel.updateCourseDollar()
        }


    }


}
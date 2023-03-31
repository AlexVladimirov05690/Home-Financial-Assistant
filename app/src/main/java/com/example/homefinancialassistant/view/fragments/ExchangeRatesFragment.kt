package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.databinding.FragmentExchangeRatesBinding
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

        viewModel.courseEuro.observe(viewLifecycleOwner) {
            binding.courseEuro.text = it.toString()
        }

        viewModel.courseRub.observe(viewLifecycleOwner) {
            binding.courseRub.text = it.toString()
        }

        viewModel.courseBtc.observe(viewLifecycleOwner) {
            binding.courseBtc.text = it.toString()
        }

        binding.refreshCourse.setOnClickListener {
            //viewModel.updateCourseDollar()
            viewModel.updateRateCurrencyFromDb()
        }


    }


}
package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homefinancialassistant.databinding.FragmentExchangeRatesBinding


class ExpenseJournalFragment : Fragment() {
    private lateinit var binding: FragmentExchangeRatesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeRatesBinding.inflate(inflater)
        return binding.root
    }

}
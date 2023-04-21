package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homefinancialassistant.databinding.FragmentExpenseJournalBinding
import com.example.homefinancialassistant.view.MainActivity


class ExpenseJournalFragment : Fragment() {
    private lateinit var binding: FragmentExpenseJournalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseJournalBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {

        binding.addConsumptionFabButton.setOnClickListener {
            (activity as MainActivity).showAddFragment()
        }
    }

}
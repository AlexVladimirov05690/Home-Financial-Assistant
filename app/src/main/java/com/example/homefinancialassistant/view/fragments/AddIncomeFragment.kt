package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.FragmentAddIncomeBinding
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.viewmodels.AddIncomeFragmentViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar


class AddIncomeFragment : Fragment() {
    private lateinit var binding: FragmentAddIncomeBinding
    private val viewModel: AddIncomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddIncomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonAndFields()
    }

    private fun initButtonAndFields() {
        viewModel.setToday()
        binding.enterDate.setText(viewModel.calendarToString())
        binding.btnAddIncome.setOnClickListener {
            viewModel.category.value = binding.enterCategory.text.toString()
            viewModel.description.value = binding.enterDescription.text.toString()
            viewModel.sum.value = (binding.enterSum.text.toString()).toDouble()
            viewModel.addIncome()
            (activity as MainActivity).navController.popBackStack()
        }
        binding.btnCancel.setOnClickListener {
            (activity as MainActivity).navController.popBackStack()
        }
        binding.btnDate.setOnClickListener {
            btnDatePickerDialog()
        }
    }

    private fun btnDatePickerDialog() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.add_date)
            .build()
        datePicker.show(
            (activity as MainActivity).supportFragmentManager,
            "Date picker2"
        )
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            viewModel.date.value = calendar
            binding.enterDate.setText(viewModel.calendarToString())
        }
        datePicker.addOnNegativeButtonClickListener {}
    }
}